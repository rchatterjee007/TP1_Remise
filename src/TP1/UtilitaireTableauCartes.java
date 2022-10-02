package TP1;

import TP1.Constantes.Sorte;

public class UtilitaireTableauCartes {
	// CONSTANTS qui determine le nombre de fois que les cartes seront brasses
		final static int NOMBRE_MAX=5;
		final static int NOMBRE_MIN=1;


	public UtilitaireTableauCartes() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Carte[] copieDuJeu(Carte[] jeuneuf) {
		return jeuneuf;
	}
	public static Boolean toutesLesCartesSontTournee(Carte[] cartesAffichees) {
		Boolean carteVisible = true;
		for(int i=0; i<cartesAffichees.length; i++) {
			carteVisible = cartesAffichees[i].visible;
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
public static Carte[] sousTabCarte(Carte [] tabCarteOriginale, int indiceDebut, int indiceFin){
	
		
		Carte[] sousTab = new Carte[indiceFin - indiceDebut + 1];
		int j = 0;
		for(int i  = indiceDebut; i <= indiceFin; i++){
			sousTab[j] = tabCarteOriginale[i];
		    j++;                                              
		}
		return sousTab;
	}
	
	public static void brasser(Carte[] cartes){	
		// Générer un nombre aléatoire de fois que vous répéterez les trois étapes.
		int nbrAlea=UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		int i=0;	
		
		
		// REPETER LE M/LANGE POUR LE X NOMBRE DE FOIS
		while (i!=nbrAlea){
			
			
			//DIVISER LE PAQUET DE CARTES EN 2
			int indiceTab1=UtilitaireFonction.alea(20, 30);
			Carte [] tab1= sousTabCarte(cartes,0,indiceTab1); 
			Carte [] tab2= sousTabCarte(cartes,indiceTab1+1,cartes.length-1); 
				
			melanger(tab1,tab2);
			//separerPaquet(tailleTab1,tailleTab2);
			fusionner();
			i++;
		}
	}
	
	public static void deplacerCartes(Carte [] tab1) {
		Carte valueBeingMoved = tab1[tab1.length-1];
		  for (int i=tab1.length-1;i>0;i--) {
			  tab1[i] = tab1[i-1];
		  }
		  tab1[0] = valueBeingMoved;
		
		
	}
	
	public static void melanger(Carte [] tab1,Carte [] tab2) {
		int nbrFoisMelanger= UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		int i=0;

		while (i!=nbrFoisMelanger){
			int nbrCartesDeplacer=UtilitaireFonction.alea(3, 10);
			for(i=0;i<nbrCartesDeplacer;i++) {
				deplacerCartes(tab1);
				deplacerCartes(tab2);
			}
			i++;
		}
		
	}
	public static void separerPaquet(int tailleTab1, int tailleTab2) {
	}
	public static void fusionner() {
	}
	

}
