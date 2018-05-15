import partie.*;
import strategie.*;

public class EssaiPartie {

	public static void main(String [] args) {
		Donnees joueur = new Donnees("j1", new JoueurSimple());
		joueur.getStrategie().jouerSemaine(joueur);
		
		
		}
}
