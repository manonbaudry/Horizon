package partie;

import description.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Une réalisation contient toutes les informations sur une tâche.
 */
public class Realisation{
	private int duree_reelle;
	private boolean acceleration;
	private HashMap<Couleur,Boolean> protections;
	private Etat etat;
	private int avancement;
	private UneTache tamerelatache;
	private  GridPane pane;
	public Label id, description, cout_acceleration, duree_initiale, duree_max, alea_rouge, alea_jaune, alea_vert, labeletat, labelavancement;

	public Realisation() {};
	public Realisation(Tache t) {
		tamerelatache = (UneTache)t;
		duree_reelle=t.getDureeInitiale();
		acceleration=false;
		protections=new HashMap<>();
		protections.put(Couleur.ROUGE, false);
		protections.put(Couleur.JAUNE,false);
		protections.put(Couleur.VERT,false);
		etat= Etat.NON_ENTAMEE;	
		display();

	}

	/**
	 * Permet de renvoyer le label correspondant à la couleur passé en paramètre
	 * @param color, la couleur 
	 * @return le label correspondant ) la couleur
	 */
	public Label QuelleCouleur(Couleur color) {
		if(color.equals(Couleur.JAUNE)) return alea_jaune;
		if(color.equals(Couleur.ROUGE)) return alea_rouge;
		return alea_vert;
	}

	/**
	 * Affichage graphique d'une réalisation
	 */
	public void display() {
		pane = new GridPane();
		pane.setPrefSize(400, 100);
		pane.setGridLinesVisible(true);
		initialize();		
	}

	/**
	 * 
	 * @return la gridpane représentant la réalisation
	 */
	public GridPane getPane() {
		return pane;
	}

	/**
	 * Initialise l'affichage graphique de la réalisation :
	 * - Créer les labels
	 * - Décore les labels
	 * - Place les labels dans le gridPane
	 */
	void initialize() {
		id = new Label(this.getTache().getId());
		id.setStyle("-fx-background-color: #c9cacc");
		id.setPrefWidth(80);

		description = new Label(this.getTache().getDescription());
		description.setPrefWidth(90);
		description.setStyle("-fx-background-color: #c9cacc");

		cout_acceleration = new Label(this.getTache().getCoutAcceleration()+ " €");
		cout_acceleration.setPrefWidth(80);
		cout_acceleration.setStyle("-fx-background-color: #c9cacc;-fx-text-fill: #478752;");

		alea_rouge = new Label(this.getTache().getAlea(Couleur.ROUGE).getNom() +" : " + this.getTache().getAlea(Couleur.ROUGE).getType());
		alea_rouge.setPrefWidth(80);
		alea_rouge.setStyle("-fx-background-color:  #dd4949; -fx-alignment: center; -fx-text-fill: white; -fx-font-size:10;");

		alea_jaune = new Label(this.getTache().getAlea(Couleur.JAUNE).getNom() +" : "+ this.getTache().getAlea(Couleur.JAUNE).getType());
		alea_jaune.setPrefWidth(90);
		alea_jaune.setStyle("-fx-background-color:   #f4e838; -fx-alignment: center ; -fx-font-size:10; ");

		alea_vert = new Label(this.getTache().getAlea(Couleur.VERT).getNom() +" : "+ this.getTache().getAlea(Couleur.VERT).getType());
		alea_vert.setPrefWidth(90);
		alea_vert.setStyle("-fx-background-color:  #5b9960; -fx-alignment: center; -fx-text-fill: white; -fx-font-size:10;");

		labeletat = new Label("" + this.getEtat());
		labeletat.setStyle(" -fx-text-fill: black; -fx-font-size:9;");
		labeletat.setPrefSize(100, 30);

		duree_initiale = new Label( "Min : " +this.getTache().getDureeInitiale());
		duree_max = new Label("Max : "+this.getTache().getDureeMax());
		labelavancement = new Label(this.getAvancement()+ " / " + this.getDuree_reelle());

		pane.getColumnConstraints().add(new ColumnConstraints(80));
		pane.getColumnConstraints().add(new ColumnConstraints(90));
		pane.getColumnConstraints().add(new ColumnConstraints(80));
		pane.add(id, 0, 0);
		pane.add(description, 1, 0);
		pane.add(cout_acceleration, 2, 0);
		pane.add(duree_initiale, 0, 1);
		pane.add(duree_max, 2, 1);
		pane.add(alea_rouge, 0, 2);
		pane.add(alea_jaune, 1, 2);
		pane.add(alea_vert, 2, 2);
		pane.add(labeletat, 0, 4);
		pane.add(labelavancement, 2, 4);

		for(Node node : pane.getChildren()) {
			GridPane.setMargin(node, new Insets(1));
		}
	}

