package TP1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class UtilitaireJeu {

	public UtilitaireJeu() {
		// TODO Auto-generated constructor stub
		
	}
	public static void initialiserJeu(Carte[] jeu, Carte[] carteAfficher, GrilleGui gui, EtatJeu etatjeu) {
		//carteAfficher= UtilitaireTableauCartes.brasser(carteAfficher);
		UtilitaireTableauCartes.afficherCartes(carteAfficher, gui);
		UtilitaireGrilleGui.cacherCartes(gui);
		timerPourCacherCartes(gui);
		
	}
	public static void effectuerUnTour(Carte[] cartes, GrilleGui gui, Stats stats, EtatJeu etatJeu) {
		
	}
	public static void montrerLesCartes(Carte[] cartes, GrilleGui gui, EtatJeu etatJeu) {
		
	}
	public static void montrerIndices(Carte[] cartes, GrilleGui gui, EtatJeu etatJeu) {

	}
	
	private static void timerPourCacherCartes(GrilleGui gui) {
		Timer timer = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilitaireGrilleGui.cacherCartes(gui);
				
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
}
