package description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe implémente l'interface Tâche et reprend donc toutes les fonctions décrites dans celle-ci
 * cf javaid Tache
 */
public class UneTache implements Tache {

	private String description;
	private int duree_initiale;
	private String id;
	private int cout_aceleration;
	private ArrayList<Tache> predecesseurs;
	private ArrayList<Tache> successeurs;	
	private HashMap<Alea, Couleur> map;


	public UneTache(String description, int duree_initiale, String id, int cout) {
		this.description = description;
		this.duree_initiale = duree_initiale;
		this.id = id;
		this.cout_aceleration = cout;
		this.map = new HashMap<>();
		predecesseurs=new ArrayList<>();
		successeurs=new ArrayList<>();	
	}

	public boolean sontEnParallele(Tache t) {
		if((!this.equals(t) && this.getPredecesseurs().size() > 0) && t.getPredecesseurs().containsAll(this.getPredecesseurs())) {
			return true;
		}
		return false ;

	}

	public boolean isPrecedesseur(Tache t) {

		if(t.getPredecesseurs().contains(this)) {
			return true;
		}
		return false;
	}

	public int getCoutAcceleration() {
		return this.cout_aceleration;
	}

	public Alea getAlea(Couleur couleur) {
		for (Map.Entry<Alea, Couleur> entry : map.entrySet() ) {
			if(entry.getValue().equals(couleur)) return entry.getKey();
		}
		return null;
	}

	public String getDescription() {
		return this.description;
	}

	public int getDureeInitiale() {
		return this.duree_initiale;
	}

	public int getDureeMax() {
		ArrayList<Integer> temp = new ArrayList<>();
		for (Map.Entry<Alea, Couleur> entry : map.entrySet() ) {
			if(entry.getKey().getType().equals(TypeAlea.DELAI)) temp.add(entry.getKey().getGravite());
		}
		java.util.Collections.sort(temp);
		if(temp.size() > 0) return this.getDureeInitiale()+temp.get(temp.size()-1);
		return 0;
	}


	public String getId() {
		return this.id;
	}

	public void addSuccesseurs(Tache[] tab) {
		for(int i=0; i<tab.length;i++) {
			successeurs.add(tab[i]);	
		}
	}

	public ArrayList<Tache> getPredecesseurs() {
		return predecesseurs;
	}

	public ArrayList<Tache> getSuccesseurs() {
		return successeurs;
	}

	public String toString() {
		String res="";
		for (Map.Entry<Alea, Couleur> entry : map.entrySet()) {
			res+=entry.getKey().getType() +" "+entry.getKey().getNom() + " | ";
		}

		return this.id + " : " + description + "["+this.cout_aceleration+"€]" + "\n\tDurée initiale : " +
		+  duree_initiale  + " -  Durée max : " + this.getDureeMax() +"\n\t" +  res + '\n'  ;
	}
	/**
	 * Retourne la HashMap avec en id les Aléas et comme valeur les Couleurs.
	 */
	public HashMap<Alea, Couleur> getMap() {
		return map;
	}

	public void estLeSuccesseurDe(Tache tache) {
		tache.getSuccesseurs().add(this);
		this.getPredecesseurs().add(tache);		
	}


	public String afficheIdTache(){
		return "Tache n°" + this.id;
	}


	public void setSuccesseurs(ArrayList<Tache> successeurs) {
		this.successeurs = successeurs;
	}

	public void affichePredecesseurs() {
		if(predecesseurs != null)
			for (Tache tache : predecesseurs) {
				System.out.println(tache.afficheIdTache() + " est le predecesseur de " + this.afficheIdTache() + '\n');

			}
	}

	public void afficheSuccesseurs() {
		if(successeurs != null) {
			for (Tache tache : successeurs) {
				System.out.println(tache.afficheIdTache() + " est le successeur de " + this.afficheIdTache() + '\n');
			}
		}
	}
}
