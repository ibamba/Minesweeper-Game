/** NB : Dans la classe Plateau, j'ai implémenté l'appel récursif pour l'affichage des mines adjacentes(c.f fonction revelerMinesAdja. Mais problème, lors de l'exécution, c une erreur qui me dit qu'il y a débordement dans ma pile d'appel. Du coup, je ne sais pas si cela est dû à la faible mémoire de mon ordinateur ou c'est réellement une mauvaise implémentation dans mon code. Pour ne pas prendre de risque, j'ai mis le code comme un commentaire. Je vous demande, svp, de le vérifier en l'enlevant en commentaire et l'exécutant
 */ 


/**
   Cette classe lance le jeu, crée un joueur, un plateau et un jeu en demandant au joueur les parametres nécessaires
   La fonction main demande en boucle au joueur s'il veut jouer, puis les parametres si oui et lance la partie
*/   

import java.util.*;

public class Lanceur{
    
    public static void main(String [] args){
	
	int largeur = 0, hauteur = 0, nbMines = 0;
	
	System.out.print("\n\n\n Entrez votre nom : ");
	Scanner sc = new Scanner(System.in);
	String nom = sc.next();
	Joueur leJoueur = new Joueur(nom);
	int statut1 = leJoueur.getNbPartiesGagnees(), statut2 = leJoueur.getNbPartiesPerdues(); //Le nombre de victoire et de défaite du joueur
	System.out.print("Bienvenue " + nom + " dans le jeu de Démineur\n" +
			 nom + ", Voulez-vous commencer une partie ?\n");
	boolean jououns = leJoueur.ouiNon();
	if(!jououns){
	    System.out.println("Aurevoir\n");
	    System.exit(0); //Pour sortir du programme proprement
	}
	
	boolean tmp = true;
	int i = 0;
	
	do{
	    
	    if(i != 0){
		System.out.println("Voulez-vous changer les paramètres précédents du jeu ?");
		tmp = leJoueur.ouiNon();
	    }//Pour ne pas afficher ce message au premier lancement du jeu, on fait un teste qui est faux seulement  au premier lancement
	    i++;
	    //On exécutera cette condition au premier lancement du programme à coup sûr
	    if(tmp){
		System.out.println("                     Les paramètres du jeu");
		System.out.print("Entrez la hauteur du démineur : ");
		hauteur = leJoueur.nombreChoisi();
		System.out.print("Entrez la largeur du démineur : ");
		largeur = leJoueur.nombreChoisi();
		System.out.print("Entrez le nbre de mines que vous souhaiter avoir dans la partie : ");
		nbMines = leJoueur.nombreChoisi();
	    }
	    
	    Plateau lePlateau = new Plateau(hauteur, largeur, nbMines);
	    Jeu leJeu = new Jeu(leJoueur, lePlateau);

	    //2 sécondes d'attente
	    
	    boolean resultat = leJeu.jouer(leJoueur, lePlateau);
	    if(resultat){
		statut1 ++;
		System.out.println("\n FELICITATION " +nom+ " !!! Vous avez gagné\n");
	    }
	    else{
		System.out.println("\n Vous avez perdu " +nom);
		statut2 ++;
	    }
	    System.out.println(nom + " : vous avez " +statut1+ " victoire(s) et " +statut2+ " défaites");
	    System.out.println("Voulez-vous refaire une nouvelle partie ?");
	    jououns = leJoueur.ouiNon();
	}
	while(jououns);
	
    }
    
}
