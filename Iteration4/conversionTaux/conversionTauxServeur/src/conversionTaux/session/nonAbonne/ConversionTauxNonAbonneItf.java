package conversionTaux.session.nonAbonne;

import javax.ejb.*;

@Remote()
public interface ConversionTauxNonAbonneItf {
	public String convertir(String monnaieA, String monnaieB, double montant);
	public String creerCompte(String login, String passwd);
}
