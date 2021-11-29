import javax.naming.InitialContext;
import java.io.*;
import java.util.*;
import conversionTaux.session.*;

public class Main {
public static void main(String[] args) throws Exception {

    /*
    doit permettre de tester les services. Il faut garantir que tous les scénarios possibles s'exécutent correctement.
    
     scenario 1
     1) un abonne a1 se connecte => ok
     2) un abonne a2 se connecte => ok
     3) un admin se connecte => ok
     4) l'admin connecté consulte le nb de connexion => ok = 2
     5) deconnexion de tous
     
     scenario 2
     1) un abonne a1 se connecte => ok
     2) l'abonne a1 se déconnecte => ok
     3) un admin se connecte => ok
     4) l'admin connecté consulte le nb de connexion => ok = 3
     5) deconnexion de l'admin
     
     scenario 3
     1) un admin ad1 consulte le nb de connexion => nok
     2) l'admin ad1 se connecte => ok
     3) l'admin connecté ad1 consulte le nb de connexion => ok = 3
     4) un admin ad2 consulte le nb de connexion => nok
     5) deconnexion de l'admin ad1
     
     Tous les autres services des deux itérations précédentes
     doivent encore être opérationnels, vous pouvez donc
     ajouter leurs scénarios une fois ceux de cette itération ok.
     Vérifier que tout est resté ok.
    
     */

     try {
         InitialContext ctx = new InitialContext();
            conversionTaux.session.ConversionTauxNonAbonneItf nonAbonne = 
                (conversionTaux.session.ConversionTauxNonAbonneItf) ctx.lookup("ConversionTauxNonAbonneJNDI");
            
            conversionTaux.session.ConversionTauxAbonneItf abonne = 
                (conversionTaux.session.ConversionTauxAbonneItf) ctx.lookup("ConversionTauxAbonneJNDI");

            conversionTaux.session.ConversionTauxAbonneItf admin = 
                (conversionTaux.session.ConversionTauxAbonneItf) ctx.lookup("ConversionTauxAdminJNDI");


     }
}
