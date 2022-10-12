package TP1;

/**
 * Gère l'affichage des cartes selon leur visibilité
 * @author Simon Pitre Lamas, Radhika Chatterjee
 *
 */
public class UtilitaireGrilleGui {

	static Carte[][] listeCarteJeu;
	public UtilitaireGrilleGui() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Affiche les images des cartes dans le gui.
	 * Si la visibilité de la carte est false, l'image est mise à null. 
	 * 
	 * @param listeCarte Une liste en 2d de carte
	 * @param gui Le gui
	 */
	public static void afficherCartes(Carte[][] listeCarte, GrilleGui gui) {
		listeCarteJeu=listeCarte;
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

	
	public static void montreLaCarte(Coordonnee c,GrilleGui gui) {		
			Carte carte= listeCarteJeu[c.ligne][c.ligne];
			listeCarteJeu[c.ligne][c.ligne].visible=true;
			gui.setImage(c.ligne, c.colonne, carte.image);
	}
	
	
}
