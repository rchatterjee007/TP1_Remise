package TP1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class UtilitaireJeu {

	public UtilitaireJeu() {
		// TODO Auto-generated constructor stub

	}
	public static void initialiserJeu(Carte[] jeu, Carte[] carteAfficher, 
			GrilleGui gui, EtatJeu etatjeu) {


		//carteAfficher= UtilitaireTableauCartes.brasser(carteAfficher);
		//UtilitaireTableauCartes.afficherCartes(carteAfficher, gui);
		//UtilitaireGrilleGui.cacherCartes(gui);
		//timerPourCacherCartes(gui);

		//Melanger les cartes
		carteAfficher =
				UtilitaireTableauCartes.
				mélangerParPositionAleatoire(carteAfficher);

		//Afficher les cartes du jeu neuf.
		UtilitaireTableauCartes.afficherCartes(jeu, gui);

		//Message à l'utilisateur
		messagePretAJouer();

		//Copier le jeu de cartes à afficher dans un tableau de cartes temporaire.
		Carte[] cartesTemporaire = new Carte[carteAfficher.length];
		cartesTemporaire = carteAfficher.clone();

		//Modifier visibilité des cartes à afficher
		carteAfficher = UtilitaireTableauCartes.
				rendreCartesNonVisible(carteAfficher);

		//Rendre les cartes temporaire visible (carteAfficher et cartesTemporaires 
		//ont la même addresse)
		cartesTemporaire = UtilitaireTableauCartes.
				rendreCartesVisble(cartesTemporaire);

		//Afficher les cartes
		UtilitaireTableauCartes.afficherCartes(cartesTemporaire, gui);

		//Faire une pause pour laisser le temps de voir les cartes
		gui.pause(Constantes.TEMPS_VISIONNEMENT);

		//Modifier la visibilité des cartes temporaire à faux
		cartesTemporaire = UtilitaireTableauCartes.
				rendreCartesNonVisible(cartesTemporaire);

		//Afficher les carte temporaire
		UtilitaireTableauCartes.afficherCartes(cartesTemporaire, gui);



	}
	public static void effectuerUnTour(Carte[] cartes, GrilleGui gui, 
			Stats stats, EtatJeu etatJeu) {

	}
	public static void montrerLesCartes(Carte[] cartes, GrilleGui gui, 
			EtatJeu etatJeu) {

	}
	public static void montrerIndices(Carte[] cartes, GrilleGui gui, 
			EtatJeu etatJeu) {

	}
	public static void messagePretAJouer() {

		JOptionPane.showMessageDialog(null, 
				genererMessagePretAJouer());

	}
	private static String genererMessagePretAJouer() {

		String message = "Vous avez quelques secondes pour "
				+ "mémoriser les cartes";		
		return message;

	}
	private static void timerPourAfficherCartes(Carte[] cartes, 
			GrilleGui gui) {
		Timer timer = new Timer(Constantes.TEMPS_VISIONNEMENT, 
				new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilitaireTableauCartes.afficherCartes(cartes, gui);

			}
		});
		timer.setRepeats(false);
		timer.start();
	}
}
