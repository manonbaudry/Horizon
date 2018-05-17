package partie;

import javax.swing.JOptionPane;

import com.sun.java_cup.internal.runtime.Scanner;

import description.Alea;
import description.Couleur;
import description.Description;
import description.TypeAlea;
import strategie.JoueurSimple;

public class Partie {
	private Description description;
	private String[] nom_joueurs;
	private Donnees[] donnees_joueurs;
	private Tour[] tours;
	private Tour letour;


	public Partie(Description description, String[] nom_joueurs) {
		this.description = description;
		this.nom_joueurs = nom_joueurs;
		donnees_joueurs = new Donnees[nom_joueurs.length];
		this.tours = new Tour[] {
				Tour.JALON,
				Tour.ALEA,
				Tour.ALEA,
				Tour.ALEA,
				Tour.ALEA,
				Tour.QUIZZ,
				Tour.JALON,
				Tour.ALEA,
				Tour.ALEA,
				Tour.ALEA,
				Tour.QUIZZ,
				Tour.ALEA,
				Tour.FINAL};
		this.letour = tours[0];
		for(int i=0; i<nom_joueurs.length; i++) {
			donnees_joueurs[i] = new Donnees(nom_joueurs[i], new JoueurSimple());
		}
	}

	public void passerTour() {
		
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].FinDuTour();
		}
		letour = tours[donnees_joueurs[0].getNumeroTour()];
		
	}

	public Donnees getVueJoueur(String nom) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			if(donnees_joueurs[i].getNom().equals(nom)) return donnees_joueurs[i];
		}
		return null;
	}

	public void tourSemaine(Couleur couleur) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			Realisation salur = donnees_joueurs[i].getRealisation(donnees_joueurs[i].getNumeroTour()-1);
			if(!salur.isProtected(couleur)) {
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.COUT)) {
					donnees_joueurs[i].depense(salur.getTache().getAlea(couleur).getGravite()*10);
				}	
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.QUALITE)) {
					donnees_joueurs[i].baisseQualite(salur.getTache().getAlea(couleur).getGravite());
				}
				if(salur.getTache().getAlea(couleur).getType().equals(TypeAlea.DELAI)) {
					salur.ajoutDelai(salur.getTache().getAlea(couleur).getGravite());
				}
			}
			donnees_joueurs[i].getStrategie().jouerSemaine(donnees_joueurs[i]);			
		}
	}
	
	public void tourQuizz() {
	}
	
	public void tourJalon() {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].getStrategie().jouerJalon(donnees_joueurs[i]);			
		}
		this.passerTour();
	}
	
	public void tourFinal() {
		
	}
	
	public static void main(String[] args) {
		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		partie.tourJalon();
		partie.tourSemaine(Couleur.VERT);
		partie.tourSemaine(Couleur.VERT);
		partie.tourSemaine(Couleur.VERT);
		partie.tourSemaine(Couleur.VERT);
		partie.tourSemaine(Couleur.VERT);

		
		
	}

}