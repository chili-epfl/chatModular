import pika
import yaml
import chat_answers
max_sentences = 2

class MessageQueue(object):
	def __init__(self, name):
		self.name = name
		#Change the parameters of the file to be able to connect to RabbitMQ
		self.settings = yaml.safe_load(open('settings.yaml', 'r').read())
		
		ip = self.settings['ip']
		port = self.settings['port']
		username = self.settings['username']
		pwd = self.settings['pwd']
		vhost = self.settings['vhost']

		credentials = pika.PlainCredentials(username, pwd)
		connection_parameters = pika.ConnectionParameters(
			host=ip, port=port, virtual_host=vhost, credentials=credentials
		)
		self.connection = pika.BlockingConnection(connection_parameters)
		self.channel = self.connection.channel()
		self.channel.exchange_declare(exchange='test-exchange', exchange_type='direct', durable='false')

	def publish(self, exchange='', routing_key='', body={}):
		self.channel.basic_publish(exchange=exchange, routing_key=routing_key, body=body)

	def bind_queue(self, exchange='', routing_key='', queue_name=None):
		result = self.channel.queue_declare(exclusive=True)
		if not queue_name:
			queue_name = result.method.queue
		queue_name = result.method.queue
		self.channel.queue_bind(exchange=exchange, queue=queue_name, routing_key=routing_key)
		
		def callback(ch, method, properties, body):
			if type(body) == bytes:
				body = body.decode("utf-8")
			print("Received %r" %body)
			self.send_back_response(body)
		
		self.channel.basic_consume(callback, queue=queue_name, no_ack=True)
		print('Waiting for messages. To exit press CTRL+C')
		self.channel.start_consuming()    

	def stop(self):
		self.channel.stop_consuming()
		self.connection.close()

	def send_back_response(self, body):
		"""
		Finds and sends back the appropriate response depending on the question asked and the different responses.
		:param body: string
		"""
		mq = MessageQueue('sender')
		body = body.split('=====')
		#Check that has received at least one question and one response and that they're not null
		if len(body) >= 2 and all(item for item in body) :
			question = body.pop(0)
			answers = body
			
			if question == 'stop':
				mq.publish(exchange="test-exchange", routing_key="from_client", body='stop')
				self.stop()
			else:
				#Creating a pair (answer, answer) is done only to be more precise when comparing the question with the forum response
				#(because the forum gives back a pair (best_sentence, sentences)). It is useless to do the next 3 lines if we don't do forum comparison.
				answers = [(answer, answer) for answer in answers]
				forum_response = chat_answers.chatbot_answer(self.parse_question(question)[0], self.parse_question(question)[1])
				answers.append(forum_response)
				
				answer = chat_answers.find_answer(question, answers)
				mq.publish(exchange="test-exchange", routing_key="from_client", body=answer)
		else: mq.publish(exchange="test-exchange", routing_key="from_client", body="Try sending at least one question and one answer and check that they're not null")

	def parse_question(self, question):
		"""
		Parses the question received into a cleansed question and the number of max sentences to put in the response (between 1 and 9).
		:param question: string
		:return: (string, int)
		"""
		if len(question):
			last_character = question[len(question) - 1]
			question = question.strip()
			if last_character.isdigit():
				question = question[:-1]
				return (question, int(last_character))
			else:
				return (question, max_sentences)
		else:
			return (question, max_sentences)

