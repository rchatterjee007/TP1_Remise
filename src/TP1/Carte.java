package TP1;
import javax.swing.Icon;

/*
 * Repr�sente une carte � jouer.
 * 
 * Sp�cial : Contient l'image correspondante � afficher.
 * 
 * Auteur: Pierre B�lisle
 * Version : copyright A2022
 */
public class Carte {

	// Le num�ro de carte de Constantes.AS � Constantes.ROI.
	public int numero;  

	// COEUR � PIQUE.
	public Constantes.Sorte couleur;  

	// L'image de la carte � montrer.
	public Icon image; 

	// Vrai si la carte est tourn�e vers le haut.
	public boolean visible = true; 

	// Sert principalement au DEBUG.
	public String toString() {

		return numero + image.toString() + couleur.toString();
	}
}
