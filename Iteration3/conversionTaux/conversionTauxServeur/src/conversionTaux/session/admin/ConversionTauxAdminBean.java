package conversionTaux.session.abonne;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.entity.*;
import java.util.*;

@Stateful (mappedName = "ConversionTauxAdminJNDI")

public class ConversionTauxAdminBean implements ConversionTauxAdminItf, ConversionTauxCste {

    @PersistenceContext (unitName = "ConversionTauxPU")
    
    private EntityManager em;

    public String connecter(String login, String passwd){
        return SUCCESS;
    }

    public String consulterNbConnexion(){
        return SUCCESS;
    }

    public String deconnecter(){
        return SUCCESS;
    }
}