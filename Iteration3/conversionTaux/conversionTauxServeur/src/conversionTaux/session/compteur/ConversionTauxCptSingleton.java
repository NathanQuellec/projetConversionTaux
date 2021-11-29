package conversionTaux.session.compteur;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.entity.*;
import java.util.*;


@Singleton
public class ConversionTauxCptSingleton implements ConversionTauxCptSingletonItf, ConversionTauxCste {

    private Integer compteurConnexions;

    public int lireCpt() {
        return compteurConnexions;
    }

    public void incrementerCpt() {
        compteurConnexions++;
    }

}