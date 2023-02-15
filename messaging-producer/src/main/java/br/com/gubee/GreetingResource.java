package br.com.gubee;

import br.com.gubee.producer.MessageProducer;

import javax.jms.JMSException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("")
public class GreetingResource {

    @GET()
    @Path("/sendMessage")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMessage(@QueryParam("message") String message) {
        MessageProducer messageProducer = new MessageProducer();
        try {
            messageProducer.produceMessage(message);
        } catch (JMSException jmsException) {
            System.err.println("JMS Exception captured");
        }
        return "Aplicação do Producer para testar mensageria com Quarkus, JMS e ActiveMQ";
    }
}