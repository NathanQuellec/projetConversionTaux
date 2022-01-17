package conversionTaux.session.admin;

import javax.ejb.Remote;

@Remote()
public interface ConversionTauxAdminItf {
    public String connecter(String login, String passwd);
    public String consulterNbConnexion(); 
    public String deconnecter();
}