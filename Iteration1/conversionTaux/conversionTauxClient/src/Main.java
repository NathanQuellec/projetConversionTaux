import javax.naming.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

    /*doit permettre de tester les services
        scenario de test à coder =
        une conversion et une creation de compte
    */

        try {
            InitialContext ctx = new InitialContext();
            conversionTaux.session.ConversionTauxItf c = 
                (conversionTaux.session.ConversionTauxItf) ctx.lookup("ConversionTauxJNDI");

            Scanner s = new Scanner(System.in);
            
            String welcomeText = " Voulez-vous : \n" +
                                    "1 - Creer un compte \n" +
                                    "2 - Supprimer un compte \n" +
                                    "3 - Convertir une somme \n" +
                                    "4 - Creer un taux \n" +
                                    "5 - Modifier un taux \n" +
                                    "6 - Supprimer un taux";

            System.out.println(welcomeText);

            int choix = s.nextInt();

            if(choix == 1){
                Scanner c1 = new Scanner(System.in);
                System.out.println("Saisir le login");
                String login = c1.nextLine();

                System.out.println("Saisir le mot de passe");
                String passwd = c1.nextLine();

                System.out.println(c.creerCompte(login, passwd));
            } 
            else if(choix == 2){
                Scanner c2 = new Scanner(System.in);
                System.out.println("Saisir le login");
                String login = c2.nextLine();

                System.out.println(c.supprimerCompte(login));
            } 
            else if(choix == 3){
                Scanner c3 = new Scanner(System.in);
                System.out.println("Saisir une premiere monnaie");
                String monnaieA = c3.nextLine();

                System.out.println("Saisir une deuxieme monnaie");
                String monnaieB = c3.nextLine();

                System.out.println("Saisir un montant");
                double montant = c3.nextInt();
                
                System.out.println(c.convertir(monnaieA, monnaieB, montant));
            }
            else if(choix == 4){
                Scanner c4 = new Scanner(System.in);
                System.out.println("Saisir la nouvelle monnaie A");
                String newMonnaieA = c4.nextLine();

                System.out.println("Saisir la nouvelle monnaie B");
                String newMonnaieB = c4.nextLine();

                System.out.println("Saisir le nouveau taux");
                double newTaux = c4.nextDouble();

                System.out.println(c.ajouterTaux(newMonnaieA, newMonnaieB, newTaux));
            } 
            else if(choix == 5){
                Scanner c5 = new Scanner(System.in);

                System.out.println("Saisir la monnaie A");
                String monnaieA = c5.nextLine();

                System.out.println("Saisir la monnaie B");
                String monnaieB = c5.nextLine();

                System.out.println("Saisir le nouveau taux");
                double newTaux = c5.nextDouble();

                System.out.println(c.modifierTaux(monnaieA, monnaieB, newTaux));
            } 
            else if(choix == 6){
                Scanner c6 = new Scanner(System.in);
                
                System.out.println("Saisir la monnaie A");
                String monnaieA = c6.nextLine();

                System.out.println("Saisir la monnaie B");
                String monnaieB = c6.nextLine();

                System.out.println(c.supprimerTaux(monnaieA, monnaieB));
            } 
            else{
                System.out.println("Erreur de saisie ! Veuillez taper un chiffre valide");
            }
        }
        catch (Exception e) {
            System.err.println("erreur dans le lookup");
            e.printStackTrace();
        }
        
    }
}
