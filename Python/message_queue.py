#!/usr/bin/env python

import pika
import yaml
import views
#from chatbot_tutorial import views


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
            if type(body) == bytes:
                body = body.decode("utf-8")
            print("Received %r" %body)
            views.chatbot_answer(body, 4)
        
        self.channel.basic_consume(callback, queue=queue_name, no_ack=True)
        print('Waiting for messages. To exit press CTRL+C')
        self.channel.start_consuming()                

    def stop(self):
        self.channel.stop_consuming()
        self.connection.close()
