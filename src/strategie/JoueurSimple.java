package strategie;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import description.Couleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

		for(Realisation r : d.getRealisation()) {
			Tooltip tip = new Tooltip("Cliquez pour protéger la réalisation " + r.getTache().getId());
				r.alea_jaune.setTooltip(tip);
			r.alea_jaune.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.JAUNE, true);
				r.alea_jaune.setTooltip(null);
			});
			r.alea_rouge.setTooltip(tip);
			r.alea_rouge.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.ROUGE, true);
				r.alea_rouge.setTooltip(null);
			});
			r.alea_vert.setTooltip(tip);
			r.alea_vert.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.VERT, true);
				r.alea_vert.setTooltip(null);
			});
			r.cout_acceleration.setTooltip(new Tooltip("Cliquez pour accélerer la réalisation " + r.getTache().getId()));
			r.cout_acceleration.setOnMouseClicked(e ->{
				d.setAcceleration(r.getTache().getId(), true);
				r.cout_acceleration.setTooltip(null);
			});
			
			
			
			
		}

		affichage(d);
		d.FinDuTour();
	}

	@Override
	public Stage jouerQuizz( Donnees d) {
	/*	if(d.getNumeroTour() < 7) {
			String rep = JOptionPane.showInputDialog("Comment t'apelles-tu");
			if(rep.equals(d.getNom())){
				d.depense(-5);
				JOptionPane.showMessageDialog(null,"Bravo tu as gagné 5€");
			}else {
				JOptionPane.showMessageDialog(null, "tu as perdu...");
			}
		}else {
			String rep = JOptionPane.showInputDialog("En quelle année sommes nous ?");
			if(rep.equals("2018")){
				d.depense(-10);
				JOptionPane.showMessageDialog(null, "Bravo tu as gagné 10€");
			}else {
				JOptionPane.showMessageDialog(null, "tu as perdu...");
			}			
			
		}*/
		String line = "";
		HashMap<String, String> map = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("/resources/questions.csv")));
			while((line = br.readLine()) != null) {
				String[] oui = line.split(";");
				map.put(oui[0], oui[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException y){y.printStackTrace();}

		Random r = new Random();
	//	String q = map.
		Pane root = new Pane();
		root.setPrefSize(299, 212);
		
		VBox bbox = new VBox();
		bbox.setStyle("-fx-background-color: #e1e9f2;");
		bbox.setPrefSize(299, 212);
		Label title = new Label("C'est l'heure du qu-qu-qu-quizz!");
		title.setAlignment(Pos.CENTER);
		title.setPrefSize(297, 27);
		title.setStyle("-fx-text-alignment: center; -fx-background-color: white;");
		
		Label question = new Label("Question : ");
		question.setPrefSize(597, 87);
		question.setAlignment(Pos.CENTER);
			
		javafx.scene.control.TextField reponse = new javafx.scene.control.TextField();
		VBox.setMargin(reponse, new Insets(0,75, 0, 75));
		
		bbox.getChildren().addAll(title, question, reponse);		
		root.getChildren().add(bbox);
		
		Scene scne = new Scene(root);
		
		Stage s = new Stage();
		s.setScene(scne);
		return s;
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

		upane.setVgap(5.0);
		upane.setHgap(5.0);
		upane.setStyle("-fx-stroke: green; -fx-stroke-width: 5; ");
		
		for(Node node : upane.getChildren()) {
			((GridPane)node).setPadding(new Insets(10));
		}
	}

	
	public GridPane getPane(Donnees d) {
		display(d);
		return upane;
	}








}
