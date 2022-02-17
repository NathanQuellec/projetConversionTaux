package conversionTaux.session;

import javax.ejb.*;
import javax.jms.*;

@MessageDriven(mappedName = "uneBALQueue")

public class InitCptMDB implements MessageListener {
   @EJB
    private conversionTaux.session.compteur.InitItf i; 

    public InitCptMDB() {}

    public void onMessage(Message inMessage){
         TextMessage msg = null;
         try{
            if(inMessage instanceof TextMessage){
                msg = (TextMessage) inMessage;
                System.out.println("Receive message: " + msg.getText());
                i.initCpt();
            }
            else {
                System.out.println(
                        "Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        }
        catch (JMSException e) {
            e.printStackTrace();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
