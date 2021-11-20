package conversionTaux.session;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.entity.*;

@Stateful (mappedName = "ConversionTauxAbonneJNDI")

public class ConversionTauxAbonneBean implements ConversionTauxAbonneItf, ConversionTauxCste {

    @PersistenceContext (unitName = "ConversionTauxPU")
    
    private EntityManager em;

    public String connecter(String login, String passwd){
        
        try {
            AbonneEntity abonne = (AbonneEntity) em.createQuery("SELECT ae FROM AbonneEntity ae WHERE ae.login = :param1 and ae.passwd = :param2")
                                                .setParameter("param1", login)
                                                .setParameter("param2", passwd)
                                                .getSingleResult();

            return SUCCESS;
        }
        catch(NoResultException e) {
            return NO_RESULTAT;
        }
       
    }

    public String ajouterFavori(String favori, String monnaieA, String monnaieB){
        return SUCCESS;
    }

    public String convertir(String favori, double montant){
        return SUCCESS;

    }

    public String deconnecter(){
        return SUCCESS;

    }
}