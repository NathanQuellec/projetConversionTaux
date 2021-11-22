package conversionTaux.session;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.entity.*;
import java.util.*;

@Stateful (mappedName = "ConversionTauxAbonneJNDI")

public class ConversionTauxAbonneBean implements ConversionTauxAbonneItf, ConversionTauxCste {

    @PersistenceContext (unitName = "ConversionTauxPU")
    
    private EntityManager em;
    private AbonneEntity abonne;

    public String connecter(String login, String passwd){
        
        try {
            abonne = (AbonneEntity) em.createQuery("SELECT ae FROM AbonneEntity ae WHERE ae.login = :param1 and ae.passwd = :param2")
                                                .setParameter("param1", login)
                                                .setParameter("param2", passwd)
                                                .getSingleResult();
            // a finir
            em.detach(abonne);
            return SUCCESS;
        }
        catch(NoResultException e) {
            return NO_RESULTAT;
        }
        catch(NonUniqueResultException e) {
            return RESULTAT_MULTIPLE;
        }
    }

    public String ajouterFavori(String favori, String monnaieA, String monnaieB){
        try {

            em.merge(abonne);
            //em.refresh(abonne);
            // add try catch unique libelle
            List<FavoriEntity> listeFav = abonne.getLesFavoris();

            TauxEntity te = (TauxEntity) em.createQuery("SELECT te FROM TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                                .setParameter("param1", monnaieA)
                                .setParameter("param2", monnaieB)
                                .getSingleResult();

            FavoriEntity newFavori = new FavoriEntity();
            em.persist(newFavori);
            newFavori.setLeTaux(te);
            newFavori.setLibelleFavori(favori);

            listeFav.add(newFavori);

            abonne.setLesFavoris(listeFav);
            
            return SUCCESS;
        }
        catch(NoResultException e) {
            return NO_RESULTAT;
        }
        catch(NonUniqueResultException e) {
            return RESULTAT_MULTIPLE;
        }
    }

    public String convertir(String favori, double montant){
        return SUCCESS;

    }

    public String deconnecter(){
        if(abonne != null) {
            abonne = null;
            return SUCCESS;
        }
        else
            return ACCESS_DENIED;
    }
}