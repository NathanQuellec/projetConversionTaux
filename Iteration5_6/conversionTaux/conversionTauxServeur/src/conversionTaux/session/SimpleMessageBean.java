package conversionTaux.session;

import javax.ejb.*;
import javax.jms.*;
import javax.persistence.*;
import javax.annotation.*;
import javax.annotation.Resource;
import conversionTaux.entity.*;
import conversionTaux.session.ConversionTauxCste;

@MessageDriven(mappedName = "uneBALTopic")

public class SimpleMessageBean implements MessageListener, ConversionTauxCste {

    @Resource(mappedName="uneConnectionFactoryQueue") private ConnectionFactory cf;
    @Resource(mappedName="uneBALQueue") private Queue queue;


    @PersistenceContext (unitName = "ConversionTauxPU")
    private EntityManager em;

    

    private Connection connection = null;
    private Session session = null;
    private MessageProducer messageProducer = null;

    public SimpleMessageBean() {}

    @PostConstruct
    public void  onPostConstruct(){
        try {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);         
        }
        catch (Exception ex) {
            System.err.println("erreur dans le lookup");
            ex.printStackTrace();
        }
    }

    @PreDestroy
    public void onPreDestroy(){
        try{
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String modifierTaux(String monnaieA, String monnaieB, double taux){
        try {
            TauxEntity te = (TauxEntity) em.createQuery("SELECT te FROM TauxEntity te WHERE te.monnaieA = :param1 AND te.monnaieB = :param2")
                                            .setParameter("param1", monnaieA)
                                            .setParameter("param2", monnaieB)
                                            .getSingleResult();
            te.setTaux(taux);
            em.flush();

            return SUCCESS;
            
        }   
        catch(NonUniqueResultException e){
            return RESULTAT_MULTIPLE;
        }
        catch(NoResultException e) {
            return  NO_RESULTAT;
        }
    }

    public void onMessage(Message inMessage){

        TextMessage msg = null;
        TextMessage msgInit = null;
        String monnaies, monnaieA, monnaieB;
        double taux;
        try{
            if(inMessage instanceof TextMessage){
                msg = (TextMessage) inMessage;
                monnaies = msg.getText();
                taux = msg.getDoubleProperty("taux");
                monnaieA = monnaies.substring(0, monnaies.lastIndexOf("-"));
                monnaieB = monnaies.substring(monnaies.lastIndexOf("-")+1);
                //System.out.println("Message recu " + monnaieA + " " + monnaieB + " " + taux);
                modifierTaux(monnaieA, monnaieB, taux);

                //ite 6
                msgInit = session.createTextMessage("taux " + monnaieA + "-" + monnaieB + " modifié");
                System.out.println("Sending message: " + msgInit.getText());
                messageProducer.send(msgInit);

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