package TP1;
/*
 * Offre des fonctions utilitaires communes au projet.
 * 
 * @author Pierre B�lisle
 * @version A2022
 *
 */
public class UtilitaireFonction {

	/**
	 * Retourne une nombre al�atoire dans un intervalle entier donn�.
	 * 
	 * @param min La plus petite valeur possible.
	 * @param max La plus grande valeur possible.
	 * 
	 * @return Un nombre entre min et max (inclusivement).
	 */
	public static int alea(int min, int max){


		// On re�oit une valeur entre 0 et 1 qu'on d�place entre min et max.
		return (int) Math.round(Math.random()* (max - min) + min);
	}	

}
