package description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Cette classe implémente l'interface Tache et reprend donc toutes les fonctions décrites dans celle-ci
 */
public class UneTache implements Tache {

	private String description;
	private int duree_initiale;
	private String id;
	private HashMap<Couleur, Alea> map;
	private int cout_aceleration;
	private ArrayList<Tache> predecesseurs;
	private ArrayList<Tache> successeurs;
	
	
	
	public UneTache(String description, int duree_initiale, String id) {
		this.description = description;
		this.duree_initiale = duree_initiale;
		this.id = id;
		this.map = new HashMap<>();
		predecesseurs=new ArrayList<>();
		successeurs=new ArrayList<>();
		map.put(Couleur.ROUGE, new UnAlea(new Random().nextInt(3)+1, TypeAlea.DELAI));
		map.put(Couleur.JAUNE, new UnAlea(new Random().nextInt(3)+1, TypeAlea.EURO));
		map.put(Couleur.VERT, new UnAlea(new Random().nextInt(3)+1, TypeAlea.QUALITE));
	}

	public int getCoutAcceleration() {
		return this.cout_aceleration;
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
		return this.duree_initiale + map.get(Couleur.ROUGE).getGravite();
	}

	public String getId() {
		return this.id;
	}
	
	/**
	 * Ajoute pour la Tache courante une Tache qui la precede
	 * @param t 
	 * 			la tache precedente
	 * @return
	 * 			vrai si la tache existe, faux sinon
	 */
	public boolean addPredecesseurs(Tache t) {
		for(Tache tache: predecesseurs) {
			if(tache.getId().equals(t.getId())) {
				return false;
			}
		}
		predecesseurs.add(t);
		return true;
	}
	
	/**
	 * Ajoute pour la Tache courante une Tache qui la succede
	 * @param t 
	 * 			la tache succedente
	 * @return
	 * 			vrai si la tache existe, faux sinon
	 */
	
	public boolean addSuccesseurs(Tache t) {
		for(Tache tache: successeurs) {
			if(tache.getId().equals(t.getId())) {
				return false;
			}
		}
		successeurs.add(t);
		return true;
	}

	public ArrayList<Tache> getPredecesseurs() {
		return predecesseurs;
	}

	public ArrayList<Tache> getSuccesseurs() {
		return successeurs;
	}
	
	/**
	 * Affiche la Tache avec sa description, sa duree initiale et sa duree finale
	 */
	@Override
	public String toString() {
		return "[description=" + description + ", duree_initiale=" + duree_initiale + ", duree_max="
				+ this.getDureeMax() + "]";
	}
	
	

}
