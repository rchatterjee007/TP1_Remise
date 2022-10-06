package TP1;

import TP1.Constantes.Sorte;
/*
 * Offre les fonctions principales à la gestion du tableau de cartes du jeu
 * 
 * @author Radhika Chatterjee 
 * @author Simon Pitre-Lamas
 * 
 * Les m�thodes publiques :
 * 
 * 							
 */
public class UtilitaireTableauCartes {
	// CONSTANTS qui determine le nombre de fois que les cartes seront brasses.
	final static int NOMBRE_MAX=5;
	final static int NOMBRE_MIN=1;


	public UtilitaireTableauCartes() {}

	public static Carte[] mélangerParPositionAleatoire(Carte[] cartes) {
		Carte[] cartesTemporaire = new Carte[Constantes.NB_CARTES];
		int chiffreAleatoire;
		for(int i=0; i<cartes.length; i++) {

			do {
				chiffreAleatoire = UtilitaireFonction.alea
						(0, Constantes.NB_CARTES-1);

			}while(cartesTemporaire[chiffreAleatoire] != null);

			cartesTemporaire[chiffreAleatoire] = cartes[i];
		}
		return cartesTemporaire;
	}

	public static Carte[] copieDuJeu(Carte[] jeuneuf) {
		Carte[] cartesCopie = new Carte[jeuneuf.length];
		for(int i = 0; i<jeuneuf.length; i++) {
			cartesCopie[i] = jeuneuf[i];
		}
		return cartesCopie;
	}


	public static Boolean toutesLesCartesSontTournee(Carte[] cartes) {
		Boolean toutesLesCartesSontTournee = true;
		int indexCarte = 0;
		while(indexCarte < cartes.length && 
				toutesLesCartesSontTournee != false) {
			if(!cartes[indexCarte].visible) {
				toutesLesCartesSontTournee = false;
				break;
			}
			indexCarte++;
		}
		return toutesLesCartesSontTournee;
	}

	public static Boolean deuxCartesSeSuivent(Carte carte1, Carte carte2) {
		Boolean carteSeSuivent = true;
		int numeroCarte1 = carte1.numero;
		int numeroCarte2 = carte2.numero;
		Sorte sorteCarte1 = carte1.couleur;
		Sorte sorteCarte2 = carte2.couleur;

		if(numeroCarte1 == numeroCarte2++ && sorteCarte1 == sorteCarte2) {
			return carteSeSuivent;
		}else {
			return false;
		}
	}

	public static Carte modifierVisibiliteCarte(Carte carte) {
		if(carte.visible == false) {
			carte.visible = true;
		}else {
			carte.visible = false;
		}
		return carte;
	}

	public static void afficherCartes(Carte[] cartes, GrilleGui gui) {
		Carte[][] listeCarte2D = transformerCarteListeEn2DPourGui(cartes, gui);
		UtilitaireGrilleGui.afficherCartes(listeCarte2D, gui);
	}

	public static Carte[] rendreCartesNonVisible(Carte[] cartes) {
		for(int i=0; i<cartes.length; i++) {
			cartes[i].visible = false;
		}
		return cartes;
	}

	public static Carte[] rendreCartesVisble(Carte[] cartes) {
		for(int i=0; i<cartes.length; i++) {
			cartes[i].visible = true;
		}
		return cartes;
	}

	private static Carte[][] transformerCarteListeEn2DPourGui
	(Carte[] cartes, GrilleGui gui) {
		int positionListeCarte = 0;
		Carte[][] listeCartes2D = new Carte
				[gui.getNbLignes()][gui.getNbColonnes()];
		for(int y=0; y< gui.getNbLignes(); y++) {
			for(int i=0; i< gui.getNbColonnes(); i++) {
				Carte carte = cartes[positionListeCarte];
				listeCartes2D[y][i] = carte;
				positionListeCarte++;
			}
		}
		return listeCartes2D;
	}



