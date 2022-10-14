package TP1;

import javax.swing.JOptionPane;


/**
 * Gère les étapes du jeu : l'initialisation d'une partie, 
 * l'affichage des messages, l'affichage des cartes et leur visibilité
 * et gère les tours du jeu.
 * @author Simon Pitre Lamas, Radhika Chatterjee
 */
public class UtilitaireJeu {

	//Tableau qui contient tous les cartes cliqués
	static Carte[] ancienCarte;



	/**
	 * Initialise le jeu avant d'effectuer un tour.
	 * Mélange les cartes, affiche ces cartes, modifie la 
	 * visibilité de ces cartes.
	 * @param jeu Le jeu de base.
	 * @param carteAfficher Les cartes à afficher.
	 * @param gui Le gui du jeu (Tableau de cartes 2d et menu).
	 * @param etatjeu  L'état du jeu.
	 */
	public static void initialiserJeu(Carte[] jeu, Carte[] carteAfficher, 
			GrilleGui gui, EtatJeu etatjeu) {

		//Melanger les cartes.
		carteAfficher=brasserCartesSelonChoix(carteAfficher);

		//Afficher les cartes du jeu neuf.
		UtilitaireTableauCartes.afficherCartes(jeu, gui);

		//Message à l'utilisateur.
		String message = 
				"Vous avez quelques secondes pour mémoriser les cartes";	
		afficherMessage(message);

		//Copier le jeu de cartes à afficher dans 
		//un tableau de cartes temporaire.
		Carte[] cartesTemporaire = new Carte[carteAfficher.length];
		cartesTemporaire = carteAfficher.clone();

		//Modifier visibilité des cartes à afficher.
		carteAfficher = 
				UtilitaireTableauCartes.rendreCartesNonVisible(carteAfficher);

		//Rendre les cartes temporaire visible(carteAfficher 
		//et cartesTemporaires 
		//ont la même addresse).
		cartesTemporaire = 
				UtilitaireTableauCartes.rendreCartesVisble(cartesTemporaire);

		//Afficher les cartes.
		UtilitaireTableauCartes.afficherCartes(cartesTemporaire, gui);

		//Faire une pause pour laisser le temps de voir les cartes.
		gui.pause(Constantes.TEMPS_VISIONNEMENT);

		//Modifier la visibilité des cartes temporaire à faux.
		cartesTemporaire =
				UtilitaireTableauCartes.
				rendreCartesNonVisible(cartesTemporaire);

		//Afficher les carte temporaire.
		UtilitaireTableauCartes.afficherCartes(cartesTemporaire, gui);
		etatjeu.tabSequence= new int[Constantes.NB_CARTES];
		ancienCarte= new Carte[Constantes.NB_CARTES];
	}

	/*
	 * Demande à l'utilisateur la méthode de brassage à utiliser.
	 * 
	 */
	private static Carte[] brasserCartesSelonChoix(Carte[] cartes) {

		String[] options = {"Méthode aléatoire",
				"Méthode en paquets",
		"Méthode carte brassée"};	
		String reponse;
		do {
			reponse = (String) JOptionPane.showInputDialog(null, 
					"Sélectionnez la méthode de brassage des cartes", 
					"Méthode de brassage", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options, 
					0);

			// Si l'utilisateur n'a pas annulé
			if(reponse != null) {	
				if(reponse.equals(options[Constantes.METHODE_ALEA])){
					//UtilitaireTableauCartes.methodePaquets(cartes);
					cartes=UtilitaireTableauCartes.
							mélangerParPositionAleatoire(cartes);
				}
				else if(reponse.equals(options[Constantes.METHODE_BRASSER])){
					cartes=UtilitaireTableauCartes.melangerParBrassage(cartes);
				}
				// options[Constantes.METHODE_PAQUETS]
				// (Laisser à la fin si on ajoute des méthodes de brassage).
				else {	
					cartes=UtilitaireTableauCartes.brasserParPaquet(cartes);			
				}						
			}

			// On refuse l'annulation. L'utilisateur doit choisir une méthode.
		}while(reponse == null);
		return cartes;
	}


