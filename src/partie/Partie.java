package partie;

import java.util.Scanner;
import description.Couleur;
import description.Description;
import description.TypeAlea;
import strategie.JoueurSimple;

/**
 * Coeur du jeu où se déroule toute les actions Aléatoire et le déroulement de la partie
 * @author Quentin, Manon, Virgil, Guillaume
 *
 */
public class Partie {
	private Description description;
	private String[] nom_joueurs;
	private Donnees[] donnees_joueurs;
	private Tour[] tours;


	/**
	 * Initialisation de la partie 
	 * @param description répresentant l'ensemble des Taches
	 * @param nom_joueurs, les différents de la partie
	 */
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
		for(int i=0; i<nom_joueurs.length; i++) {
			donnees_joueurs[i] = new Donnees(nom_joueurs[i], new JoueurSimple());
		}
	}

	/**
	 * Permet de savoir sur quelle réalisation appliquer les aléas en fonction du tour
	 * Si il y a plus de tour que le réalisations, permet de bloquer le chiffre à celui correspondant à la dernière réalisation. 
	 * @return le numéro de la réalisation
	 */
	public int unTourUneTache() {
		if(this.donnees_joueurs[0].getNumeroTour() >= this.donnees_joueurs[0].getRealisation().size()) {
			return this.donnees_joueurs[0].getRealisation().size()-1;
		}
		return this.donnees_joueurs[0].getNumeroTour();
	}

	/**
	 * Utilise la méthode indiquée en fonction du tour 
	 * @param tour, le tour courant
	 */
	public void jouerTour(Tour tour) {
		if(tour.equals(Tour.JALON)) this.tourJalon();
		if(tour.equals(Tour.ALEA)) this.tourSemaine(Couleur.tirage());
	}

	/**
	 * Fait passer le tour aux joueurs 
	 */
	public void passerTour() {

		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].FinDuTour();
		}

	}

	/**
	 * Renvoie la vue en fonction du nom donné
	 * @param nom, le nom du joueur
	 * @return les Données du joueur
	 */
	public Donnees getVueJoueur(String nom) {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			if(donnees_joueurs[i].getNom().equals(nom)) return donnees_joueurs[i];
		}
		return null;
	}

	/**
	 * Fonctionnement d'un tour Semaine (ou Alea) pour tout les joueurs
	 *  Réponse à un tirage de Couleur
	 * @param couleur, la couleur de l'Alea tiré
	 */
	public void tourSemaine(Couleur couleur) {
		System.out.println("Tour Semaine !");
		for (int i = 0; i < donnees_joueurs.length; i++) {
			Realisation salur = donnees_joueurs[i].getRealisation(unTourUneTache());
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

			System.out.println("La couleur " + couleur.toString() + " a été tirée cette semaine" + '\n' + "_________________________________");
		}
	}


	/**
	 * Fonctionnement d'un tour Quizz
	 */
	public void tourQuizz() {
	}


	/**
	 * Fonctionnement d'un tour Jalon
	 */
	public void tourJalon() {
		System.out.println("Tour jalon ! ");
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].getStrategie().jouerJalon(donnees_joueurs[i]);	
			
		}
	}

	/**
	 * Fonctionnement d'un tour Final
	 */
	public void tourFinal() {
	}


	public static void main(String[] args) {
		String oui = "salut";
		Scanner scanner = new Scanner(System.in);
		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		for (int i = 0; i < partie.tours.length; i++) {
			partie.jouerTour(partie.tours[i]);				
				do {
					System.out.println("Continuer (ok)");
					oui = scanner.nextLine();
				}while(!oui.equals("ok"));
			
		}
		scanner.close();
	}



}