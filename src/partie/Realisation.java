package partie;
import description.*;

import java.util.ArrayList;
import java.util.HashMap;

import description.Couleur;
import description.Tache;

public class Realisation {
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
		avancement = 0;
	}
	
	
	/**
	 * @return the avancement
	 */
	public int getAvancement() {
		return avancement;
	}
	
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String protec="\t "+protections.get(Couleur.ROUGE)+"\t     "+protections.get(Couleur.JAUNE)+"\t   "+protections.get(Couleur.VERT);
		return tamerelatache.toString()
				+ protec + "\n\tDurée : " + duree_reelle + "\tAcceleration : " + acceleration+ " \n\tEtat : " + etat;
	}
	
	
	public ArrayList<Tache> getPredecesseurs(){
		return tamerelatache.getPredecesseurs();
	}
	public ArrayList<Tache> getSuccesseurs(){
		return tamerelatache.getSuccesseurs();
	}


	public UneTache getTache() {
		return tamerelatache;
	}
	
	
	
}
