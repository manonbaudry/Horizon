package partie;

import java.util.HashMap;

import description.Couleur;
import description.Tache;

public class Realisation {
	
	private Tache tache;
	private int duree_reelle;
	private boolean acceleration;
	private HashMap<Couleur,Boolean> protections;
	private Etat etat;

	public Realisation(Tache t) {
		tache=t;
		duree_reelle=tache.getDureeInitiale();
		acceleration=false;
		protections=new HashMap<>();
		protections.put(Couleur.ROUGE, false);
		protections.put(Couleur.JAUNE,false);
		protections.put(Couleur.VERT,false);
		etat= Etat.NON_ENTAMEE;
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
	 * @return the tache
	 */
	public Tache getTache() {
		return tache;
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
		return "Tache :" + tache + ", duree_reelle " + duree_reelle + ", acceleration : " + acceleration
				+ ", protections=" + protections + ", etat=" + etat + "]";
	}
	
	
	
	
}
