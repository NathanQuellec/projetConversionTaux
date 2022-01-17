package conversionTaux.session.compteur;

import javax.ejb.*;

@Local()
public interface ConversionTauxCptSingletonItf {

    public int lireCpt();
    @Lock (LockType.WRITE)
    public void incrementerCpt();
    @Lock (LockType.WRITE)
    public void decrementerCpt();
}
