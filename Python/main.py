from message_queue import MessageQueue

mq = MessageQueue('receiver')
mq.bind_queue(exchange='test-exchange', routing_key='from_server')

