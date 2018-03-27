package description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Cette classe implémente l'interface Tache et reprend donc toutes les fonctions décrites dans celle-ci
 */
public class UneTache implements Tache {

	private String description;
	private int duree_initiale;
	private String id;
	private int cout_aceleration;
	private ArrayList<Tache> predecesseurs;
	private ArrayList<Tache> successeurs;
	
	private HashMap<Alea, Couleur> map;
		
		
	public UneTache(String description, int duree_initiale, String id) {
		this.description = description;
		this.duree_initiale = duree_initiale;
		this.id = id;
		this.map = new HashMap<>();
		predecesseurs=new ArrayList<>();
		successeurs=new ArrayList<>();
		
	}

	public int getCoutAcceleration() {
		return this.cout_aceleration;
	}

	public Alea getAlea(Couleur couleur) {
		for (Map.Entry<Alea, Couleur> entry : map.entrySet() ) {
			if(entry.getValue().equals(couleur)) return entry.getKey();
		}
		return null;
		//return map.get(couleur);
	}

	public String getDescription() {
		return this.description;
	}

	public int getDureeInitiale() {
		return this.duree_initiale;
	}
	
	public int getDureeMax() {
		int res=0;
		for (Map.Entry<Alea, Couleur> entry : map.entrySet() ) {
			if(entry.getKey().getType().equals(TypeAlea.DELAI)) res+=entry.getKey().getGravite();
		}
		return res;
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
		String res=" ";
		for (Map.Entry<Alea, Couleur> entry : map.entrySet()) {
			res+=entry.getKey().getNom() + "|";
		}
		
		return "Tache n°" + this.id + " [ " + description + " : " +  duree_initiale +"|" + this.getDureeMax() + res + " ]";
	}

	public HashMap<Alea, Couleur> getMap() {
		return map;
	}

	
	
	
	
	
	
	
	

}
