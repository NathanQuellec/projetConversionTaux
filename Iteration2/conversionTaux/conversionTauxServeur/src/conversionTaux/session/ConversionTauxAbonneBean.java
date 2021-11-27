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

    public String ajouterFavori(String favori, String monnaieA, String monnaieB){   // la table Abonne_Favori du sgbd sera mise a jour uniquement a la deconnection 

        if(abonne == null) {
            return ACCESS_DENIED;
        }
         em.merge(abonne);

        // add try catch unique libelle
        Collection<FavoriEntity> listeFav = new ArrayList<>();
        listeFav = abonne.getLesFavoris();

        try {
            
            TauxEntity te = (TauxEntity) em.createQuery("SELECT te FROM TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                                .setParameter("param1", monnaieA)
                                .setParameter("param2", monnaieB)
                                .getSingleResult();

            FavoriEntity newFavori = new FavoriEntity(favori, te);
            

            listeFav.add(newFavori);
            abonne.setLesFavoris(listeFav);
            
            em.persist(newFavori);              
            em.detach(abonne);
            return SUCCESS + " " + abonne.getLesFavoris();
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
        em.merge(abonne);
        if(abonne != null) {
            abonne = null;
            return SUCCESS;
        }
        else
            return ACCESS_DENIED;
    }
}