	/**
	 *Méthode qui retourne un sous-tableau de cartes qui sont situé entre 
	 *l'intervalle fourni du tableau de Cartes (52 cartes) 
	 * @param tabCarteOriginale= Tableau de 52 cartes du jeu 
	 * @param indiceDebut & @param indiceFin = les indices de l'intervalle de 
	 * cartes à mettre dans le sousTab
	 * @return sousTab= Le tableau avec l'intervalle 
	 * de Cartes du tableauCarteOriginale
	 * */
	public static Carte[] sousTableauDeCartes(Carte [] tabCarteOriginale, 
			int indiceDebut, int indiceFin){

		/*
		 * Déclarer & initialiser mon tableau de retour 
		 * indiceFin - indiceDebut + 1= nbr d'élément du soustab
		 */
		Carte[] sousTab = new Carte[indiceFin - indiceDebut + 1];
		int j = 0;
		for(int i  = indiceDebut; i <= indiceFin; i++){
			sousTab[j] = tabCarteOriginale[i];
			j++;                                              
		}
		return sousTab;
	}


	/**
	 * Méthode principale qui gère le mélange par brassage
	 * 
	 * Trois étapes de cette fonction: 
	 * 									- Mélanger
	 * 									- Séparer
	 * 									- Fusionner
	 * 
	 * @param cartes = tableau de cartes du jeu (52 cartes)
	 * @return un tableau de cartes mélangés
	 * */
	public static Carte[] melangerParBrassage(Carte[] cartes){	

		//Générer un nombre aléatoire de fois que vous répéterez les étapes.
		int nbrAleatoire=UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);


		//Déclaration et initialisation
		Carte[]nouvTabCarte=new Carte[cartes.length];

