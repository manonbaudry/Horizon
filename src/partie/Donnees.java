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
		this.qualite = 100;
		description= new Description();
		realisations = new ArrayList<>();
		numeroTour = 0;
		for(Tache t : description.getListe_taches()) {
			realisations.add(new Realisation(t));
		}
		this.strategie = strategie;
	}

	@Override
	public void actualisation() {
		setTermine();
		setEnCours();
		
	}

	@Override
	public void baisseQualite(int gravite) {
		this.qualite=(int)(qualite*(0.98*gravite));
	}

	@Override
	public void depense(int somme) {
		this.caisse-=somme;
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
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation;
		}
		return null;
	}
	public Realisation getRealisation(int numeroTour) {
		return realisations.get(numeroTour);
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
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation.getAvancement();
		}
		return -1;
	}

	@Override
	public String getDebutId() {
		return realisations.get(0).getTache().getId();
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public int getDuree(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return  realisation.getDuree_reelle();
		}
		return -1;
	}

	@Override
	public Etat getEtat(String id) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) return realisation.getEtat();
		}
		return null;
	}

	@Override
	public String getFinId() {
		return realisations.get(realisations.size()-1).getTache().getId();
	}

	@Override
	public void setAcceleration(String id, boolean active) {
		for (Realisation realisation : realisations) {
			if(!realisation.getAcceleration() && realisation.getTache().getId().equals(id)) {
				realisation.setAcceleration(active);
				caisse -= realisation.getTache().getCoutAcceleration();
			}
		}
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean active) {
		for (Realisation realisation : realisations) {
			if(realisation.getTache().getId().equals(id)) realisation.getProtections().put(couleur, active);

		}
		
		
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

	@Override
	public String toString() {
		return "Donnees [caisse=" + caisse + ", nom=" + nom + ", qualite=" + qualite + ", realisations=" + realisations
				+ ", strategie=" + strategie + ", description=" + description + ", numeroTour=" + numeroTour + "]";
	}
	
	

	
}
