package conversionTaux.session.compteur;

import javax.ejb.*;

@Local()
public interface InitItf {
    @Lock (LockType.WRITE)
    public void initCpt();
}
