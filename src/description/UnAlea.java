package description;

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
