package conversionTaux.session.nonAbonne;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.entity.*;

@Stateless (mappedName = "ConversionTauxNonAbonneJNDI")

public class ConversionTauxNonAbonneBean implements ConversionTauxNonAbonneItf, ConversionTauxCste {
    
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
}

