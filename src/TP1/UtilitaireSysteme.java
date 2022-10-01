package TP1;
import javax.swing.ImageIcon;

public class UtilitaireSysteme {

/*
 * Retourne un tableau de r�f�rences de Carte.  Les cartes ont une image 
 * qui doit �tre prise dans le r�pertoire images/, qui doit �tre au m�me niveau 
 * que le r�pertoire src/ et les fichiers .gif doivent s'y trouver.
 *
 * Le tout a �t� mont� sous Windows.  Il n'y a pas eu de test sur les autres 
 * plateformes.
 * 
 * Auteur: Pierre B�lisle
 * Version : Copyright A2022
 */
public static Carte[] obtenirJeuCartesNeuf() {
	
		Carte[] cartes = new Carte[Constantes.NB_CARTES];
		
		// Jack, Queen, King and Ace (D� au noms de fichier).
		String[] figures = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
									  "J", "Q", "K"};
		
		// heart, diamond, club and spade.  C'�tait les noms originaux des 
		// fichiers que nous n'avons pas francis�s.
		String[] couleurs = {"h", "d", "c", "s"}; // It�rateur sur les couleurs. 

		// It�rateur du tableau d'images.
		int pos = 0;
		
		// On obtient les sortes en provenance du type �num�ratif.
		Constantes.Sorte[] lesCouleurs = Constantes.Sorte.values();
		
		// Un tour par sorte.
		for (int i = 0; i < Constantes.NB_SORTES; i++) {
			
			for(int fig = 0; fig < figures.length; fig++) {
				
				cartes[pos] = new Carte();
				
				// Le r�pertoire images/ doit �tre au m�me niveau que src/ et
				// les fichiers .gif doivent s'y trouver.
				cartes[pos] .image =
						new ImageIcon("images/" + 
								figures[fig] + couleurs[i] + ".gif"); 	
				
				cartes[pos].numero = fig + 1;	
				
				cartes[pos].couleur = lesCouleurs[i];
				
				pos++;
			}			
		}
		
		return cartes;
	}
}
