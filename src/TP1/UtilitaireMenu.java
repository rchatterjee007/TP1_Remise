package TP1;
import javax.swing.JOptionPane;

/*
 * Classe qui contient les SP pour g�rer les boutons d'options
 * de menu montr�s dans le GUI.
 * 
 * S'il y a ajout de bouton dans le module Constantes, il faut modifier cette 
 * classe et y ajouter le comportement d�sir�.
 * 
 * Auteur: Pierre B�lisle
 * Version : Copyright A2022
 */
public class UtilitaireMenu {
	
	
	/*
	 * V�rifie quelle option de menu a �t� choisie et d�marre la bonne
	 * fonctionnalit�.
	 * 
	 * @param jeuNeuf Un jeu pas brasss�
	 * @param cartes  Le jeu de cartes affich�es
	 * @param gui La fen�tre qui  montre les cartes et les boutons
	 * @param stats Les statistiqwues � maintenir
	 * @param etatJeu Les d�tails sur le jeu.
	 * @return true si l'utilisateur a cliqu� sur le bouton pour quitter.
	 */
	public static boolean gererMenu(Carte[] jeuNeuf, 
			Carte[] cartes, 
			GrilleGui gui, 
			Stats stats,
			EtatJeu etatJeu){

		/*
		 * Strat�gie : Agit simplement comme distributeur de t�che selon 
		 *             l'option du menu choisie par l'utlisateur dand le gui.
		 *                  
		 *             On a cr�� un sous-programme pour chaque situation
		 *             m�me si c'�tait possible de r�utiliser en une seule ligne
		 *             de code (voir: montrer_stats).
		 *                  
		 *             Doit �tre modifi� si on ajoute des options de menu dans 
		 *             le tableau-constante TAB_OPTIONS_MENU.
		 */
		
		boolean partieTerminee = false;
		
		String reponse = gui.getOptionMenuClique();
		
		// Selon les versions de Java, switch-case sur des String ne 
		// fonctionne pas toujours.
		if (reponse. equals(Constantes.TAB_OPTIONS_MENU[Constantes.NOUVELLE_PARTIE])) {

			UtilitaireJeu.initialiserJeu(jeuNeuf, cartes,gui, etatJeu);
		}
		
		else if(reponse.equals(Constantes.TAB_OPTIONS_MENU[Constantes.CARTE])) {

			UtilitaireJeu.montrerLesCartes(cartes, gui, etatJeu);
				
		}

		else if(reponse.equals(Constantes.TAB_OPTIONS_MENU[Constantes.INDICE])) {

				
			UtilitaireJeu.montrerIndices(cartes, gui, etatJeu);
		}

		else if(reponse.equals(Constantes.TAB_OPTIONS_MENU[Constantes.STATS])) {

			UtilitaireStats.montrerStats(stats);
		}
		
		else if(reponse.equals(Constantes.TAB_OPTIONS_MENU[Constantes.QUITTER])) {

			partieTerminee = true;
		}
		
		return partieTerminee;
	}
}