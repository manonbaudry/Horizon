package description;

/*
 * Cette classe implémente l'interface Alea et reprend donc toutes les fonctions décrites dans celle-ci
 */

public class UnAlea implements Alea {
	
	private int gravite;
	private TypeAlea type;

		
	public UnAlea(int gravite, TypeAlea type) {
		this.gravite = gravite;
		this.type = type;
	}

	public int getGravite() {
		// TODO Auto-generated method stub
		return this.gravite;
	}

	public TypeAlea getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

}
