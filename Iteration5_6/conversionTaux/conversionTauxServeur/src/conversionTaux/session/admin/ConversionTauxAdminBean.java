package conversionTaux.session.admin;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.session.compteur.*;
import conversionTaux.entity.*;
import java.util.*;
import java.util.stream.*;

@Stateful (mappedName = "ConversionTauxAdminJNDI")

public class ConversionTauxAdminBean implements ConversionTauxAdminItf, ConversionTauxCste {

    @PersistenceContext (unitName = "ConversionTauxPU")
    
    private EntityManager em;
    private AdminEntity admin;

    @EJB
    private conversionTaux.session.compteur.ConversionTauxCptSingletonItf c;

    public String connecter(String login, String passwd){

        if(admin != null)
            return "Vous etes deja connectes";

        try {
            admin = (AdminEntity) em.createQuery("SELECT ade FROM AdminEntity ade WHERE ade.login = :param1 and ade.passwd = :param2")
                                    .setParameter("param1", login)
                                    .setParameter("param2", passwd)
                                    .getSingleResult();
            return SUCCESS;
        }
        catch(NonUniqueResultException e) {
            return RESULTAT_MULTIPLE;
        }
        catch(NoResultException e) {
            return  NO_RESULTAT;
        }
    }

    public String consulterNbConnexion(){
        if(admin == null)
            return ACCESS_DENIED;

        int rep = c.lireCpt();
        return RESULTAT_ADMIN + rep;
    }

    public String top5taux(){
       if(admin == null)
            return ACCESS_DENIED;
        try {
            List<Object[]> rep = em.createQuery("SELECT te.monnaieA, te.monnaieB, COUNT(te.idTaux) FROM " +         
                                                "AbonneEntity ae JOIN ae.lesFavoris fe JOIN fe.leTaux te " +       
                                                "GROUP BY te.idTaux ORDER BY COUNT(te.idTaux) DESC")
                                    .setMaxResults(5)
                                    .getResultList();
            String repToSend = rep.stream().map(obj -> Arrays.asList(obj)).collect(Collectors.toList()).toString();
            return SUCCESS + " " + repToSend;    
        }
            //List<Object> repToSend = new ArrayList<>();
            //rep.forEach(obj -> repToSend.add(Arrays.asList(obj)));
            
        catch(NoResultException e) {
            return NO_RESULTAT;
        }    
    }

    public String deconnecter(){
        admin = em.merge(admin);
        if(admin == null)
            return ACCESS_DENIED;

        admin = null;
        return SUCCESS;
    }
}