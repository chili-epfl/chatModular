#!/usr/bin/env python

import pika
import yaml
#import chat_answers				#for original files
import my_chat_answers				#for files that I generated
max_sentences = 2

class MessageQueue(object):
    def __init__(self, name):
        self.name = name
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
            mq = MessageQueue('sender')
            if type(body) == bytes:
                body = body.decode("utf-8")
            print("======================================================")
            print("Received %r" %body)

            body = body.split('=====')
            if (len(body) != 2):
                mq.publish(exchange="test-exchange", routing_key="from_client", body="I'm sorry I didn't find anything, would you like to ask another question?")
            else:
                question = body[0]
                tuto_response = body[1]

                print("QUESTION: ", question)
                print("TUTO RESPONSE: ", tuto_response)
                if not question or not tuto_response:
                    #question or tuto_response is empty
                    mq.publish(exchange="test-exchange", routing_key="from_client", body="I'm sorry I didn't find anything, would you like to ask another question?")
                else:
                    #what to do when receives something from IrisTK
                    if question == 'stop':
                        print("STOP")
                        mq.publish(exchange="test-exchange", routing_key="from_client", body='stop') #send back to stop
                        self.stop()
                    else:
                        #my_chat_answers.chatbot_answer(body, 1)							#change here
                        forum_response = my_chat_answers.chatbot_answer(self.find_text_and_max_sentences(question)[0], self.find_text_and_max_sentences(question)[1]) #find forum response
                        answer = my_chat_answers.find_answer(question, forum_response, tuto_response) #find the best answer between forum and tuto response
                        mq.publish(exchange="test-exchange", routing_key="from_client", body=answer) #send back the response
        
        self.channel.basic_consume(callback, queue=queue_name, no_ack=True)
        print('Waiting for messages. To exit press CTRL+C')
        self.channel.start_consuming()    


    def stop(self):
        self.channel.stop_consuming()
        self.connection.close()

    #find the number of sentences to put in the response (between 1 and 9)
    def find_text_and_max_sentences(self, question):
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
        
    

