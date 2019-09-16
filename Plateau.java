/*
   N.B : Pour vous aider à comprendre le fonctionnement du jeu, j'ai implémenté une affichage du tableau des mines qui est en commentaire juste en bas de l'affichage normale. Vous pouvez enlever les barres de commentaire pour pouvoir visualiser sur le terminal où sont situées les mines et ainsi tester certains paramètres du jeu 
*/
/**
   La classe plateau contient des tableaux représentants l'état du jeu ainsi que des méthodes permettant d'agir sur le plateau
*/

public class Plateau{
    
    public final int hauteur, largeur, nbMines;
    
    private final boolean[][] mines; //Indique ou sont les mines sur le plateau
    private final int[][] etats; //Indique dans quelle état est chaque case(cachée, révélée, avec ou sans drapeau)
    private final int [][] adja; //Indique le nbre de mines adjascentes à chaque case
    public final static int DRAPEAU = 2;
    public final static int REVELEE = 1;
    public final static int CACHESANSDRAPEAU = 0;
    
    
    public Plateau(int hauteur, int largeur, int nbMines){
	this.hauteur = hauteur;
	this.largeur = largeur;
	this.nbMines = nbMines;
	mines = new boolean[hauteur][largeur];
	etats = new int[hauteur][largeur]; //Les états sont initialisés à 0
	adja = new int[hauteur][largeur];
	
	//Initialisation des mines
        for(int i = 0; i < hauteur; i++){
	    for(int j = 0; j < largeur; j++) {
		if(nbMines > 0){
		    int poserMine = (int) (Math.random()*2);//Pour avoir une même probabilité d'avoir une mine ou pas dans une case
		    if(poserMine >= 1){
			mines[i][j] = true;
			nbMines --;
		    }
		    else
			mines[i][j] = false;
		}
	    }
	}
	
	//Calcul des mines adjascentes
	for(int i = 0; i < hauteur; i++){
	    for(int j = 0; j < largeur; j++)
		adja[i][j] = compteurMinesAdja(i, j);
	}
	
    }

    /**************************************************************************************************************************************/
    /**
       Cette méthode compte le nombre de mine qu'il y a dans les cases adjacentes à la case choisie
       @param y est la coordonnée verticale de la case
       @param x est la coordonnée horizontale de la case
       @return le nombre de mines dans les cases adjascentes
    */
    public int compteurMinesAdja(int y, int x){
	
	int compteur = 0;
	
	//Pour les cases non situées aux extrémités, on ne fera pas certaines actions pour ne pas sortir du tableau
	if((x + 1) < largeur){
	    if(mines[y][x + 1])
		compteur ++;
	}
	if((x - 1) >= 0){
	    if(mines[y][x - 1])
		compteur ++;
	}
	if((y + 1) < hauteur){
	    if(mines[y + 1][x])
		compteur ++;
	}
	if((y - 1) >= 0){
	    if(mines[y - 1][x])
		compteur ++;
	}
	if((y - 1) >= 0 && (x - 1) >= 0){
	    if(mines[y - 1][x - 1])
		compteur ++;
	}
	if((y + 1) < hauteur && (x - 1) >= 0){
	    if(mines[y + 1][x - 1])
		compteur ++;
	}
	if((y - 1) >= 0 && (x + 1) < largeur){
	    if(mines[y - 1][x + 1])
		compteur ++;
	}
	if((y + 1) < hauteur && (x + 1) < largeur){
	    if(mines[y + 1][x + 1])
		compteur ++;
	}
	
	return compteur;
	
    }

    /******************************************************************************************************************************************************/
      /**
       La méthode reveler revele les cases adjacentes à la case sur laquelle elle est appelée tout en s'assurant qu'elle
       ne sort pas du plateau
       @param les coordonnées de la case revélée
      */
    
    public void reveler(int y, int x){
	
	if((x + 1) < largeur)
	    etats[y][x + 1] =  REVELEE;
	if((x - 1) > 0)
	    etats[y][x - 1] = REVELEE;
	if((y + 1) < hauteur)
	    etats[y + 1][x] = REVELEE;
	if((y - 1) > 0)
	    etats[y - 1][x] = REVELEE;
	if((y - 1) > 0 && (x - 1) > 0)
	    etats[y - 1][x - 1] = REVELEE;
	if((y + 1) < hauteur && (x - 1) > 0)
	    etats[y + 1][x - 1] = REVELEE;
	if((y - 1) > 0 && (x + 1) < largeur)
	    etats[y - 1][ x + 1] = REVELEE;
	if((y + 1) < hauteur && (x + 1) < largeur)
	    etats[y + 1][x + 1] = REVELEE;
	
    }

    /******************************************************************************************************************************************************/
    /**
       la méthode revelerMinesAdja revele les cases adjacentes à la case reveler si celles ci ne contiennent pas de mines
       Puis elle s'appelle récursivement sur ces dernieres cases revelees. Cette méthode s'assure qu'on n'est pas sorti
       du plateau avant de s'appeler recursivement
       @param les coordonnées de la case à revélée
    */
    public void revelerMinesAdja(int y, int x){
	if(adja[y][x] == 0 && !mines[y][x]){
	    reveler(y, x);
	    /* if((x + 1) < largeur)
		revelerMinesAdja(y, x + 1);
	    if((x - 1) > 0)
		revelerMinesAdja(y, x - 1);
	    if((y + 1) < hauteur)
		revelerMinesAdja(y + 1, x);
	    if((y - 1) > 0)
		revelerMinesAdja(y - 1, x);
	    if((y - 1) > 0 && (x - 1) > 0)
		revelerMinesAdja(y - 1, x - 1);
	    if((y + 1) < hauteur && (x - 1) > 0)
		revelerMinesAdja(y + 1, x - 1);
	    if((y - 1) > 0 && (x + 1) < largeur)
		revelerMinesAdja(y - 1, x + 1);
	    if((y + 1) < hauteur && (x + 1) < largeur)
		revelerMinesAdja(y + 1, x + 1);
	    */
	}
    }
 