	/**
	 * Gère chaque tour de la partie en ordonnant les étapes
	 * @param cartes Un tableau de cartes.
	 * @param gui Un gui.
	 * @param stats Les statistiques de la partie.
	 * @param etatJeu L'état du jeu.
	 */
	public static void effectuerUnTour(Carte[] jeu, GrilleGui gui,
			Stats stats, EtatJeu etatJeu) {

		stats.nbEssaieActuel++;

		if(UtilisateurAClic(gui)==true) {

			Coordonnee coordonneeClic=getDernierClicPosition(gui);
			validerClic(coordonneeClic,stats,etatJeu,gui);
			UtilitaireGrilleGui.
			afficherCartes(UtilitaireGrilleGui.listeCarteJeu, gui);
			gererSequence(coordonneeClic,stats,etatJeu,gui);
			UtilitaireGrilleGui.
			afficherCartes(UtilitaireGrilleGui.listeCarteJeu, gui);
		}
	}

	/**
	 * Méthode qui gere les séquences de cartes
	 * 
	 * Si la carte suit la carte précédente, mettre la carte dans le tableau 
	 * de séquence et retient sa position 1D
	 * 
	 * incrémenter le nombre d'essai 
	 * 
	 * @param xy= coordonnee de la carte qui vient d'être cliqué
	 * @param stats= Les statistiques de la partie.
	 * @param etatJeu= L'état du jeu.
	 * @param gui: Gui principale
	 * 
	 * */
	public static void gererSequence(Coordonnee xy,Stats stats, 
			EtatJeu etatJeu, GrilleGui gui) {
		if(etatJeu.longueurSequence==0) {
			etatJeu.longueurSequence++;
		}
		else {
			Carte ancienne=ancienCarte[etatJeu.longueurSequence-1];
			Carte presentementClic= UtilitaireGrilleGui.
					listeCarteJeu[xy.ligne][xy.colonne];
			if(ancienne!=null && presentementClic!=null) {
				if(UtilitaireTableauCartes.
						lesCartesSeSuivent(ancienne,presentementClic)==true) {
					etatJeu.ilYaSequence=true;
					etatJeu.longueurSequence++;
				}else {
					mettreUneCarteInvisible(UtilitaireGrilleGui.
							listeCarteJeu[xy.ligne][xy.colonne]);
					etatJeu.ilYaSequence=false;
					afficherMessage("LA SÉQUENCE EST BRISSÉ");
					ancienCarte[etatJeu.longueurSequence]=null;
				}
			}
		}		
		if(etatJeu.longueurSequence>stats.grandeSequence) {
			stats.grandeSequence=etatJeu.longueurSequence;
		}
	}

	/**
	 * Méthode qui valide si la carte cliqué est visible ou non et retient cette 
	 * carte dans le tableau de cartes cliqué et sa position
	 * 
	 *@param coordonnee: coordonnee de la carte qui vient d'être cliqué
	 *@param stats: Les statistiques de la partie.
	 *@param etatJeu: L'état du jeu.
	 *@param gui: Gui principale
	 * */
	public static void validerClic(Coordonnee coordonnee,Stats stats, 
			EtatJeu etatJeu, GrilleGui gui) {

		if(UtilitaireGrilleGui.listeCarteJeu
				[coordonnee.ligne][coordonnee.colonne].visible==true) {
			afficherMessage("VOUS AVEZ PERDU! LE COUP");
			etatJeu.partieTerminee=true;
		}else {
			etatJeu.tabSequence[etatJeu.longueurSequence]=
					convertirPositionEn2D(gui,coordonnee);
			ancienCarte[etatJeu.longueurSequence]=UtilitaireGrilleGui.
					listeCarteJeu[coordonnee.ligne][coordonnee.colonne];
			mettreUneCarteVisible(UtilitaireGrilleGui.
					listeCarteJeu[coordonnee.ligne][coordonnee.colonne]);
		}
	}

	/**
	 * Méthode qui retourne une coordonne instancié
	 * @param x: ligne de la carte
	 * @param y: colonne de la carte
	 * @return une coordonnee instancié
	 * */
	public static Coordonnee initialiserCoordonnee(int x, int y) {
		Coordonnee xy= new Coordonnee();
		xy.colonne=x;
		xy.ligne=y;
		return xy;
	}

