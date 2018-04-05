package stratégie;

public interface Strategie {

	/** 
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Validation d'un tour simple.
	 */
	void 	jouerEtape(partie.Equipe equipe, VueJoueur vue);

	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Saisie des décisions d'accélération et de protection.
	 */
	void 	jouerJalon(partie.Equipe equipe, VueJoueur vue);
	
	/**
	 * 
	 * @param equipe - L'équipe concernée.
	 * @param vue - L'interface du jeu
	 * 
	 * Réponse à un quizz.
	 */
	void 	jouerTest(partie.Equipe equipe, VueJoueur vue);
}