		//Répéter les trois tâches nbrAleatoire de fois 
		int i=0;	
		while (i!=nbrAleatoire){

			//Mélanger 
			melanger(cartes);//melanger les cartes


			//Separer le paquet en  2 
			//La séparation résulte deux tableaux de taille différents
			int indiceTab1=UtilitaireFonction.alea(20, 30);
			Carte [] tab1= sousTableauDeCartes(cartes,0,indiceTab1); 
			Carte [] tab2= sousTableauDeCartes(cartes,indiceTab1+1,
					cartes.length-1); 

			//Fusionner pour avoir un tableau de Cartes mélangés
			nouvTabCarte=fusionner(cartes.length, tab1, tab2);
			i++;
		}
		return nouvTabCarte;
	}



	/**
	 * Méthode qui avance vers prend un cartes de la fin et la met au début en
	 * déplacant vers la droite les cartes du tableau 
	 * @param tabDeCartes = Tableau de cartes à effectuer le changement
	 * */
	public static void deplacerCartes(Carte [] tabDeCartes) {
		Carte carteADéplacerVersLeDebut = tabDeCartes[tabDeCartes.length-1];
		for (int i=tabDeCartes.length-1;i>0;i--) {
			tabDeCartes[i] = tabDeCartes[i-1];
		}
		tabDeCartes[0] = carteADéplacerVersLeDebut;
	}


	/**
	 * Méthode qui mélange les cartes du tableau de cartes
	 * Générer un nombre aléatoire de fois que vous déplacer des cartes.
	 * Pour chaque fois ;
	 * Générer un nombre de cartes à déplacer (entre 3 et 10 par exemple)
	 * Déplacer ce nombre de cartes de la fin vers le début.
	 * @param tableauCartes = tableau de cartes qui devra être mélangé
	 * */
	public static void melanger(Carte [] tableauCartes) {
		int nbrFoisMelanger= UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		int i=0;
		while (i!=nbrFoisMelanger){
			int nbrCartesDeplacer=UtilitaireFonction.alea(3, 10);
			for(int j=0;j<nbrCartesDeplacer;j++) {
				deplacerCartes(tab);
			}
			i++;
		}	
	}

	/**
	 * Fusionne les cartes des deux tableaux de Cartes en un seul tableau 
	 * @param tailleTabCartesOriginale = taille du tableau de Cartes du jeu (52)
	 * @param sousTabCartes1 = Sous-Tableau de Cartes 1
	 * @param sousTabCartes2 = Sous Tableau de Cartes 2
	 * 
	 * */
	public static Carte[]  fusionner(int tailleTabCartesOriginale, 
			Carte [] sousTabCartes1, Carte [] sousTabCartes2) {
		Carte[] tabCartesAfficher= new Carte [tailleTabCartesOriginale];


		int iterateurSousTab1=0;//iterateur sous-tableau1 de Cartes
		int iterateurSousTab2=0;//iterateur sous-tableau2 de Cartes
		int iteratuerTabCartes=0;
		while(iteratuerTabCartes<tabCartesAfficher.length){
			int nbrAleatoire=UtilitaireFonction.alea(0,1);


			//Si l’alternance est paire et que l’itérateur du 1ier 
			//tableau est plus petit que taille du 1ier tableau
			if (nbrAleatoire%2==0&&iterateurSousTab1<sousTabCartes1.length){
				tabCartesAfficher[iteratuerTabCartes]=
						sousTabCartes1[iterateurSousTab1];
				iterateurSousTab1++;
				iteratuerTabCartes++;
			}

			//(alternance impaire) si l’itérateur du 2ième tableau 
			//est plus petit que la taille du 2ième tableau,
			if(nbrAleatoire%2==1&&iterateurSousTab2<sousTabCartes2.length){
				tabCartesAfficher[iteratuerTabCartes]=
						sousTabCartes2[iterateurSousTab2];
				iterateurSousTab2++;
				iteratuerTabCartes++;
			}

		}
		return tabCartesAfficher;
	}



	/**
	 * Méthode principale qui gère le brassageParPaquets 
	 * @param tabCartesOriginale = tableau de paquets de cartes
	 * @return un tableau de cartes mélangés
	 * 
	 * */
	public static Carte[] brasserParPaquet(Carte []tabCartesOriginale) {

		//Générer le nombre de paquets au hasard.
		int nbrPaquetAlea= UtilitaireFonction.alea(6, 8);


		//Declarer et initialiser chaque indice 
		//avec un tab vide pour pas avoir de null dans cartes2D
		Carte [][] tabDePaquetsCartes = new Carte[nbrPaquetAlea+1][];
		for(int i=0;i<tabDePaquetsCartes.length;i++) {
			Carte[] arr = {};
			tabDePaquetsCartes[i]=arr;
		}


		//Mettre le jeu de carte à distribuer dans la dernière case.
		tabDePaquetsCartes[tabDePaquetsCartes.length-1]=tabCartesOriginale;

		//Distribuer le dernier paquet sur les autres
		tabDePaquetsCartes=distribuerLesCartesEnPaquets(tabDePaquetsCartes);

		//Tant qu’il reste plus d’un paquet 
		while(PaquetsSontTousVides(tabDePaquetsCartes)!=true) {
			//Choisir un paquet au hasard (case non nulle) et 
			//le mettre dans la dernière case [nbPaquet + 1].
			int nbrPaquet=UtilitaireFonction.alea(0,nbrPaquetAlea-1 );

			//Si le paquet n'est pas null et qu<aucun paquet a 52 cartes
			if(tabDePaquetsCartes[nbrPaquet]!=null&&
					siLePaquetEstComplet(tabDePaquetsCartes)==false ) {
				tabDePaquetsCartes[tabDePaquetsCartes.length-1]=null;
				tabDePaquetsCartes=
						remplacerCartesIndex(tabDePaquetsCartes,nbrPaquet);

				//Distribuer le dernier paquet sur les autres.
				tabDePaquetsCartes=
						distribuerLesCartesEnPaquets(tabDePaquetsCartes);				
			}
		}
		//Retourne le dernier paquet 
		return retournerCartesMelange(tabDePaquetsCartes);
	}

	/**
	 * Méthode qui retourne le paquet de Cartes du tableau 
	 * contenant les 52 cartes et place au dernier indice du tableau de paquet
	 * @param tabDePaquetsCartes = tableau de paquets de cartes
	 * @return le tableau de Cartes contenant les 52 cartes
	 * */
	public static Carte[] retournerCartesMelange
	(Carte[][] tabDePaquetsCartes) {
		Carte [] tabCartesJeu= new Carte [Constantes.NB_CARTES];
		for (int i=0; i<tabDePaquetsCartes.length-1; i++) {
			if (tabDePaquetsCartes[i] != null&&
					tabDePaquetsCartes[i].length==52) {
				tabCartesJeu=tabDePaquetsCartes[i];
				tabDePaquetsCartes[tabDePaquetsCartes.length-1]=tabCartesJeu;
				break;
			}
		}
		return tabDePaquetsCartes[tabDePaquetsCartes.length-1];
	}


	/**
	 * Méthode qui vérifie si dans le tableau de cartes, 
	 * un paquet contient tous les 52 cartes
	 * @param tabDePaquetsCartes = tableau de paquets de cartes
	 * @return vrai si un paquet contient 52 cartes 
	 * @return faux si aucun paquet contient 52 cartes
	 * */
	public static boolean siLePaquetEstComplet(Carte[][] tabDePaquetsCartes)
	{
		boolean unPaquetEstComplet=false;
		for (int i=0; i<tabDePaquetsCartes.length-1; i++) {
			if (tabDePaquetsCartes[i] != null&&
					tabDePaquetsCartes[i].length==Constantes.NB_CARTES) {
				unPaquetEstComplet=true;
				break;
			}
		}
		return unPaquetEstComplet;
	}


	/**
	 * Méthode qui met le paquet de cartes à distribuer dans le dernier paquet
	 * et marque l'index du paquet à null pour fermer ce paquet
	 * @param tabDePaquetsCartes = tableau de paquets de cartes 
	 * @param cartesADistribuer= tableau de Cartes à distribuer
	 * @return le tableau de paquets de cartes
	 * */
	public static Carte[][] remplacerCartesIndex(Carte[][] tabDePaquetsCartes,
			int indexPaquetAVider){
		Carte[]  cartesADistribuer= tabDePaquetsCartes[indexPaquetAVider];
		tabDePaquetsCartes[tabDePaquetsCartes.length-1]= cartesADistribuer;
		tabDePaquetsCartes[indexPaquetAVider]=null;
		return tabDePaquetsCartes;
	}

	/**
	 * Méthode qui vérifie si reste des paquets pour distribuer les cartes
	 * 
	 * @param tabDePaquetsCartes = tableau de paquets de cartes 
	 * @return faux si tous les paquets (excluant le dernier) 
	 * ne sont pas null et n'ont pas 52 cartes 
	 * @return vrai si les paquets (excluant le dernier)
	 * sont tous null et qu'il paquet a 52 cartes
	 * */
	public static boolean PaquetsSontTousVides(Carte[][] tabDePaquetsCartes) {
		boolean vide = true;
		for (int i=0; i<tabDePaquetsCartes.length-1; i++) {
			if (tabDePaquetsCartes[i] != null&&
					tabDePaquetsCartes[i].length!=Constantes.NB_CARTES) {
				vide = false;
				break;
			}

		}
		return vide;
	}


	/**
	 * Méthode qui subdivise le jeu de cartes en plusieurs paquets(entre 6 & 8).
	 * Distribuer ses cartes une à une sur les autres paquets.  
	 * On recommence jusqu’à ce qu’il ne reste qu’un paquet   
	 * 
	 * @param tabDePaquetsCartes= tableau qui contient les paquets de cartes
	 * @return le tableau qui contient les paquets de cartes
	 * */
	public static Carte[][] distribuerLesCartesEnPaquets
	(Carte [][]tabDePaquetsCartes) {
		int iterateurCarte=0;

		//Les cartes à distribuer qui se trouvent dans le dernier paquet
		Carte [] TabDeCartes= tabDePaquetsCartes[tabDePaquetsCartes.length-1];		


		// Tant qu'il y a des cartes dans le paquet
		while(iterateurCarte<TabDeCartes.length) {	

			// Distribuer à chaque paquet
			for(int indexPaquet=0;indexPaquet<tabDePaquetsCartes.length-1;
					indexPaquet++) { 

				// Si tous les cartes ne sont pas distribués et que le paquet
				//à distribuer n'est pas null/vide
				if(iterateurCarte<TabDeCartes.length&&
						tabDePaquetsCartes[indexPaquet]!=null) {

					//Ajouter cet carte dans le ce paquet
					Carte [] c= {TabDeCartes[iterateurCarte]}; 
					int taille=tabDePaquetsCartes[indexPaquet].length+c.length;
					tabDePaquetsCartes[indexPaquet]=
							fusionner(taille,tabDePaquetsCartes[indexPaquet],c);


					// incrementer lorsque la carte est distribué
					iterateurCarte++;
					// incrementer pour pas depasser le nombre 
					//de carte dans le dernier paquet de la case
				}
			}
		}

		return tabDePaquetsCartes;
	}

}