    /**************************************************************************************************************************************/
    /**
       Cette méthode exécute les actions demandées par le joueur : reveler une case, placer un drapeau ou enlever un drapeau
       tout en s'assurant que l'action est demandée est bien réalisable dans l'état du jeu
       @param un tableau qui représente l'action à réaliser et les coordonnées de la case à laquelle la réaliser
       @return vraie si l'action a pu être réalisée et faux sinon
    */
    public boolean agir(int[] action){
	
	if(etats[action[1]][action[2]] == REVELEE){
	    System.out.println("\n Cette case est déjà révélée");
	    return false;
	}
	
	else{
	    
	    switch(action[0]){
	    case 0 : //Pour enlever un drapeau
		if(etats[action[1]][action[2]] == DRAPEAU){ //On s'assure qu'il y est bien un drapeau dans la case
		    etats[action[1]][action[2]] = CACHESANSDRAPEAU;
		    break;
		}
		else{
		    System.out.println("\n Vous n'avez pas poser de drapeau dans cette case.");
		    return false;
		}
	    case 2 : //Pour poser un drapeau
		if(etats[action[1]][action[2]] != DRAPEAU){
		    etats[action[1]][action[2]] = DRAPEAU;
		    break;
		}
		else{
		    System.out.println("\n Vous avez déjà poser un drapeau à cette case");
		    return false;
		}
	    case 1 : //Pour reveler la case
		if(etats[action[1]][action[2]] == REVELEE){
		    System.out.println("\n Vous avez déjà revélé cette case");
		    return false;
		}
		else if(etats[action[1]][action[2]] == DRAPEAU){
		    System.out.println("\n Vous ne pouvez pas reveler cette case car vous la soupçonnez. Enlevez d'abord le drapeau.");
		    return false;
		}
		else{
		    etats[action[1]][action[2]] = REVELEE;
		    revelerMinesAdja(action[1], action[2]);
		    break;
		}
		
	    }
	    
	    return true;
	}
	
    }
    
    /***************************************************************************************************************************************/
    /**
       Cette fonction testera si le jeu est fini après chaque coup du joueur
       @param y et x sont les coordonnées de la case
       @return vrai si le jeu est fini, faux sinon
    */
    public boolean jeuFini(int y, int x){
	
	if(jeuPerdu(y, x) || jeuGagne())
	    return true;
	return false;
	
    }

    /**************************************************************************************************************************************/
    /**
       Cette fonction vérifie si le joueur a perdu après chaque coup joué
       @param y et x les coordonnées de la case du dernier coup
       @return vrai si le joueur a perdu et faux sinon
    */
    public boolean jeuPerdu(int y, int x){
	
	if(mines[y][x] && (etats[y][x] == REVELEE))
	    return true;
    	return false;
	
    }

    /***************************************************************************************************************************************/
    /**
       Cette méthode vérifie si le jeu s'est soldé par une victoire
       @return vrai si oui, faux si non
    */
    public boolean jeuGagne(){
	
	for(int i = 0; i < hauteur; i++){
	    for(int j = 0; j < largeur; j++){
		if(!mines[i][j] && etats[i][j] != REVELEE)
		    return false;
	    }
	}
	
	return true;
    }

    /***************************************************************************************************************************************/
    /** 
	affichage s'occupe d'afficher l'état du démineur après chaque coup du joueur
    */
    public void affichage(){
	
	System.out.println("\n\n\n ");
	
	//Numérotation des cases du plateau
	System.out.print("   ");
	for(int i = 0; i < largeur; i++)
	    System.out.print(i + " "); 
	System.out.println();
	System.out.print("   ");
	for(int i = 0; i < largeur; i++)
	    System.out.print("_ "); 
	System.out.println();
	
	
	//Affichage du plateau
	for(int i = 0; i < hauteur; i++){
	    System.out.print(i + "| ");
	    for(int j = 0; j < largeur; j++){
		if(etats[i][j] == 0)
		    System.out.print("."); // Les cases vides seront représentées par un point et les drapeaux par un ?
		if(etats[i][j] == 2)
		    System.out.print("?");
		if(etats[i][j] == 1){
		    if(mines[i][j])
			System.out.print("*");// Les mines seront représentées par un étoile(*)
		    else{
			int tmp = adja[i][j];
			System.out.print(tmp);
		    }
		}
		System.out.print(" ");
	    }
	    System.out.println();
	}

	System.out.println("\n\n\n");
	/*****************************************************************************************************************************/
	/***REVELATION DE L'EMPLACEMENT DES MINES***/
	/*
	  System.out.println("\n\n\n ");
	  
	  //Numérotation des cases du plateau
	  System.out.print("   ");
	  for(int i = 0; i < largeur; i++)
	  System.out.print(i + " "); 
	  System.out.println();
	  
	  //Affichage du plateau
	  for(int i = 0; i < hauteur; i++){
	  System.out.print(i + "| ");
	  for(int j = 0; j < largeur; j++){
	  if(mines[i][j])
	  System.out.print("*"); // Les cases vides seront représentées par un point et les drapeaux par un ?
	  else
	  System.out.print(".");
	  System.out.print(" ");
	  }
	  System.out.println();
	  }
	  System.out.println("\n\n\n");
	*/
	
    }
    
}
