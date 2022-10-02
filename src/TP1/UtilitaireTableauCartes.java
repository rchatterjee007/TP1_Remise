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
	public static Carte[] separerCartesEn2(Carte [] tabCarteOriginale, int indiceDebut, int indiceFin){
		Carte[] sousTab = new Carte[indiceFin - indiceDebut + 1];
		int j = 0;
		for(int i  = indiceDebut; i <= indiceFin; i++){
			sousTab[j] = tabCarteOriginale[i];
			j++;                                              
		}
		return sousTab;
	}
public static Carte[] separerCartesEn2(Carte [] tabCarteOriginale, int indiceDebut, int indiceFin){
		Carte[] sousTab = new Carte[indiceFin - indiceDebut + 1];
		int j = 0;
		for(int i  = indiceDebut; i <= indiceFin; i++){
			sousTab[j] = tabCarteOriginale[i];
			j++;                                              
		}
		return sousTab;
	}
	public static Carte[] brasser(Carte[] cartes){	
		// Générer un nombre aléatoire de fois que vous répéterez les trois étapes.
		int nbrAlea=UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		Carte[]nouvTabCarte=new Carte[cartes.length];
		int i=0;	
		while (i!=nbrAlea){
			melanger(cartes);//melanger les cartes
			//Separer LE PAQUET DE CARTES EN 2
			int indiceTab1=UtilitaireFonction.alea(20, 30);
			Carte [] tab1= separerCartesEn2(cartes,0,indiceTab1); 
			Carte [] tab2= separerCartesEn2(cartes,indiceTab1+1,cartes.length-1); 
			nouvTabCarte=fusionner(cartes, tab1, tab2);
			i++;
		}
		return nouvTabCarte;
	}
	public static void deplacerCartes(Carte [] tab1) {
		Carte valueBeingMoved = tab1[tab1.length-1];
		for (int i=tab1.length-1;i>0;i--) {
			tab1[i] = tab1[i-1];
		}
		tab1[0] = valueBeingMoved;
	}
	public static void melanger(Carte [] tab) {
		int nbrFoisMelanger= UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		int i=0;
		
		while (i!=nbrFoisMelanger){
			int nbrCartesDeplacer=UtilitaireFonction.alea(3, 10);
			for(int j=0;j<nbrCartesDeplacer;j++) {
				deplacerCartes(tab);
			}
			i++;
		}	
	}
	public static Carte[]  fusionner(Carte [] cartesOriginales, Carte [] tab1, Carte [] tab2) {
		Carte[] tabCartesAfficher= new Carte [cartesOriginales.length];
		int iterateurTab1=0;
	    int iterateurTab2=0;
	    int iteratuerTabCartesFinale=0;
	    while(iteratuerTabCartesFinale<tabCartesAfficher.length){
	    	int nbrPairOuImpaire=UtilitaireFonction.alea(0,10);
	       	if (nbrPairOuImpaire%2==0&&iterateurTab1<tab1.length){
	       		tabCartesAfficher[iteratuerTabCartesFinale]=tab1[iterateurTab1];
	       		iterateurTab1++;
	       		iteratuerTabCartesFinale++;
	       	}
	       	if(nbrPairOuImpaire%2==1&&iterateurTab2<tab2.length){
	       		tabCartesAfficher[iteratuerTabCartesFinale]=tab2[iterateurTab2];
	       		iterateurTab2++;
	       		iteratuerTabCartesFinale++;
	        }
	    }
		return tabCartesAfficher;
	}
	
	

}
