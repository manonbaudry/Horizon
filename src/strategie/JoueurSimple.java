package strategie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import description.Couleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import partie.*;

public class JoueurSimple implements Strategie {
	private int nbjalon = 0;
	private GridPane upane = new GridPane();
	private Label chemin = new Label();

	public void jouerSemaine( Donnees d) {		
		affichage(d);	
		d.FinDuTour();
	}

	public void jouerJalon( Donnees d) {
		d.actualisation();
		ArrayList<Realisation> salut = new ArrayList<>();
		ArrayList<Realisation> jalon1 = new ArrayList<>();
		ArrayList<Realisation> jalon2 = new ArrayList<>();
		jalon1.add(d.getRealisation().get(0));
		jalon1.add(d.getRealisation().get(1));
		jalon1.add(d.getRealisation().get(2));
		jalon1.add(d.getRealisation().get(3));

		jalon2.add(d.getRealisation().get(4));
		jalon2.add(d.getRealisation().get(5));
		jalon2.add(d.getRealisation().get(6));
		jalon2.add(d.getRealisation().get(7));
		if( nbjalon == 0 ) {
			salut.addAll(jalon1);
		}else {
			salut.addAll(jalon2);
		}	

		for(Realisation r : salut) {
			Tooltip tip = new Tooltip("Cliquez pour protéger la réalisation " + r.getTache().getId());
			Tooltip tip2 = new Tooltip("Cliquez pour accélerer la réalisation " + r.getTache().getId());
			tip.setStyle("-fx-background-color: #e1e9f2; -fx-text-fill: black;");
			tip2.setStyle("-fx-background-color: #e1e9f2; -fx-text-fill: black;");
			r.alea_jaune.setTooltip(tip);
			r.alea_jaune.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.JAUNE, !r.isProtected(Couleur.JAUNE));
				r.alea_jaune.setTooltip(null);
			});
			r.alea_rouge.setTooltip(tip);
			r.alea_rouge.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.ROUGE, !r.isProtected(Couleur.ROUGE));
				r.alea_rouge.setTooltip(null);
			});
			r.alea_vert.setTooltip(tip);
			r.alea_vert.setOnMouseClicked(e ->{
				d.setProtection(r.getTache().getId(), Couleur.VERT, !r.isProtected(Couleur.VERT));
				r.alea_vert.setTooltip(null);
			});
			r.cout_acceleration.setTooltip(tip2);
			r.cout_acceleration.setOnMouseClicked(e ->{
				d.setAcceleration(r.getTache().getId(), true);
				r.cout_acceleration.setTooltip(null);
			});
		}
		affichage(d);
		d.FinDuTour();
		nbjalon++;
	}

	public Stage jouerQuizz( Donnees d) {

		String line = "";
		ArrayList<String >reponses = new ArrayList<>();
		ArrayList<String >questions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("resources/questions.csv"))) {

			while ((line = br.readLine()) != null) {
				String[] oui = line.split(";");
				if(oui != null) {
					questions.add(oui[0]);
					reponses.add(oui[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int ligne = new Random().nextInt(reponses.size());

		Stage s = new Stage();
		//Pane root = new Pane();
		VBox bbox = new VBox();
		Label title = new Label("C'est l'heure du qu-qu-qu-quizz à 10 balles!");
		Button valider = new Button("Valider");	
		Label question = new Label(questions.get(ligne));
		Scene scne = new Scene(bbox);
		javafx.scene.control.TextField reponse = new javafx.scene.control.TextField();

		bbox.setPrefSize(400, 212);		
		title.setPrefSize(Double.MAX_VALUE, 27);
		question.setPrefSize(597, 87);

		title.setAlignment(Pos.CENTER);

		title.setStyle("-fx-text-alignment: center; -fx-background-color: white;");		
		bbox.setStyle("-fx-background-color: #e1e9f2; -fx-font: 14px Roboto;");

		question.setAlignment(Pos.CENTER);
		valider.setAlignment(Pos.CENTER);

		VBox.setMargin(reponse, new Insets(0,75, 15, 75));
		VBox.setMargin(valider, new Insets(0,0, 15, 175));

		valider.setOnAction(e ->{
			if( reponse.getText().toUpperCase().equals(reponses.get(ligne))) {
				d.depense(-10);
				d.update();
			}
			s.close();
		});

		bbox.getChildren().addAll(title, question, reponse, valider);			
		s.setScene(scne);
		s.setResizable(false);
		s.initModality(Modality.APPLICATION_MODAL);
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
		chemin.setText(" Chemin critique : " +d.RealisationToString().toString() + "\n Durée du projet : " + d.getDateFinDeProjet());
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
		//chemin.setText("Chemin critique :" +  d.RealisationToString().toString());
		upane.add(d.getRealisation().get(0).getPane(), 0, 1);	
		upane.add(d.getRealisation().get(1).getPane(), 1, 0);
		upane.add(d.getRealisation().get(2).getPane(), 1, 1);
		upane.add(d.getRealisation().get(3).getPane(), 1, 2);
		upane.add(d.getRealisation().get(4).getPane(), 2, 1);
		upane.add(d.getRealisation().get(5).getPane(), 3, 1);
		upane.add(d.getRealisation().get(6).getPane(), 2, 2);
		upane.add(d.getRealisation().get(7).getPane(), 4, 1);
		upane.add(chemin, 0, 2);
		upane.setVgap(5.0);
		upane.setHgap(5.0);
		for(Node node : upane.getChildren()) {
			if(node instanceof GridPane)((GridPane)node).setPadding(new Insets(10));
		}
	}

	public GridPane getPane(Donnees d) {
		display(d);
		return upane;
	}
}
