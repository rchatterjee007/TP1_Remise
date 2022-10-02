package TP1;

import javax.swing.JOptionPane;

public class UtilitaireStats {

	public UtilitaireStats() {
		// TODO Auto-generated constructor stub
	}
	public static void ajusterStatsNouvellePartie(Stats stats) {
		
	}
	public static void montrerStats(Stats stats) {
		
		JOptionPane.showMessageDialog(null, 
				genererMessageStats(stats));
		
	}
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
