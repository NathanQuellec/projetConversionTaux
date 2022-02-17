package conversionTaux.session.compteur;

import javax.ejb.*;
import javax.persistence.*;
import conversionTaux.session.ConversionTauxCste;
import conversionTaux.entity.*;
import java.util.*;


@Singleton
public class ConversionTauxCptSingleton implements ConversionTauxCptSingletonItf, InitItf, ConversionTauxCste {

    private Integer compteurConnexions = 0;

    public int lireCpt() {
        return compteurConnexions;
    }

    public void incrementerCpt() {
        compteurConnexions++;
    }

    public void decrementerCpt() {
        compteurConnexions--;
    }

    public void initCpt() {
        compteurConnexions = 0;
    }
}