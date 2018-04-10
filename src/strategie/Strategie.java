package strategie;

import partie.*;

public interface Strategie {

	
	/** 
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Validation d'un tour simple.
	 */
	void 	jouerEtape(VueJoueur vue);

	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Saisie des décisions d'accélération et de protection.
	 */
	void 	jouerJalon(VueJoueur vue);
	
	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Réponse à un quizz.
	 */
	void 	jouerTest(VueJoueur vue);
}
