package br.com.gubee;

import br.com.gubee.consumer.MessageConsumer;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class GreetingResource {

    @GET()
    @Path("/receiveMessage")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveMessage() {
        MessageConsumer messageConsumer = new MessageConsumer();
        TextMessage message;
        try {
             message = (TextMessage) messageConsumer.consumeMessage();
             return "Método receiveMessage() recebeu a mensagem: " + message.getText();
        } catch (JMSException jmsException) {
            System.err.println("JMS Exception captured");
        }
        return "Aplicação do Consumer para testar mensageria com Quarkus, JMS e ActiveMQ";
    }

    @GET()
    @Path("/anotherMethod")
    @Produces(MediaType.TEXT_PLAIN)
    public String anotherMethod() {
        return "Aplicação do Consumer para testar mensageria com Quarkus, JMS e ActiveMQ";
    }

}