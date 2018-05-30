package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;
import description.Tache;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import strategie.Strategie;

/**
 * Rassemble toute les informations du joueur sur l'état de la partie propre à chaque joueur
 * @author sakid
 *
 */
public class Donnees implements DonneesJoueur, VueJoueur{
	private int caisse;
	private String nom;
	private int qualite;
	private ArrayList<Realisation> realisations;
	private Strategie strategie;
	private Description description;
	private int numeroTour;
	private boolean realisationUnePassee = false;
	private HBox hbox = new HBox();

	public Donnees(String nom, Strategie strategie) {
		super();
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
	 * @param r la réalistion 
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
	 * Baisse la qualité de 2% par niveau de gravité
	 * @param gravite, la gravité de la baisse
	 */
	public void baisseQualite(int gravite) {
		this.qualite=(int)(qualite*(0.98*gravite));
	}

	/**
	 * Retire de la caisse les dépenses faites par le joueur ou les Aléas de cout
	 * @param la somme à prélever
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
			if(realisations.get(i).getEtat().equals(Etat.EN_COURS)) {
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
			if(!realisation.getAcceleration() && realisation.getTache().getId().equals(id)) {
				realisation.setAcceleration(active);
				caisse -= realisation.getTache().getCoutAcceleration();
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
			if(realisation.getTache().getId().equals(id)) realisation.getProtections().put(couleur, active);

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
		if(! realisationUnePassee) {
			realisations.get(0).setEnCours();	
			//realisations.get(0).incrementAvancement();
			realisationUnePassee = true;
		}
		for (Realisation realisation : realisations) {
			if(!realisation.getEtat().equals(Etat.EN_COURS) && !realisation.estTerminee() && realisationUnePassee && PrecedentesTerminees(realisation) &&  ! realisation.equals(realisations.get(0))) {
				realisation.setEnCours();
			}			
		}

	}

	/**
	 * Passe l'état de la tache à TERMINE si sa durée réelle et son avancement sont égaux
	 */
	private void setTermine() {
		for(Realisation r : realisations) {
			if(r.getDuree_reelle() == r.getAvancement()) {
				r.setTerminee();
			}
		}

	}

	public String toString() {
		return "Donnees [caisse=" + caisse + ", nom=" + nom + ", qualite=" + qualite + ", realisations=" + realisations
				+ ", strategie=" + strategie + ", description=" + description + ", numeroTour=" + numeroTour + "]";
	}
 
	public int getCheminCritique() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public HBox getHBox() {
		VBox vbox = new VBox();
		Label caisse = new Label("La caisse : "+this.caisse);
		vbox.getChildren().add(caisse);
		hbox.getChildren().addAll(this.getStrategie().getPane(this), caisse);
		return hbox;
	}
	

}
