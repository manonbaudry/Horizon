package description;

import java.util.Collection;
import java.util.HashMap;


/*
 * Cette classe implémente l'interface Tache et reprend donc toutes les fonctions décrites dans celle-ci
 */
public class UneTache implements Tache {

	private String description;
	private int duree_initiale;
	private int duree_max;
	private String id;
	private HashMap<Couleur, Alea> map;
	
	
	
	public UneTache(String description, int duree_initiale, int duree_max, String id) {
		this.description = description;
		this.duree_initiale = duree_initiale;
		this.duree_max = duree_max;
		this.id = id;
	}

	public int coutAcceleration() {
		return 0;
	}

	public Alea getAlea(Couleur couleur) {
		return map.get(couleur);
	}

	public String getDescription() {
		return this.description;
	}

	public int getDureeInitiale() {
		return this.duree_initiale;
	}
	
	public int getDureeMax() {
		return this.duree_max;
	}

	public String getId() {
		return this.id;
	}

	public Collection<Tache> getPredecesseurs() {
		return null;
	}

	public Collection<Tache> getSuccesseurs() {
		return null;
	}

}
