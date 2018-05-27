package partie;
import description.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Realisation extends Region {
	private int duree_reelle;
	private boolean acceleration;
	private HashMap<Couleur,Boolean> protections;
	private Etat etat;
	private int avancement;
	private UneTache tamerelatache;

	public Realisation(Tache t) {
		tamerelatache = (UneTache)t;
		duree_reelle=t.getDureeInitiale();
		acceleration=false;
		protections=new HashMap<>();
		protections.put(Couleur.ROUGE, false);
		protections.put(Couleur.JAUNE,false);
		protections.put(Couleur.VERT,false);
		etat= Etat.NON_ENTAMEE;

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
