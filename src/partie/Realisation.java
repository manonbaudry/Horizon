package partie;


import description.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class Realisation {
	private int duree_reelle;
	private boolean acceleration;
	private HashMap<Couleur,Boolean> protections;
	private Etat etat;
	private int avancement;
	private UneTache tamerelatache;
	@FXML private  GridPane pane;
	@FXML private Label id, description, cout_acceleration, duree_initiale, duree_max, labelduree_reelle, alea_rouge, alea_jaune, alea_vert, labelacceleration, labeletat, labelavancement;

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
	
	public void display() {
		pane = new GridPane();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("RealisationGraphique.fxml"));
		try {
			pane = (GridPane)loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		start();
	}
	
	public void  start() {
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        id.setText(getTache().getId());
                    }

                });

            }

        }, 500);
	}
	
	
	public GridPane getPane() {
		return pane;
	}

	@FXML
	void initialize() {
	System.out.println("Initialisation");

	/*	id = new Label(this.getTache().getId());
		description = new Label(this.getTache().getDescription());
		cout_acceleration = new Label(this.getTache().getCoutAcceleration()+ " €");
		duree_initiale = new Label(this.getTache().getDureeInitiale()+ "");
		duree_max = new Label(this.getTache().getDureeMax()+"");
		alea_rouge = new Label(this.getTache().getAlea(Couleur.ROUGE).getNom());
		alea_jaune = new Label(this.getTache().getAlea(Couleur.JAUNE).getNom());
		alea_vert = new Label(this.getTache().getAlea(Couleur.VERT).getNom());
		labelacceleration = new Label(this.getAcceleration()+ "");
		labeletat = new Label(this.getEtat().name());
		labelavancement = new Label(this.getAvancement()+ "");
		labelduree_reelle = new Label(this.getDuree_reelle()+"");
		id.setText(this.getTache().getId());
		description.setText(this.getTache().getDescription());*/
		//id.setText(getTache().getId());

		/*pane.add(id, 0, 0);
		pane.add(description, 1, 0);
		pane.add(cout_acceleration, 2, 0);
		pane.add(duree_initiale, 0, 1);
		pane.add(duree_max, 2, 1);
		pane.add(alea_rouge, 0, 2);
		pane.add(alea_jaune, 1, 2);
		pane.add(alea_vert, 2, 2);
		pane.add(labelduree_reelle, 0, 3);
		pane.add(labelacceleration, 2, 3);
		pane.add(labeletat, 0, 4);
		pane.add(labelavancement, 2, 4);*/

	}
	


	public int calculPlusTot() {
		int res = 0;
		for(int i = 0; i < this.getPredecesseurs().size(); i++) {
			if(this.getPredecesseurs().get(i).getDureeInitiale() > res)
				res = this.getPredecesseurs().get(i).getDureeInitiale();
		}
		System.out.println("----" + res + " le plus grand");
		return res;
	}

	public int calculPlusTard() {
		int res = 0;
		for(int i = 0; i < this.getPredecesseurs().size(); i++) {
			if(this.getPredecesseurs().get(i).getDureeMax() > res)
				res = this.getPredecesseurs().get(i).getDureeMax();
		}
		return res;
	}

	public int lePlusLongSuccesseur(ArrayList<Tache> tache) {
		int res = 0;
		for(Tache t: this.getTache().getSuccesseurs()) {
			if(t.getDureeInitiale() > res) res = t.getDureeInitiale();
		}
		return res;
	}


	/**
	 * @return vrai si la réalisation courante est terminées, faux sinon
	 */
	public boolean estTerminee() {
		if(this.getEtat().equals(Etat.TERMINE)) return true;
		return false;
	}

	public Realisation laPlusLongueInitiale(Realisation r) {
		if(this.getTache().sontEnParallele(r.getTache())) {
			if( this.getTache().getDureeInitiale() > r.getTache().getDureeInitiale()) {
				//System.out.println(this.getTache().getId() + " initiale plus longue que " + r.getTache().getId());
				return this;
			}else {
				//System.out.println(this.getTache().getId() + " initiale moins longue que " + r.getTache().getId());
				return r;
			}
		}
		return new Realisation(new UneTache("", 0, "", 0));
	}

	public Realisation laPlusLongueMax(Realisation r) {
		if(this.getTache().sontEnParallele(r.getTache())) {
			if( this.getTache().getDureeMax() > r.getTache().getDureeMax()) {
				System.out.println(this.getTache().getId() + " max plus longue que " + r.getTache().getId());
				return this;
			}else {
				//System.out.println(this.getTache().getId() + " max moins longue que " + r.getTache().getId());
				return r;
			}
		}
		return new Realisation(new UneTache("", 0, "", 0));
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
		this.incrementAvancement();
	}

	/**
	 * passe l'état de la réalisation courant à TERMINEE
	 */
	public void setTerminee() {
		this.etat = Etat.TERMINE;

	}




}
