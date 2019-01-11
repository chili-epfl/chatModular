# Building a Modular Architecture for Customizable Chatbots from two Already Existing Frameworks

## Presentation

Working to build out chat capabilities for DUAL-T and FROG. The aim of this project is to develop a modular architecture for custom chatbots. To do this I extended and combined two already existing frameworks for building chatbots and tested different strategies to see which ones could be apply for a customizable chatbot. The final goal is to connect this architecture with REALTO and FROG.
To demonstrate my work I wrote a chatbot representing either a tutor helping a student or a travel agent giving information to its customer, depending on the question asked. The interface accepts inputs in natural language (text input or speech recognition) and displays the most appropriate reply for the user’s request.

## How to use

You can generate the PKL files needed for the python code by running the Jupyter Notebook file Generalized Forum Scraping and Creation of PKL files.
To run the python code first add your RabbitMQ parameters in the settings.yaml file then you can run python main.py.
To run the IrisTK code you must change the RabbitMQ parameters in URI.java (in app/tutoring/src) and you can run ChatbotSystem.java (in app/tutoring/src also).

