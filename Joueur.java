/**
   La classe Joueur contient éssentiellement les méthodes permettant au joueur de saisir les actions qu'il souhaite
   pour agir sur le plateau
*/

import java.util.*;

public class Joueur{
    private final String nom; //Pour ne pas qu'un joueur puisse changer de nom durant le jeu
    private int nbPartiesGagnees;
    private int nbPartiesPerdues;
    
    public Joueur(String nom){ //Le main demandera le nom du joueur et appelera le constructeur avec le nom entrée
	this.nom = nom;
	this.nbPartiesGagnees = 0;
	this.nbPartiesPerdues = 0;
    }

    /***************************************************************************************************************************************/
    /**
       Les classes getNom, getNbPartiesGagnees et getNbPartiesPerdues permettent de récupérer le nom, le nb de parties gagnées
       et le nb de parties perdues du joueur pour d'autres méthodes d'autres classes puisqu'ils sont déclarés privés
    */
    
    public String getNom(){
	return this.nom;
    }
    
    public int getNbPartiesGagnees(){
	return this.nbPartiesGagnees;
    }
    
    public int getNbPartiesPerdues(){
	return this.nbPartiesPerdues;
    }

    /***************************************************************************************************************************************/
    /**
       ouiNon vérifie si le joueur a répondu oui ou non à une question qui lui est demandée
       @return la réponse du joueur
    */
    public boolean ouiNon(){
	System.out.println("-1- Oui                        -0- Non");
	int rep = nombreChoisi();
	if(rep == 1)
	    return true;
	return false;
    }

    /***************************************************************************************************************************************/
    /**
       Cette fonction permet au joueur de choisir un nombre
       @return le nombre que le joueur a choisi
    */
    public int nombreChoisi(){
	Scanner sc = new Scanner(System.in);
	int rep = sc.nextInt();
	return rep;
    }

    /***************************************************************************************************************************************/
    /**
       Cette méthode permet au joueur de choisir une action à réaliser puis les coordonnées de la case sur laquelle exécuter l'action
       @return un tableau de 3 cases : la premiere case est l'action à réaliser, la seconde la coordonnées verticale de la case
       et la troisieme la coordonnée horizontale
    */
    public int[] actionChoisie(){
	
	int[] res = new int[3];
	
	System.out.println("       Choisissez une action à réaliser : ");
	System.out.println("-0- ENLEVER UN DRAPEAU     -1- REVELER     -2- POSER UN DRAPEAU");
	res[0] = nombreChoisi();	
	System.out.print("Coordonnée verticale de la case : ");
	res[1] = nombreChoisi();
	System.out.print("Coordonnée horizontale de la case : ");
	res[2] = nombreChoisi();
	
	return res;
	
    }
    
}
