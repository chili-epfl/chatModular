#!/usr/bin/env python

import pika
import yaml
import sys

settings = yaml.safe_load(open('settings.yaml', 'r').read())
ip = settings['ip']
port = settings['port']
username = settings['username']
pwd = settings['pwd']
vhost = settings['vhost']

var = raw_input("Please enter something: ")

credentials = pika.PlainCredentials(username, pwd)
connection_parameters = pika.ConnectionParameters(
            host=ip, port=port, virtual_host=vhost, credentials=credentials
        )

connection = pika.BlockingConnection(connection_parameters)
channel = connection.channel()

channel.exchange_declare(exchange='test-exchange', exchange_type='direct', durable='true')

channel.basic_publish(exchange='test-exchange',
					  routing_key='from_client',
					  body=var)

connection.close()
