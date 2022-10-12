package TP1;

import javax.swing.JOptionPane;


/**
 * Gère les étapes du jeu : l'initialisation d'une partie, 
 * l'affichage des messages, l'affichage des cartes et leur visibilité
 * et gère les tours du jeu.
 * @author Simon Pitre Lamas, Radhika Chatterjee
 */
public class UtilitaireJeu {
	
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
    
    
    //carteAfficher= UtilitaireTableauCartes.brasser(carteAfficher);
		//UtilitaireTableauCartes.afficherCartes(carteAfficher, gui);
		//UtilitaireGrilleGui.cacherCartes(gui);
		//timerPourCacherCartes(gui);
		
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
			UtilitaireTableauCartes.rendreCartesNonVisible(cartesTemporaire);
		
		//Afficher les carte temporaire.
		UtilitaireTableauCartes.afficherCartes(cartesTemporaire, gui);
		

		
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
					cartes=UtilitaireTableauCartes.mélangerParPositionAleatoire(cartes);
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
	public static void effectuerUnTour(Carte[] cartes, GrilleGui gui,
			Stats stats, EtatJeu etatJeu) {
	}
	
	
	public static void gererSequence(Carte[][] cartes,Coordonnee dernierCarteDeLaSequence,Stats stats, EtatJeu etaJ, GrilleGui gui) {
		if(etaJ.longueurSequence==0) {
			
		}
		else {
			Coordonnee c= getDernierClicPosition(gui,etaJ);
			Carte carte=cartes[c.colonne][c.ligne];
			
			if(UtilitaireTableauCartes.deuxCartesSeSuivent(carte, cartes[dernierCarteDeLaSequence.colonne][dernierCarteDeLaSequence.ligne])) {
				etaJ.ilYaSequence=true;
				etaJ.longueurSequence++;
			}
			else {
				afficherMessage("LA SÉQUENCE EST BRISSÉ");
				if(etaJ.longueurSequence==1) {
					cartes[c.colonne][c.ligne].visible=false;
					etaJ.longueurSequence=0;
				}
			}
		}		
		if(etaJ.longueurSequence>stats.grandeSequence) {
			stats.grandeSequence=etaJ.longueurSequence;
		}
	}
	

	public static void validerClic(Carte[][] cartes,Stats stats, EtatJeu etaJ, GrilleGui gui) {
		if(getDernierClicPosition(gui,etaJ)!=null) {
			Coordonnee c= getDernierClicPosition(gui,etaJ);
			if(cartes[c.colonne][c.ligne].visible==true) {
				stats.nbEssaieActuel++;
				afficherMessage("VOUS AVEZ PERDU! LE COUP");
			}
			else {
				etaJ.tabSequence[etaJ.longueurSequence]=convertirPositionEn1D(gui,c);
				cartes[c.colonne][c.ligne].visible=true;
				stats.nbEssaieActuel++;
			}
		}
		
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
		UtilitaireTableauCartes.afficherCartes(cartes, gui);
	}
	
	
	
	
	public static void montrerIndices(Carte[] cartes, 
			GrilleGui gui, EtatJeu etatJeu) {
		String message = "Votre nombre d'indice est  : "+etatJeu.nbIndices;	
		afficherMessage(message);
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
	 * Convertir la position de 2D à 1D (ligne * nombre de colonnes + colonne) .
	 * */
	public static int convertirPositionEn1D(GrilleGui gui,Coordonnee coordonnee ) {
		int position=0;
		if(coordonnee!=null) {
			position=(coordonnee.ligne*gui.getNbColonnes())+coordonnee.colonne;
		}
		return position;
	}
	
	public static Coordonnee getDernierClicPosition(GrilleGui gui,EtatJeu etatJeu) {
		return gui.getPosition();
	}
	
	
	
}
