package partie;

import description.Couleur;
import description.Description;
import description.Tache;
import graphe.Graphe;
import strategie.Strategie;

public class Donnees implements DonneesJoueur, VueJoueur{
	private int caisse;
	private String nom;
	private int qualite;
	private Realisation[] realisations;
	private Strategie strategie;
	private Description description;
	
	public Donnees(String nom, Strategie strategie) {
		super();
		this.caisse = 300;
		this.nom = nom;
		this.qualite = 0;
		description= new Description();
		realisations=new Realisation[description.getListe_taches().size()];
		for(int i=0;i<realisations.length;i++) {
			realisations[i]=new Realisation(description.getListe_taches().get(i));
		}
		this.strategie = strategie;
	}

	@Override
	public void actualisation(int temps) {
		
	}

	@Override
	public void baisseQualite(int delta) {
		this.qualite=this.qualite-delta;
	}

	@Override
	public void depense(int somme) {
		this.caisse=this.caisse-somme;
	}

	@Override
	public int getCaisse() {
		return this.caisse;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQualite() {
		return this.qualite;
	}

	@Override
	public Realisation getRealisation(String id) {
		return this.realisations[Integer.parseInt(id)-1];
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return this.strategie;
	}

	@Override
	public void FinDuTour() {
		
		for(int i = 0; i< realisations.length; i++) {
			if(realisations[i].getEtat().equals(Etat.EN_COURS)) {
				realisations[i].setAvancement(realisations[i].getAvancement()+1);
			}
		}
	}

	@Override
	public int getCurrent(String id) {
		return realisations[Integer.parseInt(id)-1].getAvancement();
		
	}

	@Override
	public String getDebutId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDuree(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Etat getEtat(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFinId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAcceleration(String id, boolean active) {
		realisations[Integer.parseInt(id)-1].setAcceleration(active);
		caisse -= realisations[Integer.parseInt(id)-1].getCoutAcceleration();
		
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean active) {
		realisations[Integer.parseInt(id)-1].
		
	}

	@Override
	public int getNumeroTour() {
		
		return 0;
	}
	
	public void setEnCours() {

		for(int i = 0; i < realisations.length ; i++) {
			int j = 0;
			while( j< realisations[i].getPredecesseurs().size() && realisations[j].getEtat().equals(Etat.TERMINE) ) {
				j++;
			}
			if(j==realisations[i].getPredecesseurs().size()) {
				realisations[j].setEtat(Etat.EN_COURS);
			}
				
		}
	}

	@Override
	public DonneesJoueur getDonnees() {
		// TODO Auto-generated method stub
		return null;
	}
}
