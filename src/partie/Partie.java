package partie;


import description.Couleur;
import description.TypeAlea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import strategie.JoueurSimple;

/**
 * Coeur du jeu où se déroule toute les actions Aléatoire et le déroulement de la partie
 * @author Quentin, Manon, Virgil, Guillaume
 *
 */
public class Partie extends Application{
	private static final String[] nom_joueurs = new String[] {"Joueur 1"};
	private Donnees[] donnees_joueurs;
	private Tour[] tours;
	private int nbToursSemaines;


	/**
	 * Initialisation de la partie 
	 * @param description répresentant l'ensemble des Taches
	 * @param nom_joueurs, les différents de la partie
	 */
	public Partie() {
		nbToursSemaines = -1;
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
		return this.donnees_joueurs[0].getNumeroTour()-1;
	}

	/**
	 * Utilise la méthode indiquée en fonction du tour 
	 * @param tour, le tour courant
	 */
	public void jouerTour(Tour tour) {
		if(tour.equals(Tour.JALON)) this.tourJalon();
		if(tour.equals(Tour.ALEA)) {
			this.donnees_joueurs[0].getStrategie().reset(this.donnees_joueurs[0]);
			this.tourSemaine(Couleur.tirage());

		}
		if(tour.equals(Tour.FINAL)) {
			this.donnees_joueurs[0].getStrategie().reset(this.donnees_joueurs[0]);
			this.tourFinal();
		}if(tour.equals(Tour.QUIZZ)) {
			this.donnees_joueurs[0].getStrategie().reset(this.donnees_joueurs[0]);
			this.tourQuizz();
		}
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
		nbToursSemaines++;
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].label_tour.setText("TOUR SEMAINE");
			Realisation currentRealisation = donnees_joueurs[i].getRealisation(nbToursSemaines);
			if(!currentRealisation.isProtected(couleur) && !currentRealisation.estTerminee()) {
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.COUT)) {
					donnees_joueurs[i].depense(currentRealisation.getTache().getAlea(couleur).getGravite()*10);
					donnees_joueurs[i].getRealisation().get(nbToursSemaines).QuelleCouleur(couleur).setStyle("-fx-background-color:  black; -fx-alignment: center; -fx-text-fill: white;-fx-font-size:10;");
				}	
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.QUALITE)) {
					donnees_joueurs[i].baisseQualite(currentRealisation.getTache().getAlea(couleur).getGravite());
					donnees_joueurs[i].getRealisation().get(nbToursSemaines).QuelleCouleur(couleur).setStyle("-fx-background-color:  black; -fx-alignment: center; -fx-text-fill: white;-fx-font-size:10;");
				}
				if(currentRealisation.getTache().getAlea(couleur).getType().equals(TypeAlea.DELAI)) {
					currentRealisation.ajoutDelai(currentRealisation.getTache().getAlea(couleur).getGravite());
					donnees_joueurs[i].getRealisation().get(nbToursSemaines).QuelleCouleur(couleur).setStyle("-fx-background-color:  black; -fx-alignment: center; -fx-text-fill: white;-fx-font-size:10;");
				}
			}
			donnees_joueurs[i].getStrategie().jouerSemaine(donnees_joueurs[i]);	
		}
	}

	/**
	 * Fonctionnement d'un tour Quizz
	 */
	public void tourQuizz() {		
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].label_tour.setText("TOUR QUIZZ");
			donnees_joueurs[i].getStrategie().jouerQuizz(donnees_joueurs[i]).show();;
		}
	}

	/**
	 * Fonctionnement d'un tour Jalon
	 */
	public void tourJalon() {
		for (int i = 0; i < donnees_joueurs.length; i++) {
			donnees_joueurs[i].label_tour.setText("TOUR JALON");
			donnees_joueurs[i].getStrategie().jouerJalon(donnees_joueurs[i]);	
		}
	}

	/**
	 * Fonctionnement d'un tour Final 
	 */
	public void tourFinal() {
		for(int i = 0; i < donnees_joueurs.length; i++) {
			for(Realisation r : donnees_joueurs[i].getRealisation()){
					r.setAvancement(r.getDuree_reelle());
					r.setTerminee();
			}
			int part= ((32 + (24 - donnees_joueurs[i].getCheminCritique()) * (donnees_joueurs[i].getCaisse()+20))/8000)-(100-donnees_joueurs[i].getQualite());
			donnees_joueurs[i].getStrategie().jouerSemaine(donnees_joueurs[i]);

			donnees_joueurs[i].label_tour.setText("Terminé ! Votre part de marché est de : "+part+"%");
			donnees_joueurs[i].finDeTour.setText("Quitter le jeu");
			donnees_joueurs[i].finDeTour.setStyle("-fx-background-color: #a01010; -fx-text-fill: white;");
			donnees_joueurs[i].finDeTour.setOnAction(e ->{
				System.exit(0);
			});
		}
	}
	
	/**
	 * Coeur du jeu : permet de jouer. 
	 * @param p, la partie en cours
	 */
	public void play(Partie p)  {
		for( int j = 0; j < p.donnees_joueurs.length; j++) {
		for (int i = 0 ; i < p.tours.length; i++) {
			p.jouerTour(p.tours[i]);
			p.donnees_joueurs[j].pause();		
		}
		}
	}


	public void start(Stage primaryStage) throws Exception {
		
		Partie p = new Partie();	
		Scene scene = new Scene(p.donnees_joueurs[0].getVBox(),1550 , 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("HORIZONS G3");
		primaryStage.setResizable(false);
		primaryStage.show();
		play(p);
	}

	public static void main(String[] args)  {
			launch(args);
	}
}