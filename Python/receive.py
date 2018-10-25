#!/usr/bin/env python

import pika
import yaml

settings = yaml.safe_load(open('settings.yaml', 'r').read())
ip = settings['ip']
port = settings['port']
username = settings['username']
pwd = settings['pwd']
vhost = settings['vhost']

credentials = pika.PlainCredentials(username, pwd)
connection_parameters = pika.ConnectionParameters(
            host=ip, port=port, virtual_host=vhost, credentials=credentials
        )

connection = pika.BlockingConnection(connection_parameters)
channel = connection.channel()


channel.exchange_declare(exchange='test-exchange', exchange_type='direct', durable='false')
result = channel.queue_declare(exclusive=True)
queue_name = result.method.queue
channel.queue_bind(exchange='test-exchange', queue=queue_name, routing_key='from_server')

def callback(ch, method, properties, body):
    print("Received %r" % body)

channel.basic_consume(callback,
                      queue=queue_name,
                      no_ack=True)
                      


print('Waiting for messages. To exit press CTRL+C')
channel.start_consuming()
