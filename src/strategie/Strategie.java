package strategie;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import partie.*;

public interface Strategie {

	
	/** 
	 * 
	 * @param d - L'interface du jeu
	 * 
	 * Validation d'un tour simple.
	 */
	void 	jouerSemaine(Donnees d);

	/**
	 * 
	 * @param d - L'interface du jeu
	 * 
	 * Saisie des décisions d'accélération et de protection.
	 */
	void 	jouerJalon(Donnees d);
	
	/**
	 * @param d - L'interface du jeu
	 * 
	 * Réponse à un quizz.
	 * @return la fenêtre du quiz
	 */
	Stage 	jouerQuizz(Donnees d);
	
	/**
	 * @param d - L'interface du jeu
	 * @return la gridPane de données pour être récupérée par Partie
	 */
	GridPane getPane(Donnees d);
	
	/**
	 * Réinitialise les valeurs de données dans l'interface graphique
	 * @param d - L'interface du jeu
	 */
	void reset(Donnees d);
}