	/**
	 * @param l le label
	 * @param t, le texte
	 */
	public void setText(Label l, String t) {
		l.setText(t);
	}

	/**
	 * @return vrai si la réalisation courante est terminées, faux sinon
	 */
	public boolean estTerminee() {
		if(this.getEtat().equals(Etat.TERMINE)) return true;
		return false;
	}

	/**
	 * @return the avancement 
	 */
	public int getAvancement() {
		return avancement;
	}

	public void setAvancement(int n) {
		this.avancement=n;
	}

	/**
	 * 
	 * @param couleur
	 * @return vrai si la réalisation courante est protégée de la couleur en paramètre, faux sinon
	 */
	public boolean isProtected(Couleur couleur) {
		return protections.get(couleur);
	}

	/**
	 * @param avancement the avancement to set
	 */
	public void incrementAvancement() {
		this.avancement ++;
	}


	/**
	 * @return the acceleration
	 */
	public boolean getAcceleration() {
		return acceleration;
	}


	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(boolean acceleration) {
		this.acceleration = acceleration;
		duree_reelle --;
	}

	/**
	 * Ajout du délai en fonction du niveau de gravité (+1 par niveau de gravité)
	 * @param gravitay, le niveau de gravité
	 */
	public void ajoutDelai(int gravitay) {
		this.duree_reelle += gravitay;
	}


	/**
	 * @return the duree_reelle
	 */
	public int getDuree_reelle() {
		return duree_reelle;
	}

	/**
	 * @param duree_reelle the duree_reelle to set
	 */
	public void setDuree_reelle(int duree_reelle) {
		this.duree_reelle = duree_reelle;
	}

	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}


	/**
	 * @return the protections
	 */
	public HashMap<Couleur, Boolean> getProtections() {
		return protections;
	}

	@Override
	public String toString() {
		String protec="\t "+protections.get(Couleur.ROUGE)+" (R) " + "   "+protections.get(Couleur.JAUNE)+" (J) " + "   "+protections.get(Couleur.VERT) + " (V) ";
		return tamerelatache.toString()
				+ protec + "\n\tDurée : " + duree_reelle + "\tAcceleration : " + acceleration+ " \n\tEtat : " + etat;
	}
	/**
	 * 
	 * @return les predecesseurs de la tâche courrante
	 */

	public ArrayList<Tache> getPredecesseurs(){
		return tamerelatache.getPredecesseurs();
	}
	/**
	 * 
	 * @return les successeurs de la tâche courrante
	 */
	public ArrayList<Tache> getSuccesseurs(){
		return tamerelatache.getSuccesseurs();
	}

	/**
	 * 
	 * @return la tâche liée a la réalisation courante
	 */
	public UneTache getTache() {
		return tamerelatache;
	}

	/**
	 * passe l'état de la réalisation courant à EN COURS
	 */
	public void setEnCours() {
		this.etat = Etat.EN_COURS;
	}

	/**
	 * passe l'état de la réalisation courant à TERMINEE
	 */
	public void setTerminee() {
		this.etat = Etat.TERMINE;
	}

	public void setImminent () {
		this.etat = Etat.IMMINENT;
	}

}
