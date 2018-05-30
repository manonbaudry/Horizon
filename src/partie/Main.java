package partie;

import description.Description;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Partie partie = new Partie(new Description(), new String[] {"Fred"});
		Scene scene = new Scene(partie.getVueJoueur("Fred").getHBox(), 1000, 1000);
		
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
