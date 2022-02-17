import javax.naming.InitialContext;
import java.io.*;
import java.util.*;

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
            conversionTaux.session.nonAbonne.ConversionTauxNonAbonneItf nonAbonne = 
                (conversionTaux.session.nonAbonne.ConversionTauxNonAbonneItf) ctx.lookup("ConversionTauxNonAbonneJNDI");
            
            conversionTaux.session.abonne.ConversionTauxAbonneItf abonne1 = 
                (conversionTaux.session.abonne.ConversionTauxAbonneItf) ctx.lookup("ConversionTauxAbonneJNDI");

            conversionTaux.session.abonne.ConversionTauxAbonneItf abonne2 = 
                (conversionTaux.session.abonne.ConversionTauxAbonneItf) ctx.lookup("ConversionTauxAbonneJNDI");

            conversionTaux.session.admin.ConversionTauxAdminItf admin = 
                (conversionTaux.session.admin.ConversionTauxAdminItf) ctx.lookup("ConversionTauxAdminJNDI");
           

            System.out.println(abonne1.connecter("a1", "a1m"));
            System.out.println(abonne2.connecter("a2", "a2m"));

            System.out.println(admin.connecter("admin", "admin"));
            System.out.println(admin.consulterNbConnexion());

            System.out.println(abonne2.listerFavoris());

            System.out.println(abonne1.deconnecter());
            System.out.println(abonne2.deconnecter()); 
            System.out.println(admin.deconnecter());



     }
    catch (Exception ex) {
            System.err.println("erreur dans le lookup");
            ex.printStackTrace();
        }
}
}
