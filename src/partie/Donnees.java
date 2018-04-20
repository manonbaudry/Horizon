package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;
import description.Tache;
import strategie.Strategie;

public class Donnees implements DonneesJoueur, VueJoueur{
	private int caisse;
	private String nom;
	private int qualite;
	private ArrayList<Realisation> realisations;
	private Strategie strategie;
	private Description description;
	private int numeroTour;
	
	public Donnees(String nom, Strategie strategie) {
		super();
		this.caisse = 300;
		this.nom = nom;
		this.qualite = 0;
		description= new Description();
		realisations = new ArrayList<>();
		numeroTour = 0;
		for(Tache t : description.getListe_taches()) {
			realisations.add(new Realisation(t.getDescription(), t.getDureeInitiale(), t.getId(), t.getCoutAcceleration()));
		}
		this.strategie = strategie;
	}

	@Override
	public void actualisation() {
		setTermine();
		setEnCours();
		
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
		return this.realisations.get(Integer.parseInt(id)-1);
	}

	@Override
	public Strategie getStrategie() {
		return this.strategie;
	}

	@Override
	public void FinDuTour() {
		for(int i = 0; i< realisations.size(); i++) {
			if(realisations.get(i).getEtat().equals(Etat.EN_COURS)) {
				realisations.get(i).incrementAvancement();
			}
		}
		actualisation();
		numeroTour++;
		
	}

	@Override
	public int getCurrent(String id) {
		return realisations.get(Integer.parseInt(id)-1).getAvancement();
	}

	@Override
	public String getDebutId() {
		return realisations.get(0).getId();
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public int getDuree(String id) {
		return  realisations.get(Integer.parseInt(id)-1).getDuree_reelle();
	}

	@Override
	public Etat getEtat(String id) {
		return realisations.get(Integer.parseInt(id)-1).getEtat();
	}

	@Override
	public String getFinId() {
		return realisations.get(realisations.size()-1).getId();
	}

	@Override
	public void setAcceleration(String id, boolean active) {
		realisations.get(Integer.parseInt(id)-1).setAcceleration(active);
		caisse -= realisations.get(Integer.parseInt(id)-1).getCoutAcceleration();
		
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean active) {
		realisations.get(Integer.parseInt(id)-1).getProtections().put(couleur, active);
		
	}

	public int getNumeroTour() {
		return this.numeroTour;
	}
	
	private void setEnCours() {

		for(int i = 0; i < realisations.size(); i++) {
			int j = 0;
			while( j< realisations.get(i).getPredecesseurs().size() && realisations.get(j).getEtat().equals(Etat.TERMINE) ) {
				j++;
			}
			if(j==realisations.get(i).getPredecesseurs().size()) {
				realisations.get(j).setEtat(Etat.EN_COURS);
			}
				
		}
	}
	
	private void setTermine() {
		for(Realisation r : realisations) {
			if(r.getDuree_reelle() == r.getAvancement()) {
				r.setEtat(Etat.TERMINE);
			}
		}
	}
	
}
