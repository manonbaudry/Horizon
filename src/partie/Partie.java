package partie;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import description.Couleur;
import description.Description;
import description.TypeAlea;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import strategie.JoueurSimple;

/**
 * Coeur du jeu où se déroule toute les actions Aléatoire et le déroulement de la partie
 * @author Quentin, Manon, Virgil, Guillaume
 *
 */
public class Partie extends Application{
	private Description description;
	private String[] nom_joueurs;
	private Donnees[] donnees_joueurs;
	private Tour[] tours;
	private int nbToursSemaines;

	/**
	 * Initialisation de la partie 
	 * @param description répresentant l'ensemble des Taches
	 * @param nom_joueurs, les différents de la partie
	 */
	public Partie(Description description, String[] nom_joueurs) {
		this.description = description;
		nbToursSemaines = -1;
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
	
	public Partie() {};

	/**
	 * Permet de savoir sur quelle réalisation appliquer les aléas en fonction du tour
	 * Si il y a plus de tour que le réalisations, permet de bloquer le chiffre à celui correspondant à la dernière réalisation. 
	 * @return le numéro de la réalisation
	 */
	public int unTourUneTache() {
		if(this.donnees_joueurs[0].getNumeroTour() >= this.donnees_joueurs[0].getRealisation().size()) {
			return this.donnees_joueurs[0].getRealisation().size()-1;
		}
		return this.donnees_joueurs[0].getNumeroTour()-1;
	}

	/**
	 * Utilise la méthode indiquée en fonction du tour 
	 * @param tour, le tour courant
	 */
	public void jouerTour(Tour tour) {
		if(tour.equals(Tour.JALON)) this.tourJalon();

		if(tour.equals(Tour.ALEA)) {
			this.tourSemaine(Couleur.tirage());
		}
		if(tour.equals(Tour.FINAL)) this.tourFinal();
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
		nbToursSemaines++;
		for (int i = 0; i < donnees_joueurs.length; i++) {
			Realisation currentRealisation = donnees_joueurs[i].getRealisation(nbToursSemaines);
			if(!currentRealisation.isProtected(couleur)) {
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.COUT)) {
					donnees_joueurs[i].depense(currentRealisation.getTache().getAlea(couleur).getGravite()*10);
				}	
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.QUALITE)) {
					donnees_joueurs[i].baisseQualite(currentRealisation.getTache().getAlea(couleur).getGravite());
				}
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.DELAI)) {
					currentRealisation.ajoutDelai(currentRealisation.getTache().getAlea(couleur).getGravite());
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
		//System.out.println(getClass().getResource("RealisationGraphique.fxml"));
		System.out.println("Tour jalon ! ");
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].getStrategie().jouerJalon(donnees_joueurs[i]);	

		}
	}

	/**
	 * Fonctionnement d'un tour Final 
	 */
	public void tourFinal() {
		for(int i = 0; i < donnees_joueurs.length; i++) {
			for(Realisation r : donnees_joueurs[i].getRealisation()){
				if(r.getEtat().equals(Etat.EN_COURS) || r.getEtat().equals(Etat.NON_ENTAMEE)) {
					r.setAvancement(r.getDuree_reelle());
				}

			}
			int part= ((32 + (24 - donnees_joueurs[i].getCheminCritique()) * (donnees_joueurs[i].getCaisse()+20))/8000)-(100-donnees_joueurs[i].getQualite());
			donnees_joueurs[i].getStrategie().jouerSemaine(donnees_joueurs[i]);
			System.out.println("Votre part de marché est de : "+part+"%");

		}
	}

	public void play(Stage primaryStage ) {
		/*String oui = "salut";
		Scanner scanner = new Scanner(System.in);
		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		for (int i = 0; i < partie.tours.length; i++) {
			partie.jouerTour(partie.tours[i]);							
		}
		scanner.close();*/	


	}
	
	public void init() {
	//	for(int i = 0; i < donnees_joueurs.length; i++) {
		//	donnees_joueurs[i].getStrategie().init(donnees_joueurs[i]);
	//	}
		//this.donnees_joueurs[0].getRealisation().get(0).setText(this.donnees_joueurs[0].getRealisation().get(0).id, this.donnees_joueurs[0].getRealisation().get(0).getTache().getId());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Partie p = new Partie(new Description(), new String[] {"Fred"});
		Scene scene = new Scene(p.donnees_joueurs[0].getHBox(), Double.MAX_VALUE, Double.MAX_VALUE);
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
	}
	
	public static void main(String[] args) {
		//Partie p = new Partie(new Description(), new String[] {"Fred"});
		//p.init();
		launch(args);
		
		
		
		
	}





}