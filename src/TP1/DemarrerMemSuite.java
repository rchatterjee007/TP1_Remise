package TP1;
import javax.swing.JOptionPane;

/*
Le jeu memSuite est un nom invent� dans le cadre du tp1 INF111 H18.  C'est un
jeu de m�moire pour retrouver des suites de cartes cach�es (voir �nonc� fourni).

La r�gle du jeu est simple.  Des cartes sont brass�es et dispos�es � l'envers � 
l'utilisateur en N lignes et M colonnes!  L'utilisateur doit s�lectionner sans
 erreur des suites de cartes de m�me sorte.

Le jeu retient les statistiques sur la plus grande s�quence obtenue 
(1, 2, 3, ..., n cartes cons�cutives), le nombre d'essais dans la partie,
le nombre de r�ussites depuis le d�but de la partie et le nombre d'essais en
moyenne.

Les r�gles sont les suivantes :
	- Une carte est retourn�e.  Impossible d'annuler.
	- Tant qu'il y a une suite (1,2,3 ou 3,4,5, ou 10,V,D,R ...) les cartes 
	  restent d�voil�es.  Le jeu se termine lorque toutes les cartes sont 
	  d�voil�es.
	- Il faut un minimum de 2 cartes pour une suite.
	- Une carte seule de la mauvaise couleur compte pour un essai.
	- Une suite compte pour un essai.
	- Il n'y a pas de suivante au ROI et L'AS vient avant 2.


Le code ne d�passe pas 80 colonnes.
Remarquez l'a�ration du code (l'espace entre les lignes).

 * Auteur: Pierre B�lisle
 * Version : Copyright A2022
 */

public class DemarrerMemSuite {

	/*
	 * Strat�gie globale : On utilise les SP des diff�rents modules pour obtenir
	 * une grille GUI dont on se sert pour afficher les cartes du jeu. 
	 * 
	 * C'est ici qu'on initialise le jeu et qu'on g�re la boucle principale qui 
	 * se termine si l'utilisateur quitte.  S'il r�ussit, un message de 
	 * f�licitations est donn� et certaines statistiques sont initialis�es.
	 * 
	 * Pour g�rer les clics, il n'y a que 2 possibilit�s. Un clic sur un 
	 * bouton de menu ou sur une carte. 
	 */
	public static void main(String[] args) {

		// Cr�ation de l'interface graphique qui permet de voir les cartes 
		// et de jouer.
		GrilleGui gui = new GrilleGui(Constantes.NB_SORTES, 
				Constantes.NB_CARTES/Constantes.NB_SORTES);

		// Les statistique � maintenir.
		Stats stats = new Stats();		

		// Il faut afficher un jeu neuf avant chaque nouvelle partie.  Pour ne 
		// pas avoir � r�ouvrir les fichiers � chaque fois, on garde le jeu 
		// neuf et on travaille sur une copie.
		Carte[] jeuNeuf = UtilitaireSysteme.obtenirJeuCartesNeuf();
		Carte[] cartesAffichees = UtilitaireTableauCartes.copieDuJeu(jeuNeuf);


		// Retient l'�tat du jeu.
		EtatJeu etatJeu = new EtatJeu();

		// Cr�ation du tableau de s�quences avec le maximum possible. 
		etatJeu.tabSequence =  new int[Constantes.CARTES_PAR_SORTES];

		// Boucle qui se termine si l'utilisateur quitte 
		// en cliquant sur X ou le bouton quitter.
		while(!etatJeu.partieTerminee){

			// � faire entre chaque partie.
			etatJeu.ilYaSequence = true;
			etatJeu.longueurSequence = 0;
			stats.nbEssaieActuel = 0;			
			
			// Brasse les cartes, affiche le jeu et le cache ensuite.
			UtilitaireJeu.initialiserJeu(jeuNeuf, cartesAffichees, gui, etatJeu);

			/*
			 * Tant que toutes les cartes ne sont pas tourn�es ou que 
			 * l'utilisateur n'a  pas quitt�, on continue.
			 * 
			 * Se lit aussi : Tantque toutes les cartes ne sont pas toutes 
			 *                tourn�es et que la partie n'est pas termin�e.
			 * 
			 */
			while(!(UtilitaireTableauCartes
					.toutesLesCartesSontTournee(cartesAffichees)  || 
					etatJeu.partieTerminee)) {

				// Proc�dure locale.
				gererClic(jeuNeuf, cartesAffichees, gui, stats, etatJeu);

			}	

			// Si la boucle s'est termin�e, c'est parce que toutes les cartes 
			// sont tourn�es ou que l'utilisateur a annul�.  S'il n'a pas 
			// annul�, on le f�licite et on se pr�pare � la prochaine partie.
			if(!etatJeu.partieTerminee) {

				JOptionPane.showMessageDialog(null, 
						"Bravo, vous avez " + 
								++ stats.nbReussites + " r�ussite(s)");

				UtilitaireStats.ajusterStatsNouvellePartie(stats);
			}			
		}

		// Ferme le GUI et termine l'application.
		System.exit(0);
	}
	/*
	 * Permet � l'utilisateur de cliquer sur une carte ou sur un des 
	 * boutons du menu.
	 */
	private static void gererClic(Carte[] jeuNeuf, 
			Carte[] cartes, GrilleGui gui,
			Stats stats, 
			EtatJeu etatJeu) {

		etatJeu.partieTerminee = false;

		// Laisse le temps � l'utilisateur de cliquer (important).
		gui.pause(1);

		// On effectue un tour s'il y a eu un clic  sur une case.
		// Une seule carte est trait�e � chaque tour.
		if(gui.caseEstCliquee()){

			UtilitaireJeu.effectuerUnTour(cartes, gui, stats, etatJeu);
		}

		// L'utilisateur a cliqu� sur un des boutons d'option, on g�re le menu.
		else if(gui.optionMenuEstCliquee()) {

			etatJeu.partieTerminee =
					UtilitaireMenu.gererMenu(jeuNeuf, cartes, gui, stats, etatJeu);
		}
	}
}