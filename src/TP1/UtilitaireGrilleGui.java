package TP1;

public class UtilitaireGrilleGui {

	public UtilitaireGrilleGui() {
		// TODO Auto-generated constructor stub
		
	}
	public static void afficherCartes(Carte[] listeCarte, GrilleGui gui) {
		
		/**
		 * Parcours la liste de cartes pour ajouter les images du gui.
		 * Rempli la première ligne jusqu'à la dernière.
		 * 
		 */
		
		int positionListeCarte = 0;
		
		for(int y=0; y< gui.getNbLignes(); y++) {
			
			for(int i=0; i< gui.getNbColonnes(); i++) {
				
				Carte carte = listeCarte[positionListeCarte];
				gui.setImage(y, i, carte.image);
				positionListeCarte++;
			}
			
		}
	}

}
