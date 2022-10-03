package TP1;

public class UtilitaireGrilleGui {

	public UtilitaireGrilleGui() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Affiche les images des cartes dans le gui.
	 * Si la carte n'est pas découverte, l'image est mise à null. 
	 * 
	 * @param listeCarte Une liste en 2d de carte
	 * @param gui Le gui
	 */
	public static void afficherCartes(Carte[][] listeCarte, GrilleGui gui) {
		
		Carte carte = new Carte();
		for(int y=0; y< gui.getNbLignes(); y++) {
			
			for(int i=0; i< gui.getNbColonnes(); i++) {
				
				carte = listeCarte[y][i];
				if(carte.visible==false) {
					gui.setImage(y, i, null);
				}else {
					gui.setImage(y, i, carte.image);
				}
			}
		}
	}
	/**
	 * Cache toutes les cartes en mettant l'image à null
	 * @param gui
	 */
	public static void cacherCartes(GrilleGui gui) {
		
		for(int y=0; y< gui.getNbLignes(); y++) {
			
			for(int i=0; i< gui.getNbColonnes(); i++) {
				
				gui.setImage(y, i, null);
			}
		}
	}
}
