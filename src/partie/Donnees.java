package partie;

import java.util.ArrayList;
import com.sun.javafx.tk.Toolkit;
import description.Couleur;
import description.Description;
import description.Tache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import strategie.Strategie;

/**
 * Rassemble toute les informations du joueur sur l'état de la partie propre à chaque joueur
 *
 */
public class Donnees implements DonneesJoueur, VueJoueur{
	private int caisse;
	private String nom;
	private int qualite;
	private ArrayList<Realisation> realisations;
	private Strategie strategie;
	private Description description;
	public int numeroTour;
	private HBox hbox = new HBox();
	private VBox big = new VBox();
	public int tourd;
	public Label label_caisse, joueur, tour, qualité, label_tour;
	public Button finDeTour; 
	public ComboBox<String> cbb;

	/**
	 * Constructeur des données du joueur :
	 * - initialise la caisse, la qualité
	 * - crée les réalisations à partir des taches de la description
	 * - donne au joueur sa stratégie
	 * @param nom le nom du joueur
	 * @param strategie la stratégie
	 */
	public Donnees(String nom, Strategie strategie) {
		super();
		tourd = 0;
		this.caisse = 300;
		this.nom = nom;
		this.qualite = 100;
		description= new Description();
		realisations = new ArrayList<>();
		numeroTour = 0;
		for(Tache t : description.getListe_taches()) {
			realisations.add(new Realisation(t));
		} 
		this.strategie = strategie;
		display();
	}

	/**
	 * @return la collection de réalisation
	 */
	public ArrayList<Realisation> getRealisation(){
		return realisations;
	}

	/**
	 * Déclenche la fin du fin et actualise la partie 
	 */
	public void actualisation() {
		setTermine();
		setImminent();
		setEnCours();

	}

	/**
	 * Vérifie si les prédécesseurs de la réalistion donnée en paramètre sont terminés
	 * @param r la réalisation 
	 * @return Vrai si touts les prédécesseurs de r sont en état TERMINE, faux sinon
	 */
	public boolean PrecedentesTerminees(Realisation r) {
		for(Realisation pred : getPredecesseurs(r)) {
			if(!pred.estTerminee()) return false;
		}
		return true;
	}

	/**
	 * @param r la réalisation 
	 * @return la collection des prédécesseurs de r 
	 */
	private ArrayList<Realisation> getPredecesseurs(Realisation r){
		ArrayList<Realisation> pred = new ArrayList<>();
		for (int i = 0; i < realisations.indexOf(r); i++) {
			if(realisations.get(i).getTache().isPrecedesseur(r.getTache())) {
				pred.add(realisations.get(i));				
			}
		}
		return pred;
	}
	/**
	 *
	 * @param r la réalisation
	 * @return	une arraylist des successeurs de r
	 */
	public ArrayList<Realisation> getSuccesseurs(Realisation r){
		ArrayList<Realisation> succ = new ArrayList<>();
		for (int i = realisations.indexOf(r); i < realisations.size(); i++) {
			if(realisations.get(i).getTache().isSuccesseur(r.getTache())) {
				succ.add(realisations.get(i));
			}
		}
		return succ;
	}


	/**
	 * Baisse la qualité de 2% par niveau de gravité
	 * @param gravite, la gravité de la baisse
	 */
	public void baisseQualite(int gravite) {
		this.qualite=qualite-2*gravite;
	}

	/**
	 * Retire de la caisse les dépenses faites par le joueur ou les Aléas de cout
	 * @param somme à prélever
	 */
	public void depense(int somme) {
		this.caisse-=somme;
	}

	/**
	 * @return la caisse du joueur
	 */
	public int getCaisse() {
		return this.caisse;
	}

	/**
	 * @return le nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return la qualité du qualité
	 */
	public int getQualite() {
		return this.qualite;
	}