	/**
	 * Procédure qui reçoit appel le module UtilitaireTableauCartes 
	 * pour afficher les carte.
	 * @param cartes Un tableau de cartes.
	 * @param gui Un gui.
	 * @param etatJeu L'état du jeu.
	 */
	public static void montrerLesCartes(Carte[] cartes, 
			GrilleGui gui, EtatJeu etatJeu) {
		if(etatJeu.partieTerminee!=true) {
			afficherMessage("LA PARTIE EST EN COURS..."
					+ " LES CARTES PEUVENT PAS ÊTRE TOURNÉS");
		}
		else{
			Carte[][] copieDesCartes= UtilitaireGrilleGui.listeCarteJeu;	
			mettreTouslesCartesVisible();
			UtilitaireGrilleGui.afficherCartes(UtilitaireGrilleGui.
					listeCarteJeu, gui);
			gui.pause(Constantes.TEMPS_VISIONNEMENT);
			UtilitaireGrilleGui.afficherCartes(copieDesCartes, gui);
		}
	}


	public static void mettreTouslesCartesVisible() {
		Carte[][] cartesDuJeu= UtilitaireGrilleGui.listeCarteJeu;
		for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
			for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {
				mettreUneCarteVisible(UtilitaireGrilleGui.listeCarteJeu[i][j]);
			}
		}
	}
	public static void mettreTouslesCartesInvisible() {
		Carte[][] cartesDuJeu= UtilitaireGrilleGui.listeCarteJeu;
		for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
			for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {
				mettreUneCarteInvisible(UtilitaireGrilleGui.listeCarteJeu[i][j]);
			}
		}
	}

	/**
	 * Méthode qui retourne une carte de numéro 2 quelquon non cliqué.
	 *@return uneCarte avec le numéro 2 et sorte quelquonque
	 * */
	public static Carte trouverCartePlusPetite() {
		int indexPetitCarte=1;
		Carte carte= null;
		while(carte==null) {
			for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
				for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {
					if(UtilitaireGrilleGui.listeCarteJeu[i][j].
							numero==indexPetitCarte&&
							UtilitaireGrilleGui.listeCarteJeu[i][j]
									.visible==false) {
						carte=UtilitaireGrilleGui.listeCarteJeu[i][j];
						break;
					}
				}
			}
			indexPetitCarte++;
		}
		return carte;
	}

	/**
	 * Méthode qui retourne la prochaine carte de la dernière séquence 
	 * montrée par l'utilisateur.  
	 * @param gui : Guille principale
	 * @param etatJeu : L'état du jeu.
	 * @return la prochaine carte de la dernière séquence
	 */
	public static Carte trouverCarteSuivantSequence(GrilleGui gui, 
			EtatJeu etatJeu) {
		Carte carteSuivante=new Carte();
		if(UtilisateurAClic(gui)==true) {
			Coordonnee c= getDernierClicPosition(gui);
			Carte carteCourante;
			if(ancienCarte[etatJeu.longueurSequence]==null) {
				carteCourante=ancienCarte[etatJeu.longueurSequence-1];
			}
			else {
				carteCourante= UtilitaireGrilleGui.listeCarteJeu
						[c.ligne][c.colonne];
			}
			for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
				for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {


					if(carteCourante.numero>=1&&carteCourante.numero<
							Constantes.CARTES_PAR_SORTES-1&&
							carteCourante.couleur.equals(UtilitaireGrilleGui.
									listeCarteJeu[i][j].couleur)&&
							UtilitaireGrilleGui.listeCarteJeu[i][j].numero==
							carteCourante.numero+1) {
						carteSuivante=UtilitaireGrilleGui.listeCarteJeu[i][j];
					}
					if(carteCourante.numero==Constantes.CARTES_PAR_SORTES) {
						carteSuivante=trouverCartePlusPetite();
					}

				}
			}
		}else {
			carteSuivante=trouverCartePlusPetite();
		}
		return carteSuivante;
	}

	/**
	 * Méthode qui permet de visualiser la prochaine carte 
	 * Pour montrer une seule carte il suffit de mettre la carte visible
	 * afficher les cartes, laisser un délai, mettre la carte non visible 
	 * et afficher les cartes. 
	 * @param carte: la carte à montrer 
	 * @param gui: Gui principale
	 * */
	public static void montrerUneCarte(Carte carte,GrilleGui gui) {

		mettreUneCarteVisible(carte);
		UtilitaireGrilleGui.afficherCartes(UtilitaireGrilleGui.
				listeCarteJeu, gui);
		gui.pause(Constantes.TEMPS_VISIONNEMENT);
		mettreUneCarteInvisible(carte);
		UtilitaireGrilleGui.afficherCartes(UtilitaireGrilleGui.
				listeCarteJeu, gui);
	}


	/**
	 * Méthode qui montre la prochaine carte de la dernière séquence montrée 
	 * par l'utilisateur si il lui des indices
	 * @param cartes : cartes du jeu 
	 * @param gui: Gui principale
	 * @param etatJeu :etatJeu L'état du jeu.
	 * */
	public static void montrerIndices(Carte[] cartes, 
			GrilleGui gui, EtatJeu etatJeu) {

		Carte carte= new Carte();
		if(etatJeu.nbIndices==0) {
			afficherMessage("VOUS N'AVEZ AUCUN INDICES RESTANT");
		}
		else {
			if(etatJeu.ilYaSequence==false) {
				carte=trouverCarteSuivantSequence(gui,etatJeu);
				montrerUneCarte(carte,gui);
				etatJeu.nbIndices--;
			}
			if(etatJeu.ilYaSequence==true) {
				carte=trouverCarteSuivantSequence(gui,etatJeu);
				montrerUneCarte(carte,gui);
				etatJeu.nbIndices--;
			}
		}

	}

	/**
	 * Méthode qui prend une carte et cherche pour la met visible dans la gui
	 * @param uneCarte= la carte à mettre visible
	 * */
	public static void mettreUneCarteVisible(Carte uneCarte) {	
		for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
			for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {
				if(UtilitaireGrilleGui.listeCarteJeu[i][j].equals(uneCarte)) {
					UtilitaireGrilleGui.listeCarteJeu[i][j].visible=true;
				}
			}
		}
	}

	/**
	 * Méthode qui prend une carte et cherche pour la met invisible dans la gui
	 * @param uneCarte= la carte à mettre invisible
	 * */
	public static void mettreUneCarteInvisible(Carte uneCarte) {	
		for(int i=0;i<UtilitaireGrilleGui.listeCarteJeu.length;i++) {
			for(int j=0;j<UtilitaireGrilleGui.listeCarteJeu[i].length;j++) {
				if(UtilitaireGrilleGui.listeCarteJeu[i][j].equals(uneCarte)) {
					UtilitaireGrilleGui.listeCarteJeu[i][j].visible=false;
				}
			}
		}
	}

	/**
	 * Procédure qui affiche un message cliquable.
	 * @param message Message à afficher.
	 */
	public static void afficherMessage(String message) {
		JOptionPane.showMessageDialog(null, 
				message);
	}

	/**
	 * Convertir la position de 2D à 1D (ligne * nombre de colonnes + colonne) 
	 * @param gui = Gui principale
	 * @param coordonnee= coordonnee de la carte sur le gui
	 * @return position de la carte transformé
	 * */
	public static int convertirPositionEn2D(GrilleGui gui,
			Coordonnee coordonnee ) {
		int position=0;
		if(coordonnee!=null) {
			position=(coordonnee.ligne*gui.getNbColonnes())+coordonnee.colonne;
		}
		return position;
	}


	/*
	 * Méthode qui return la dernière position sauvegardé
	 * 
	 * */
	public static Coordonnee getDernierClicPosition(GrilleGui gui) {
		return gui.getPosition();
	}

	/**
	 * Méthode qui vérifie si l'urilisateur a cliqué une carte
	 * 
	 * @param gui = Gui principale
	 * @return true si le gui a retenu une coordonnee de carte
	 * */
	public static boolean UtilisateurAClic(GrilleGui gui) {
		boolean clic= true;

		if(getDernierClicPosition(gui)==null) {
			clic=false;
		}
		return clic;
	}



}
