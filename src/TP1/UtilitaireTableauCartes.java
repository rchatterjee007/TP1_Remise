package TP1;

import TP1.Constantes.Sorte;

public class UtilitaireTableauCartes {
	// CONSTANTS qui determine le nombre de fois que les cartes seront brasses
		final static int NOMBRE_MAX=5;
		final static int NOMBRE_MIN=1;


	public UtilitaireTableauCartes() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Carte[] mélangerParPositionAleatoire(Carte[] cartes) {
		
		Carte[] cartesTemporaire = new Carte[Constantes.NB_CARTES];
		int chiffreAleatoire;
		for(int i=0; i<cartes.length; i++) {
			
			do {
			chiffreAleatoire = UtilitaireFonction.alea(0, Constantes.NB_CARTES-1);
				
			}while(cartesTemporaire[chiffreAleatoire] != null);
			
			cartesTemporaire[chiffreAleatoire] = cartes[i];
		}
		
		
		return cartesTemporaire;
	}
	public static Carte[] copieDuJeu(Carte[] jeuneuf) {
		Carte[] cartesCopie = new Carte[jeuneuf.length];
		for(int i = 0; i<jeuneuf.length; i++) {
			cartesCopie[i] = jeuneuf[i];
		}
		return cartesCopie;
	}
	
	
	public static Boolean toutesLesCartesSontTournee(Carte[] cartes) {
		Boolean toutesLesCartesSontTournee = true;
		
		int indexCarte = 0;
		
		while(indexCarte < cartes.length && toutesLesCartesSontTournee != false) {
			
			if(!cartes[indexCarte].visible) {
				toutesLesCartesSontTournee = false;
				break;
			}
			indexCarte++;
		}

		
		return toutesLesCartesSontTournee;
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
	
	public static Carte modifierVisibiliteCarte(Carte carte) {
		
		
		if(carte.visible == false) {
			carte.visible = true;
		}else {
			carte.visible = false;
		}
		
		return carte;
	}
	

	public static void afficherCartes(Carte[] cartes, GrilleGui gui) {

		Carte[][] listeCarte2D = transformerCarteListeEn2DPourGui(cartes, gui);
		
		UtilitaireGrilleGui.afficherCartes(listeCarte2D, gui);
		
		
	}
	
	
	public static Carte[] rendreCartesNonVisible(Carte[] cartes) {
			
		for(int i=0; i<cartes.length; i++) {
			cartes[i].visible = false;
		}
		return cartes;
	}
	
	public static Carte[] rendreCartesVisble(Carte[] cartes) {
		
		for(int i=0; i<cartes.length; i++) {
			cartes[i].visible = true;
		}
		return cartes;
	}
	
	private static Carte[][] transformerCarteListeEn2DPourGui(Carte[] cartes, GrilleGui gui) {
		int positionListeCarte = 0;
		Carte[][] listeCartes2D = new Carte[gui.getNbLignes()][gui.getNbColonnes()];
		
		for(int y=0; y< gui.getNbLignes(); y++) {
			
			for(int i=0; i< gui.getNbColonnes(); i++) {
				
				Carte carte = cartes[positionListeCarte];
				 
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