	/**
	 * @param id, id de la réalisation
	 * @return la réalistion ayant pour id l'id donné en paramètre
	 */
	public Realisation getRealisation(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation;
		}
		return null;
	}

	/**
	 * @param numeroTour, le numero du tour en cours
	 * @return la réalistion ayant comme indice numeroTour dans la collection de réalisations
	 */
	public Realisation getRealisation(int numeroTour) {
		return realisations.get(numeroTour);
	}

	/**
	 * @return la stratégie du joueur
	 */
	public Strategie getStrategie() {
		return this.strategie;
	}


	/**
	 * Termine le tour, actualise la partie 
	 */
	public void FinDuTour() {
		for(int i = 0; i< realisations.size(); i++) {
			if(realisations.get(i).getEtat().equals(Etat.EN_COURS) || realisations.get(i).getEtat().equals(Etat.IMMINENT) ) {
				realisations.get(i).incrementAvancement();
			}
		}
		actualisation();
		numeroTour++;	

	}

	/**
	 * @param id, l'id de la réalisation
	 * @return l'avancement de la réalistion ayant pour id id, -1 sinon
	 */
	public int getCurrent(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation.getAvancement();
		}
		return -1;
	}

	/**
	 * @return l'id de la première réalisation
	 */
	public String getDebutId() {
		return realisations.get(0).getTache().getId();
	}

	/**
	 * @return la description du joueur
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @param id, l'id de la réalisation
	 * @return la durée réelle de la réalisation, -1 sinon
	 */
	public int getDuree(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return  realisation.getDuree_reelle();
		}
		return -1;
	}

	/**
	 * @param id, l'id de la réalisation
	 * @return l'état de la réalisation, null sinon
	 */
	public Etat getEtat(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation.getEtat();
		}
		return null;
	}

	/**
	 * @return l'id de la dernière réalisation
	 */
	public String getFinId() {
		return realisations.get(realisations.size()-1).getTache().getId();
	}

	/**
	 * Sert à exectuer l'action d'accélération
	 * @param id, l'id de la réalisation
	 * @param active, boolean qui active ou non l'accéleration
	 */
	public void setAcceleration(String id, boolean active) {
		for (Realisation realisation : realisations) {
			if(!realisation.getAcceleration() && realisation.getTache().getId().equals(id) && caisse >= realisation.getTache().getCoutAcceleration()) {
				realisation.setAcceleration(active);
				depense(realisation.getTache().getCoutAcceleration());
				realisation.cout_acceleration.setText("Accélerée");
				realisation.cout_acceleration.setStyle("-fx-background-color:  #087021; -fx-text-fill: white;-fx-font-size:11;");
				label_caisse.setText("La caisse : "+this.caisse + " €");
			}
		}
	}

	/**
	 * @param id, la réalistion que le joueur veut protéger 
	 * @param couleur, la couleur dont le joueur veut se protéger
	 * @param active, boolean qui active ou non l'accéleration
	 */
	public void setProtection(String id, Couleur couleur, boolean active) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id) && caisse >= 10 && !realisation.isProtected(couleur) ) {
				caisse -= 10;
				label_caisse.setText("La caisse : "+this.caisse + " €");
				realisation.getProtections().put(couleur, active);
				realisation.QuelleCouleur(couleur).setStyle("-fx-background-color:  #accbef; -fx-alignment: center; -fx-text-fill: black;-fx-font-size:10;");
			}
		}
	}

	/**
	 * @return le numéro du tour
	 */
	public int getNumeroTour() {
		return this.numeroTour;
	}

	/**
	 * Passe l'état des réalisation à EN COURS si :
	 * 	- elles ne sont pas terminées
	 * 	- la réalisation première est passée (celle-ci est gérée avant)
	 * 	- les réalisation précedant la réalisation sont bien TERMINEES
	 */
	private void setEnCours() {
		for (Realisation realisation : realisations) {
			if(!realisation.estTerminee() && realisation.getAvancement() > 0) realisation.setEnCours();
		}
	}

	private void setImminent() {
		for (Realisation realisation : realisations) {
			if(realisation.getAvancement() == 0 && !realisation.estTerminee() && PrecedentesTerminees(realisation)) {
				realisation.setImminent();
			}			
		}
	}

	/**
	 * Passe l'état de la tache à TERMINE si sa durée réelle et son avancement sont égaux
	 */
	private void setTermine() {
		for(Realisation r : realisations) {
			if(r.getDuree_reelle() <= r.getAvancement()) {
				r.setAvancement(r.getDuree_reelle());
				r.setTerminee();
			}
		}

	}

	public String toString() {
		return "Donnees [caisse=" + caisse + ", nom=" + nom + ", qualite=" + qualite + ", realisations=" + realisations
				+ ", strategie=" + strategie + ", description=" + description + ", numeroTour=" + numeroTour + "]";
	}

	/**
	 * 
	 * @return durée de réalisations de toutes les tâches (durée du projet)
	 */
	public int getDateFinDeProjet() {
		ArrayList<Realisation> cheminCritique = cheminCritique();
		int dureeMax = 0;
		for (Realisation r : cheminCritique) {
			dureeMax += r.getDuree_reelle();
		}
		return dureeMax;
	}

	/**
	 * Met en pause le jeu
	 */
	public void pause() {
		Toolkit.getToolkit().enterNestedEventLoop(finDeTour);
	}

	/**
	 * Fait reprendre le jeu à l'appui du bouton de fin de tour
	 */
	private void resume() {
		Toolkit.getToolkit().exitNestedEventLoop(finDeTour, null);
	}

	/**
	 * Créer l'affichage de l'interface graphique :
	 * - les différents attibuts des données 
	 * - le "plateau" de jeu venant de la stratégie du joueur
	 */
	public void display() {
		VBox donnees = new VBox();
		label_caisse = new Label("La caisse : "+this.caisse + " €");
		joueur = new Label("Joueur : " + this.getNom());
		tour = new Label( "Tour : " + this.getNumeroTour());
		qualité = new Label("Qualité : " + this.getQualite() + " %");
		label_tour = new Label("");
		label_tour.setPrefSize(Double.MAX_VALUE, 15);
		label_tour.setFont(new Font(54));
		label_tour.setAlignment(Pos.CENTER);
		finDeTour = new Button("Fin De tour");
	
		ObservableList<String> options = 
			    FXCollections.observableArrayList("Aléatoire", "Rouge","Jaune","Vert");
		 cbb = new ComboBox<>(options);
		 cbb.getSelectionModel().selectFirst();

		finDeTour.setOnAction(e ->{
			this.resume();
		});

		donnees.getChildren().addAll( tour, label_caisse, qualité, cbb, finDeTour);
		donnees.setPrefSize(2000, 100);
		donnees.setStyle("-fx-background-color: #e1e9f2; -fx-background-radius: 10; ");
		donnees.setPadding(new Insets(15));	
		for (Node node : donnees.getChildren()) {
			VBox.setMargin(node, new Insets(7));
		}

		hbox.getChildren().addAll(this.getStrategie().getPane(this), donnees);		
		big.getChildren().addAll(hbox, label_tour);
		VBox.setMargin(label_tour, new Insets(100, 0,0, 0));
		VBox.setMargin(qualité, new Insets(10,5,50,8));
		VBox.setMargin(finDeTour, new Insets(0, 0, 0, 25));
		HBox.setMargin(donnees, new Insets(5,5 , 0, 0));
		VBox.setMargin(cbb, new Insets(0,0,30,0));
		cbb.setPadding(new Insets(0,0,0,25));
		big.setStyle("-fx-font: 14px Roboto;");

	}
	
	public Couleur getChoix() {
		Couleur tamere;
		if(cbb.getValue().equals("Aléatoire")) {
			tamere = Couleur.tirage();
		}else if(cbb.getValue().equals("Rouge")) {
			tamere = Couleur.ROUGE;
		}else if(cbb.getValue().equals("Jaune")) {
			tamere = Couleur.JAUNE;
		}else {
			tamere = Couleur.VERT;
		}
		
		return tamere;
	}

	/**
	 * Met à jour la caisse, le tour et la qualité sur l'interface grpahique
	 */
	public void update() {
		label_caisse.setText("La caisse : "+this.caisse + " €");
		tour.setText(( "Tour : " + this.getNumeroTour()));
		qualité.setText(("Qualité : " + this.getQualite() + " %"));

	}
	/**
	 * @return la HBox utilisée pour illustrer les donnéees dans l'interface grpahique
	 */
	public VBox getVBox() {		
		return big;
	}

	/**
	 * 
	 * @return une Arraylist de Réalisations contenant le chemin critique.
	 */
	public ArrayList<Realisation> cheminCritique(){
		ArrayList<Realisation> cc = new ArrayList<>();
		ArrayList<Realisation> j1=new ArrayList<>();
		ArrayList<Realisation> j2=new ArrayList<>();
		ArrayList<Realisation> truc = new ArrayList<>();
		int dureeComposee = 0;
		cc.add(this.realisations.get(0));
		j1.addAll(getSuccesseurs(cc.get(0)));
		Realisation r = j1.get(0);
		for(Realisation real : j1) {
			if(real.getDuree_reelle() > r.getDuree_reelle()) r = real;
		}
		cc.add(r);
		for (int i = 4; i < realisations.size()-1; i++) {
			if(! j1.contains(realisations.get(i))) j2.add(realisations.get(i));
		}
		for (Realisation real : j2) {
			if(real.getDuree_reelle() >= dureeComposee) r = real;
			if(j2.contains(getSuccesseurs(real).get(0))){
				dureeComposee = real.getDuree_reelle() + getSuccesseurs(real).get(0).getDuree_reelle();
				truc.add(real);
				truc.add(getSuccesseurs(real).get(0));
			}
		}
		if(r.getDuree_reelle() < dureeComposee) {
			cc.addAll(truc);
		}else if(!cc.contains(r)){
			cc.add(r);
		}
		cc.add(realisations.get(realisations.size()-1));
		return cc;
	}
	
	public ArrayList<String> RealisationToString(){
		ArrayList<String> res = new ArrayList<>();
		for(Realisation r : this.cheminCritique()) {
			res.add(r.getTache().getId());
		}
		return res;
	}
	
}
