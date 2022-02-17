import javax.naming.InitialContext;
import java.io.*;
import javax.jms.*;

public class Main {
public static void main(String[] args) throws Exception {

    TextMessage message = null;
    ConnectionFactory cf = null;
    Topic topic = null;

    Connection connection = null;
    Session session = null;
    MessageProducer messageProducer = null;
    
    try {
         InitialContext ctx = new InitialContext();

         cf = (ConnectionFactory) ctx.lookup("uneConnectionFactoryTopic");
         topic = (Topic) ctx.lookup("uneBALTopic");
     }
    catch (Exception ex) {
            System.err.println("erreur dans le lookup");
            ex.printStackTrace();
        }
    try {
        connection = cf.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		messageProducer = session.createProducer(topic);
		message = session.createTextMessage();

        message.setDoubleProperty("taux", 1.05);
        message.setText("euros-dollars");
        System.out.println("Sending message: " + message.getText());
        messageProducer.send(message);
    }
    catch (JMSException e){
        System.out.println("Exception occurred: " + e.toString());
    }
}
}
