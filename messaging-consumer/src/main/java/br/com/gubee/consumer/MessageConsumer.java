package br.com.gubee.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageConsumer {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String jmsQueue = "example_queue";

    public Message consumeMessage() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(jmsQueue);

        javax.jms.MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Successfully received: " + textMessage.getText());
        }

        connection.close();

        return message;
    }
}
