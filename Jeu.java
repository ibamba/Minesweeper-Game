/**
   La classe jeu fait intéragir le joueur et le plateau
*/

public class Jeu{
    
    private Joueur leJoueur;
    private Plateau lePlateau;
    
    public Jeu(Joueur leJoueur, Plateau lePlateau){
	this.leJoueur = leJoueur;
	this.lePlateau = lePlateau;
    }
    
    /**************************************************************************************************************************************/
    /**
       La méthode jouer est la boucle principale du jeu. Elle fait appelle aux fonctions qui demandent au joueur de poser
       une action et la réalisent jusqu'à ce que le jeu fini. Elle vérifie ensuite si le jeu s'est soldé par une victoire
       ou défaite du joueur
       @param un joueur et un plateau
       @return vrai si le joueur a gagné, faux sinon
    */
    public boolean jouer(Joueur leJoueur, Plateau lePlateau){
	
	int [] action = new int [3];
	String s = leJoueur.getNom();
	System.out.println("Bienvenue " +s+ ". Vous avez créé votre partie de Démineur. Vous pouvez maintenant commencer votre jeu. BONNE CHANCE !!!");
	lePlateau.affichage();
	do{
	    // Sécurité : vérification de l'exactitude des données sasies
	    do
		action = leJoueur.actionChoisie();
	    while((action[0] < 0 || action[0] > 2) || (action[1] > lePlateau.hauteur) || (action[2] > lePlateau.largeur));
	    lePlateau.agir(action);
	    lePlateau.affichage();
	}
	while(!lePlateau.jeuFini(action[1], action[2]));
	
	if(lePlateau.jeuGagne())
	    return true;
	return false;
    }
    
}
