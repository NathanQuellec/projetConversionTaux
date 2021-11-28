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

        if(abonne != null)
            return "Vous etes deja connectes a un compte abonne !";
        
        try {
            abonne = (AbonneEntity) em.createQuery("SELECT ae FROM AbonneEntity ae WHERE ae.login = :param1 and ae.passwd = :param2")
                                                .setParameter("param1", login)
                                                .setParameter("param2", passwd)
                                                .getSingleResult();
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

    public String ajouterFavori(String favori, String monnaieA, String monnaieB){   // la table Abonne_Favori sera mise a jour dans le sgbd ultierement

        if(abonne == null) 
            return ACCESS_DENIED;
        
         abonne = em.merge(abonne);
         em.refresh(abonne);

        // add try catch unique libelle
 
        try {
            FavoriEntity fe = (FavoriEntity) em.createQuery("SELECT fe FROM AbonneEntity ae JOIN ae.lesFavoris fe WHERE ae.login = :param1 and fe.libelleFavori = :param2")
                                                .setParameter("param1", abonne.getLogin())
                                                .setParameter("param2", favori)
                                                .getSingleResult();

            return NO_INSERTION;
        }
        catch(NonUniqueResultException e) {
            return RESULTAT_MULTIPLE + "\n" + NO_INSERTION;
        }
        catch(NoResultException e) {
            // si libelle n'existe pas alors on insere le favori
            Collection<FavoriEntity> listeFav = new ArrayList<>();
            listeFav = abonne.getLesFavoris();

            try {
                // on recupere le taux
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
            catch (EntityExistsException eee) {
                return NO_INSERTION;
            }
            catch(NoResultException eee) {
                return NO_RESULTAT;
            }
            catch(NonUniqueResultException eee) {
                return RESULTAT_MULTIPLE;
            }
        }
    }

    public String supprimerFavori(String favori){  // ne fonctionne pas, erreur contrainte avec table Abonne_Favori

        if(abonne == null)
            return ACCESS_DENIED;

        abonne = em.merge(abonne);
        em.refresh(abonne);

        try {
            //on recupere le bon favori de l'abonne connecte
            FavoriEntity fe = (FavoriEntity) em.createQuery("SELECT fe FROM AbonneEntity ae JOIN ae.lesFavoris fe WHERE ae.login = :param1 and fe.libelleFavori = :param2")
                                                .setParameter("param1", abonne.getLogin())
                                                .setParameter("param2", favori)
                                                .getSingleResult();

            Collection<FavoriEntity> listeFav = new ArrayList<>();
            listeFav = abonne.getLesFavoris();
            listeFav.remove(fe);
            abonne.setLesFavoris(listeFav);

            em.remove(fe);
            em.detach(abonne);
            return SUCCESS + fe;
        }
        catch(NoResultException e) {
            return NO_RESULTAT;
        }
        catch(NonUniqueResultException e) {
            return RESULTAT_MULTIPLE;
        }
    }

    public String convertir(String favori, double montant){

        if(abonne == null){
            return ACCESS_DENIED;
        }
        abonne = em.merge(abonne);
        em.refresh(abonne);
        try {
           // on recupere le taux du bon favori ET de l'abonne connecte
            double taux = (Double) em.createQuery("SELECT te.taux FROM AbonneEntity ae JOIN ae.lesFavoris fe JOIN fe.leTaux te WHERE ae.login = :param1  and fe.libelleFavori = :param2")
                                    .setParameter("param1", abonne.getLogin())
                                    .setParameter("param2", favori)
                                    .getSingleResult();

            em.detach(abonne);
            return RESULTAT + (montant * taux);
        }
        catch (NonUniqueResultException e){
            return RESULTAT_MULTIPLE;
        }

        catch (NoResultException e){
            return NO_RESULTAT;
        }
    }

    public String deconnecter(){ 
        
        if(abonne != null) {
            abonne = em.merge(abonne);
            em.refresh(abonne);
            abonne = null;
            return SUCCESS;
        }
        else
            return ACCESS_DENIED;
    }
}