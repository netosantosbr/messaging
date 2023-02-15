package br.com.gubee.producer;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageProducer {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String jmsQueue = "example_queue";

    public void produceMessage(String mensageToSend) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(jmsQueue);

        javax.jms.MessageProducer producer = session.createProducer(destination);

        TextMessage message = session
                .createTextMessage(mensageToSend);

        producer.send(message);

        System.out.println("Successfully sent: " + message.getText());
        connection.close();
    }

}
