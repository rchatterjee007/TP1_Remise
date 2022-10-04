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
				chiffreAleatoire = UtilitaireFonction.alea(0, Constantes.
						NB_CARTES-1);

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
		Boolean toutesLesCartesSontTournee = false;
		for(int i=0; i<cartes.length; i++) {

			if(cartes[i].visible) {
				toutesLesCartesSontTournee = true;
				break;
			}

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

	private static Carte[][] transformerCarteListeEn2DPourGui(Carte[] cartes, 
			GrilleGui gui) {
		int positionListeCarte = 0;
		Carte[][] listeCartes2D = new Carte[gui.getNbLignes()]
				[gui.getNbColonnes()];

		for(int y=0; y< gui.getNbLignes(); y++) {

			for(int i=0; i< gui.getNbColonnes(); i++) {

				Carte carte = cartes[positionListeCarte];

				listeCartes2D[y][i] = carte;

				positionListeCarte++;

			}

		}
		return listeCartes2D;
	}


	public static Carte[] sousTabCarte(Carte [] tabCarteOriginale, 
			int indiceDebut, int indiceFin){
		return tabCarteOriginale;
	}


	public static Carte[] separerCartesEn2(Carte [] tabCarteOriginale, 
			int indiceDebut, int indiceFin){
		Carte[] sousTab = new Carte[indiceFin - indiceDebut + 1];
		int j = 0;
		for(int i  = indiceDebut; i <= indiceFin; i++){
			sousTab[j] = tabCarteOriginale[i];
			j++;                                              
		}
		return sousTab;
	}
	public static Carte[] brasser(Carte[] cartes){	

		int nbrAlea=UtilitaireFonction.alea(NOMBRE_MIN, NOMBRE_MAX);
		Carte[]nouvTabCarte=new Carte[cartes.length];
		int i=0;	
		while (i!=nbrAlea){
			melanger(cartes);//melanger les cartes
			//Separer LE PAQUET DE CARTES EN 2
			int indiceTab1=UtilitaireFonction.alea(20, 30);
			Carte [] tab1= separerCartesEn2(cartes,0,indiceTab1); 
			Carte [] tab2= separerCartesEn2(cartes,indiceTab1+1,
					cartes.length-1); 
			nouvTabCarte=fusionner(cartes.length, tab1, tab2);
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
	public static Carte[]  fusionner(int tailleTabCartesOriginale, 
			Carte [] tab1, Carte [] tab2) {
		Carte[] tabCartesAfficher= new Carte [tailleTabCartesOriginale];
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
	public static Carte[] brasserParPaquet(Carte []cartes) {
		int nbrPaquetAlea= UtilitaireFonction.alea(6, 8);
		Carte [][] cartes2D = new Carte[nbrPaquetAlea+1][cartes.length];
		cartes2D[cartes2D.length-1]=cartes;
		for (int i=0;i<cartes2D.length-1;i++){
			cartes2D[i]=new Carte[cartes.length];
		}
		
		while(verifierSiLesPaquetsSontVides(cartes2D)!=true) {
			int nbrProchainPaquetAVider= UtilitaireFonction.alea(0, 8);
			cartes2D=diviserLesCartesEnPaquets(cartes2D,
					cartes2D[cartes2D.length-1],nbrProchainPaquetAVider);		
			
		}
		return cartes2D[cartes2D.length-1];
	}
	public static Carte[][] diviserLesCartesEnPaquets(Carte [][] cartes2D,
			Carte []cartes,int nbrProchainPaquetAVider){
		int iterateurCarte=0;
		int iterateurPaquet=0;
		while(iterateurCarte<cartes.length-1){
			for(int s=0;s<cartes2D.length-1;s++){
				if(cartes2D[s]!=null) {
					cartes2D[s][iterateurPaquet]=cartes[iterateurCarte];
					iterateurCarte++;
				}	
			}
			iterateurPaquet++;
		}

		cartes2D[cartes2D.length-1]=null;
		cartes2D[cartes2D.length-1]=cartes2D[nbrProchainPaquetAVider];
		cartes2D[nbrProchainPaquetAVider]=null;
		return cartes2D;
	}	
	public static boolean verifierSiLesPaquetsSontVides(Carte[][] cartes) {
		boolean estVide=false;
		while(estVide=false) {
			for (int i=0;i<cartes.length-1;i++) {
				if(cartes[i].equals(null)) {
					estVide=true;
				}
				else {
					estVide=false;
				}		
			}
		}
		return estVide;
	}



}
