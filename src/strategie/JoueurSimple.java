package strategie;

import description.Couleur;
import javafx.scene.layout.GridPane;
import partie.*;



public class JoueurSimple implements Strategie {

	private GridPane upane = new GridPane();


	@Override
	public void jouerSemaine( Donnees d) {		

		affichage(d);	
		d.FinDuTour();
	}


	@Override
	public void jouerJalon( Donnees d) {

		for(Realisation r : d.getRealisation()) {
			
			r.alea_jaune.setOnMouseClicked(e ->{
				if(!r.estDejaProtege())
				d.setProtection(r.getTache().getId(), Couleur.JAUNE, true);
			});
			r.alea_rouge.setOnMouseClicked(e ->{
				if(!r.estDejaProtege())
				d.setProtection(r.getTache().getId(), Couleur.ROUGE, true);
			});

			r.alea_vert.setOnMouseClicked(e ->{
				if(!r.estDejaProtege())
				d.setProtection(r.getTache().getId(), Couleur.VERT, true);
			});

			r.cout_acceleration.setOnMouseClicked(e ->{
				d.setAcceleration(r.getTache().getId(), true);
			});
		}

		affichage(d);
		d.FinDuTour();
	}

	@Override
	public void jouerQuizz( Donnees d) {
		// TODO Auto-generated method stub
	}

	/**
	 * Calcule la date au plus tôt pour le joueur
	 * @param d, les données du joueur
	 * @return la valeur de la date au plus tôt
	 */
	public int auPlusTot(Donnees d) {
		int cpt = 0;
		for(Realisation real : d.getRealisation()) {
			System.out.println(real.getTache().getId() + " au plus tot " + real.calculPlusTot());
			cpt += real.calculPlusTot();
		}
		return cpt;
	}

	/**
	 * Calcule la date au plus tard pour le joueur
	 * @param d les données du joueur
	 * @return la valeur de la date au plus tard
	 */
	public int auPlusTard(Donnees d) {
		int cpt = 0;
		for(Realisation real : d.getRealisation()) {
			System.out.println(real.getTache().getId() + " au plus tard " + real.calculPlusTard());
			cpt += real.calculPlusTard();
		}
		return cpt;
	}

	/**
	 * Affichage du plateau du joueur
	 * @param d, la vue du joueur
	 */
	private void affichage(Donnees d) {
		this.update(d);
	}

	/**
	 * Met à jour l'affichage pour l'avancement et l'état de toutes les réalisations
	 * @param d, les données du joueur
	 */
	private void update(Donnees d) {
		d.update();
		for( Realisation r : d.getRealisation()) {
			r.setText(r.labelavancement, r.getAvancement()+" / " + r.getDuree_reelle());
			r.setText(r.labeletat, r.getEtat().name());
		}
	}
	
	/**
	 * Bloque la possibilité de cliquer sur les labels des réalisations pendant un autre tour que le tour jalon
	 */
	public void reset(Donnees d) {
		for( Realisation r : d.getRealisation()) {
		r.alea_jaune.setOnMouseClicked(e ->{});
		r.alea_rouge.setOnMouseClicked(e ->{});				
		r.alea_vert.setOnMouseClicked(e ->{});			
		r.cout_acceleration.setOnMouseClicked(e ->{});
		}
	}

	/**
	 * Créer le plateau de jeu avec les différentes réalisations
	 * @param d les données du joueur
	 */
	public void display(Donnees d) {
		upane.add(d.getRealisation().get(0).getPane(), 0, 1);
		upane.add(d.getRealisation().get(1).getPane(), 1, 0);
		upane.add(d.getRealisation().get(2).getPane(), 1, 1);
		upane.add(d.getRealisation().get(3).getPane(), 1, 2);
		upane.add(d.getRealisation().get(4).getPane(), 2, 1);
		upane.add(d.getRealisation().get(5).getPane(), 3, 1);
		upane.add(d.getRealisation().get(6).getPane(), 3, 2);
		upane.add(d.getRealisation().get(7).getPane(), 4, 1);

		upane.setVgap(30.0);
		upane.setHgap(30.0);
		upane.setStyle("-fx-stroke: green; -fx-stroke-width: 5; ");
	}

	
	public GridPane getPane(Donnees d) {
		display(d);
		return upane;
	}








}
