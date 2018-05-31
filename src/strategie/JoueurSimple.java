package strategie;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import description.Couleur;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

		Integer acceleration;
		//affichage(d);
		Integer protection = JOptionPane.showConfirmDialog(null , "Desirez-vous protéger?");
		if(protection == 0){
			String saisietache= JOptionPane.showInputDialog("Quelle tache à proteger?");
			String saisieCouleur = JOptionPane.showInputDialog("Quelle couleur?");
			saisieCouleur.toLowerCase();
			if(saisieCouleur.equals("rouge")) d.setProtection(saisietache, Couleur.ROUGE, true);
			if(saisieCouleur.equals("vert")) d.setProtection(saisietache, Couleur.VERT, true);
			if(saisieCouleur.equals("jaune")) d.setProtection(saisietache, Couleur.JAUNE, true);
		}
		do {
			acceleration = JOptionPane.showConfirmDialog(null , "Desirez-vous accélerer?");
			if(acceleration != 1 ) {
				String saisieTache= JOptionPane.showInputDialog("Quelle tache à accélerer?");
				d.setAcceleration(saisieTache, true);

			}
		}while(acceleration != 1);
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
		
		String res="Tour : "+ d.getNumeroTour()
		+"\nNom : "+d.getNom()
		+"\nCaisse : "+d.getCaisse()+"\tQualité : "+d.getQualite()+"%\n\n";

		for(int i=0;i<d.getDescription().getListe_taches().size();i++ ) {
			res+=d.getRealisation(Integer.toString(i+1)).toString()
					+"\tAvancement : "+d.getCurrent(Integer.toString(i+1))+"\n\n" +"  ______________" + "\n\n";
			
		}

		System.out.println(res + "\nAu plus tôt : "+ auPlusTot(d) +
				"\t Au plus tard : " + auPlusTard(d));
		
	}
	
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
	
	@Override
	public GridPane getPane(Donnees d) {
		display(d);
			return upane;
	}
	
	public void init(Donnees d) {
	//	d.getRealisation().get(0).setText(d.getRealisation().get(0).id, d.getRealisation().get(0).getTache().getId());
	}



	



}
