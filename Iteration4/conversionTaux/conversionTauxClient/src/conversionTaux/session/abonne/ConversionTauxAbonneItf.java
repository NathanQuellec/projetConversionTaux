package conversionTaux.session.abonne;

import javax.ejb.*;

@Remote()
public interface ConversionTauxAbonneItf {
    public String connecter(String login, String passwd);
    public String ajouterFavori(String favori, String monnaieA, String monnaieB);
    public String supprimerFavori(String favori);
    public String listerFavoris();
    public String convertir(String favori, double montant);
    public String deconnecter();
}