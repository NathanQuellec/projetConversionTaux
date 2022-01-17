package conversionTaux.session.admin;

import javax.ejb.*;

@Remote()
public interface ConversionTauxAdminItf {
    public String connecter(String login, String passwd);
    public String consulterNbConnexion(); 
    public String top5taux(); 
    public String deconnecter();
}