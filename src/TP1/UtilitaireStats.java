package TP1;

import javax.swing.JOptionPane;

/**
 * Gère l'affichage des statistiques.
 * @author Simon Pitre Lamas, Radhika Catherjee
 *
 */
public class UtilitaireStats {


	public static void ajusterStatsNouvellePartie(Stats stats) {
		
	}
	/**
	 * Affiche les statistiques avec un message interactif.
	 * @param stats Statistique du jeu
	 */
	public static void montrerStats(Stats stats) {
		
		JOptionPane.showMessageDialog(null, 
				genererMessageStats(stats));
		
	}
	/**
	 * Génère un message représentant les statistiques de la partie tel que :
	 * le nombre d'essaie, la séquence, le nombre de réussites et la moyenne
	 * des essais par partie.
	 * @param stats Statistique du jeu.
	 * @return Un message à afficher.
	 */
	private static String genererMessageStats(Stats stats) {
		
		double moyenneEssaisParParties = stats.nbEssaiesTotal/stats.nbReussites;
		
		String message = 
				
				"Nombre d'essai(s) actuel: " + stats.nbEssaieActuel+" carte(s)"+"\n"+
				"La plus grande séquance: " + stats.grandeSequence+" carte(s)"+"\n"+
				"Nombre de réussites:" + stats.nbReussites+" partie(s) consécutives"+"\n"+
				"Nombre d'essai(s) en moyenne par parties: "+ moyenneEssaisParParties;
		return message;
		
	}
}