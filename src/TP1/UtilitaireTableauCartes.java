package TP1;

import TP1.Constantes.Sorte;

public class UtilitaireTableauCartes {

	public UtilitaireTableauCartes() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Carte[] copieDuJeu(Carte[] jeuneuf) {
		return jeuneuf;
	}
	public static Boolean toutesLesCartesSontTournee(Carte[] cartesAffichees) {
		Boolean carteVisible = true;
		for(int i=0; i<cartesAffichees.length; i++) {
			
			if(!cartesAffichees[i].visible) {
				carteVisible = false;
				break;
			}
			
		}
		
		return carteVisible;
	}
	
	public static Boolean deuxCartesSeSuivent(Carte carte1, Carte carte2) {
		
		Boolean carteSeSuivent = true;
		int numeroCarte1 = carte1.numero;
		int numeroCarte2 = carte2.numero;
		Sorte sorteCarte1 = carte1.couleur;
		Sorte sorteCarte2 = carte2.couleur;
		if(numeroCarte1 == numeroCarte2++ && sorteCarte1 == sorteCarte2) {
		return carteSeSuivent;
		}else {
			return false;
		}
	}
	
	public static Carte[] modifierVisibiliteCartes(Carte[] cartesAffichees) {
		for(int i=0; i<cartesAffichees.length; i++) {
			cartesAffichees[i].visible = false;
		}
		return cartesAffichees;
	}
	

	public static void afficherCartes(Carte[] carteAfficher, GrilleGui gui) {
		
		Carte[][] listeCarte2D = transformerCarteListeEn2DPourGui(carteAfficher, gui);
		
		UtilitaireGrilleGui.afficherCartes(listeCarte2D, gui);
		
		
	}
	
	private static Carte[][] transformerCarteListeEn2DPourGui(Carte[] carteAfficher, GrilleGui gui) {
		int positionListeCarte = 0;
		Carte[][] listeCartes2D = new Carte[gui.getNbLignes()][gui.getNbColonnes()];
		
		for(int y=0; y< gui.getNbLignes(); y++) {
			
			for(int i=0; i< gui.getNbColonnes(); i++) {
				
				Carte carte = carteAfficher[positionListeCarte];
				 
				listeCartes2D[y][i] = carte;
				
				positionListeCarte++;
				
			}
			
		}
		return listeCartes2D;
	}
	
	

}
