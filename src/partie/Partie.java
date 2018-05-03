package partie;

import description.Couleur;
import description.Description;
import strategie.JoueurSimple;

public class Partie {
	private Description description;
	private String[] nom_joueurs;
	private Donnees[] donnees_joueurs;
	
	
	public Partie(Description description, String[] nom_joueurs) {
		this.description = description;
		this.nom_joueurs = nom_joueurs;
		for(int i=0; i<nom_joueurs.length; i++) {
			donnees_joueurs[i] = new Donnees(nom_joueurs[i], new JoueurSimple());
		}
	}
	
	public void passerTour() {
		
	}
	
	public Donnees getVueJoueur(String nom) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			if(donnees_joueurs[i].getNom().equals(nom)) return donnees_joueurs[i];
		}
		return null;
	}
	
	public void tourSemaine(Couleur couleur) {

	}
	
	
	


}
