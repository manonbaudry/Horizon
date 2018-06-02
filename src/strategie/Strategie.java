package strategie;

import javafx.scene.layout.GridPane;
import partie.*;

public interface Strategie {

	
	/** 
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param d - L'interface du jeu
	 * 
	 * Validation d'un tour simple.
	 */
	void 	jouerSemaine(Donnees d);

	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param d - L'interface du jeu
	 * 
	 * Saisie des décisions d'accélération et de protection.
	 */
	void 	jouerJalon(Donnees d);
	
	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param d - L'interface du jeu
	 * 
	 * Réponse à un quizz.
	 */
	void 	jouerQuizz(Donnees d);
	
	GridPane getPane(Donnees d);
	
	void reset(Donnees d);
}
