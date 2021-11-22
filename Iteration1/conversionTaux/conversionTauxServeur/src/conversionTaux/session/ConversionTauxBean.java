package conversionTaux.session;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.entity.*;

/*
 Les indications fournies n'incluent pas ce qui relève de :
1) l'écriture d'un bean session
2) les éléments JPA, en particulier ceux relatifs à l'utilisation d'une entité dans une méthode.

 A vous de déterminer ce qui doit être écrit, en particulier pour la
 synchronisation des entités avec la BD
 */

@Stateless (mappedName = "ConversionTauxJNDI")

public class ConversionTauxBean implements ConversionTauxItf, ConversionTauxCste {
    
    @PersistenceContext (unitName = "ConversionTauxPU")

    private EntityManager em;
    
	public String convertir(String monnaieA, String monnaieB, double montant)
	{
        Query query = em.createQuery("SELECT te.taux FROM TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                        .setParameter("param1", monnaieA)
                        .setParameter("param2", monnaieB);
		try
        {
           /*
            Remonter de la BD en mémoire la valeur du taux correspondant à la paire (monnaieA, monnaieB)
            Retourner la String RESULTAT + montant * taux trouvé)
            */
            
            Double taux = (Double) query.getSingleResult();

            return RESULTAT + montant * taux;
		} 
		catch (NoResultException e) //exception si la paire (monnaieA, monnaieB) n'est pas trouvee
		{
			return NO_RESULTAT;
		}
		catch (NonUniqueResultException e) //exception si plusieurs paires (monnaieA, monnaieB) sont trouvees
		{
			return RESULTAT_MULTIPLE;
		}
	}

    public String ajouterTaux(String monnaieA, String monnaieB, double taux)
    {
        try {
            TauxEntity te = (TauxEntity) em.createQuery("SELECT te from TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                            .setParameter("param1", monnaieA)
                            .setParameter("param2", monnaieB)
                            .getSingleResult();

            return NO_INSERTION; 
        }
        catch (NonUniqueResultException e)
        {
            return RESULTAT_MULTIPLE + "\n" + NO_INSERTION;
        }
        catch (NoResultException e)
        {
            TauxEntity newTaux = new TauxEntity();
            newTaux.setMonnaieA(monnaieA);
            newTaux.setMonnaieB(monnaieB);
            newTaux.setTaux(taux);
            try {
                em.persist(newTaux);
                return SUCCESS;
            }
            catch (EntityExistsException ee)
            {
                return NO_INSERTION;
            }
        }  
    }

    public String modifierTaux(String monnaieA, String monnaieB, double newTaux)
    {
        Query query = em.createQuery("SELECT te FROM TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                        .setParameter("param1", monnaieA)
                        .setParameter("param2", monnaieB);
        try {
            
            TauxEntity te = (TauxEntity)query.getSingleResult();
            te.setTaux(newTaux);
            em.flush();
            return SUCCESS;
        }
        catch (NoResultException e)  
        {
            return NO_RESULTAT;
        }
        catch (NonUniqueResultException e)
		{
			return RESULTAT_MULTIPLE;
		}
    }

    public String supprimerTaux(String monnaieA, String monnaieB)
    {
        try {
            TauxEntity te = (TauxEntity) em.createQuery("SELECT te FROM TauxEntity te WHERE te.monnaieA = :param1 and te.monnaieB = :param2")
                            .setParameter("param1", monnaieA)
                            .setParameter("param2", monnaieB)
                            .getSingleResult();
            em.remove(te);
            te = null;
            return SUCCESS;
        }
        catch(NoResultException e)
        {
            return NO_RESULTAT;
        }
        catch (NonUniqueResultException e) 
		{
			return RESULTAT_MULTIPLE;
		}
    }
    
    public String creerCompte(String login, String passwd)
    {
        //Instancier l'entité
        try{
            AbonneEntity a = (AbonneEntity) em.createQuery("SELECT a FROM AbonneEntity a WHERE a.login = :param")
                                                .setParameter("param", login)
                                                .getSingleResult();
            // pas bon, il existe, on n'insere pas
            return NO_INSERTION;
        }

        catch (NonUniqueResultException e)
        {
            // pas bon, il existe deja => on n'insere pas
            return RESULTAT_MULTIPLE + "\n" + NO_INSERTION;
        }
        catch (NoResultException e){
            // c'est bon, il n'existe pas, on le cree
            AbonneEntity newAbonne = new AbonneEntity(login, passwd);
            try {
                //La faire passer à l'état "managed" (cf le diagramme du cycle de vie d'une entité)
                em.persist(newAbonne);
                return SUCCESS;
            }
            catch (EntityExistsException ee) //exception si les valeurs existent deja
            {
                return NO_INSERTION;
            }  
        }
    }

    public String supprimerCompte(String login)
    { 
        try {
            AbonneEntity ae = (AbonneEntity) em.createQuery("SELECT ae FROM AbonneEntity ae WHERE ae.login = :param")
                            .setParameter("param", login)
                            .getSingleResult();
            em.remove(ae);
            ae = null;
            return SUCCESS;
        }
        catch(NoResultException e)
        {
            return NO_RESULTAT;
        }
        catch (NonUniqueResultException e) 
		{
			return RESULTAT_MULTIPLE;
		}
    }
}

