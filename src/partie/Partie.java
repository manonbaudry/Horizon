package partie;

import description.Alea;
import description.Couleur;
import description.Description;
import description.TypeAlea;
import strategie.JoueurSimple;

public class Partie {
	private Description description;
	private String[] nom_joueurs;
	private Donnees[] donnees_joueurs;


	public Partie(Description description, String[] nom_joueurs) {
		this.description = description;
		this.nom_joueurs = nom_joueurs;
		donnees_joueurs = new Donnees[nom_joueurs.length];
		for(int i=0; i<nom_joueurs.length; i++) {
			donnees_joueurs[i] = new Donnees(nom_joueurs[i], new JoueurSimple());
		}
	}

	public void passerTour() {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].FinDuTour();
		}
	}

	public Donnees getVueJoueur(String nom) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			if(donnees_joueurs[i].getNom().equals(nom)) return donnees_joueurs[i];
		}
		return null;
	}

	public void tourSemaine(Couleur couleur) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			Realisation salur = donnees_joueurs[i].getRealisation(donnees_joueurs[i].getNumeroTour());
			if(!salur.isProtectedSaraConnor(couleur)) {
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.COUT)) {
					System.out.println("tg virgil cout");
					donnees_joueurs[i].depense(salur.getTache().getAlea(couleur).getGravite());
				}	
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.QUALITE)) {
					System.out.println("tg virgil qualite");
					donnees_joueurs[i].baisseQualite(salur.getTache().getAlea(couleur).getGravite());
				}
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.DELAI)) {
					System.out.println("tg virgil delai ");
					salur.ajoutDelai(salur.getTache().getAlea(couleur).getGravite());
				}
			}
		}
	}
	
	public String toString(){
		return "oui";
	}
	
	public static void main(String[] args) {
		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		System.out.println(partie);
	}

}