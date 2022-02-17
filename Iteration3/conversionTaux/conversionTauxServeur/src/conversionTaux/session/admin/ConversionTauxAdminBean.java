package conversionTaux.session.admin;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.session.compteur.*;
import conversionTaux.entity.*;
import java.util.*;

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

    public String deconnecter(){
        admin = em.merge(admin);
        if(admin == null)
            return ACCESS_DENIED;

        admin = null;
        return SUCCESS;
    }
}