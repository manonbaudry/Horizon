package description;

/**
 * Cette classe implémente l'interface Alea et reprend donc toutes les fonctions décrites dans celle-ci
 * (cf javadoc Alea)
 */

public class UnAlea implements Alea {
	
	private String nom;
	private int gravite;
	private TypeAlea type;

		
	public UnAlea(String nom, int gravite, TypeAlea type) {
		this.nom = nom;
		this.gravite = gravite;
		this.type = type;
	}

	public int getGravite() {
		return this.gravite;
	}

	public TypeAlea getType() {
		return this.type;
	}
	
	public String getNom() {
		return nom;
	}
	/**
	 * Affiche l'Alea avec sa gravite et le type d'alea
	 * @return le type d'aléa et sa gravité
	 */
	@Override
	public String toString() {
		return "[gravite=" + gravite + ", type=" + type + "]";
	}
	
	

}
