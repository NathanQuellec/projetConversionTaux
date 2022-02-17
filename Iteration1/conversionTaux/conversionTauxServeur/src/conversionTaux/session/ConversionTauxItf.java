package conversionTaux.session;

import javax.ejb.Remote;

@Remote()
public interface ConversionTauxItf {
	public String convertir(String monnaieA, String monnaieB, double montant);
	public String ajouterTaux(String monnaieA, String monnaieB, double taux);
	public String modifierTaux(String monnaieA, String monnaieB, double newTaux);
	public String supprimerTaux(String monnaieA, String monnaieB);
	public String creerCompte(String login, String passwd);
	public String supprimerCompte(String login);
	
}
