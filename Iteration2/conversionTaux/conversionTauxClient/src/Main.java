import javax.naming.InitialContext;
import java.io.*;
import java.util.*;

public class Main {
public static void main(String[] args) throws Exception {

    /*
    doit permettre de tester les services. Il faut garantir que tous les scénarios possibles s'exécutent correctement.
     
     Par exemple :
     
    un premier scenario de test (dont les 2 premières étapes viennent de l'itération 1) =
     1) une conversion ouverte a tous
     2) une creation de compte login a1
     3) une connexion de a1
     4) une creation de favori libelle f1
     5) une conversion avec favori f1
     6) une conversion ouverte à tous
     7) une deconnexion de a1
     
    un deuxième scenario de test (en supposant que le 1er est exécuté avec succès, donc un compte existe et un favori aussi)
     
     1) une conversion avec favori f1 => doit echouer (car pas de connexion)
     2) une creation de favori f2 => doit echouer (car pas de connexion)
     3) une conversion ouverte a tous => doit reussir
     
     un troisième scenario de test (en supposant que les deux premiers sont exécutés avec succès)
     
     1) une creation de compte login a1 => doit echouer (car login a1 existe deja)
     2) une connexion de a1
     3) une creation de favori libelle f1 => doit echouer (car a1 a deja un favori libelle f1)
     4) une deconnexion de a1
     
     un quatrième scenario de test (en supposant que les trois premiers sont exécutés avec succès)
     
     1) une creation de compte login a2
     2) une connexion de a2
     3) une creation de favori libelle f1 => doit marcher (car deux abonnes differents peuvent choisir le meme libelle favori)
     4) une deconnexion de a2
     
     A vous d'imaginer tous les tests possibles pour garantir le
     fonctionnement correct du code serveur
     */

     try {
            InitialContext ctx = new InitialContext();
            conversionTaux.session.ConversionTauxNonAbonneItf c1 = 
                (conversionTaux.session.ConversionTauxNonAbonneItf) ctx.lookup("ConversionTauxNonAbonneJNDI");
            
            conversionTaux.session.ConversionTauxAbonneItf c2 = 
                (conversionTaux.session.ConversionTauxAbonneItf) ctx.lookup("ConversionTauxAbonneJNDI");

            while(true) {

                Scanner s = new Scanner(System.in);
                
                String welcomeText = " Voulez-vous : \n" +
                                        "1 - Creer un compte \n" +
                                        "2 - Convertir une somme \n" +
                                        "3 - Se connecter \n" + 
                                        "4 - Creer un favori \n" +
                                        "5 - Se deconnecter \n" +
                                        "6 - Convertir une somme Fav \n" +
                                        "7 - Supprimer un favori \n";

                System.out.println(welcomeText);

                int choix = s.nextInt();

                if(choix == 1){
                    Scanner s1 = new Scanner(System.in);
                    System.out.println("Saisir le login");
                    String login = s1.nextLine();

                    System.out.println("Saisir le mot de passe");
                    String passwd = s1.nextLine();

                    System.out.println(c1.creerCompte(login, passwd));
                } 
                else if(choix == 2){
                    Scanner s2 = new Scanner(System.in);
                    System.out.println("Saisir une premiere monnaie");
                    String monnaieA = s2.nextLine();

                    System.out.println("Saisir une deuxieme monnaie");
                    String monnaieB = s2.nextLine();

                    System.out.println("Saisir un montant");
                    double montant = s2.nextInt();
                    
                    System.out.println(c1.convertir(monnaieA, monnaieB, montant));
                } 
                else if(choix == 3){
                    Scanner s3 = new Scanner(System.in);
                    System.out.println("Saisir le login");
                    String login = s3.nextLine();

                    System.out.println("Saisir le mot de passe");
                    String passwd = s3.nextLine();

                    System.out.println(c2.connecter(login, passwd));
                } 
                else if(choix == 4){
                    Scanner s4 = new Scanner(System.in);

                    System.out.println("Saisir votre favori");
                    String favori = s4.nextLine();

                    System.out.println("Saisir une premiere monnaie");
                    String monnaieA = s4.nextLine();

                    System.out.println("Saisir une deuxieme monnaie");
                    String monnaieB = s4.nextLine();
                    
                    System.out.println(c2.ajouterFavori(favori, monnaieA, monnaieB));
                } 
                else if(choix == 5) {
                    c2.deconnecter();
                    break;
                }
                else if(choix == 6) {
                    Scanner s6 = new Scanner(System.in);

                    System.out.println("Saisir votre favori");
                    String favori = s6.nextLine();

                    System.out.println("Saisir votre montant");
                    double montant = s6.nextDouble();

                    System.out.println(c2.convertir(favori, montant));
                }
                else if(choix == 7) { // erreur contrainte table Abonne_Favori
                    Scanner s7 = new Scanner(System.in);

                    System.out.println("Saisir votre favori");
                    String favori = s7.nextLine();

                    System.out.println(c2.supprimerFavori(favori));

                }
                else{
                    System.out.println("Erreur de saisie ! Veuillez taper un chiffre valide");
                }
            }
	
    }
    catch (Exception e) {
            System.err.println("erreur dans le lookup");
            e.printStackTrace();
        }
}
}
