package TP1;
/*
 * Durant la partie, les diff�rents sous-programmes doivent maintenir ces 
 * attributs qui donnent l'�tat du jeu.
 * 
 * Auteur: Pierre B�lisle
 * Version : Copyright A2022
 */
public class EtatJeu{
	
    	// Mis � true si l'utilisateur clique sur le bouton pour quitter.
		public boolean partieTerminee;
		
		// Permet de retenir entre les sous-programme s'il y a une s�quence de 
		// cartes s�lectionn�es par l'utilisateur (plus de 2 cartes).
		public boolean ilYaSequence;

		// On retient toutes les positions de cartes qui font partie d'une 
		// s�quence.
		public int[] tabSequence;
		public int longueurSequence;
		
		// Le pointage depuis le d�marrage du jeu.
		public int pointage;
		
		
		// Le nombre de fois que le joueur peut demander de voir la prochaine 
		// carte de la s�quence.
		public int nbIndices =  Constantes.NB_INDICE_DEPART;
		

}
	
