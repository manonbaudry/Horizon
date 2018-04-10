package partie;

import graphe.Graphe;
import strategie.Strategie;

public class Donnees implements DonneesJoueur{
	private int caisse;
	private Graphe graphe;
	private String nom;
	private int qualite;
	private Realisation realisation;
	private Strategie strategie;
	
	
	@Override
	public void actualisation(int temps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void baisseQualite(int detlta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depense(int somme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCaisse() {
		// TODO Auto-generated method stub
		return this.caisse;
	}

	@Override
	public Graphe getGraphe() {
		// TODO Auto-generated method stub
		return this.graphe;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public int getQualite() {
		// TODO Auto-generated method stub
		return this.qualite;
	}

	@Override
	public Realisation getRealisation(String id) {
		// TODO Auto-generated method stub
		return this.realisation;
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return this.strategie;
	}

	@Override
	public void uneSemaine() {
		// TODO Auto-generated method stub
		
	}

}
