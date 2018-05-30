package partie;

import description.Description;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		
		Scene scene = new Scene(partie.getVueJoueur("Fred").getHBox(), 1550, 650);
		primaryStage.setScene(scene);
		
		primaryStage.show();

	}

	public static void main(String[] args) {
	try{
		launch(args);
	}catch (Exception e) {
		e.printStackTrace();
	}
}

}
