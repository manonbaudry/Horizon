package partie;

import graphe.Graphe;
import strategie.Strategie;

public class Donnees implements DonneesJoueur{
	private int caisse;
	private String nom;
	private int qualite;
	private Realisation realisation;
	private Strategie strategie;
	
	
	
	public Donnees(String nom, int qualite, Realisation realisation, Strategie strategie) {
		super();
		this.caisse = 300;
		this.nom = nom;
		this.qualite = 0;
		this.realisation = realisation;
		this.strategie = strategie;
	}

	@Override
	public void actualisation(int temps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void baisseQualite(int delta) {
		// TODO Auto-generated method stub
		this.qualite=this.qualite-delta;
	}

	@Override
	public void depense(int somme) {
		// TODO Auto-generated method stub
		this.caisse=this.caisse-somme;
	}

	@Override
	public int getCaisse() {
		// TODO Auto-generated method stub
		return this.caisse;
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
}
