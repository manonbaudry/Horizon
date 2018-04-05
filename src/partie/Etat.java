package partie;

/**
 * 
 * @author forestav
 *Les différents états que peut prendre une réalisation. ceci concerne uniquement l'état d'avancement de la tâche. Le fait que l'alea ait - ou non - été déterminé pour la tâche n'influe pas sur l'état décrit ici.
 */

public enum Etat {
	/**
	 *La réalisation est en cours
	 */
	EN_COURS('E'),
	/**
	 * La réalisation n'est pas encore entamée mais toutes les conditions sont respectées pour un démarrage immédiat.
	 */
	IMMINENT('I'),
	/**
	 * La réalisation n'est pas encore entamée.
	 */
	NON_ENTAMEE('N'),
	/**
	 * La réalisation est terminée.
	 */
	TERMINE('T');
	
	private final char type;
	
	private Etat (char c) {
		this.type=c;
	}
	/**
	 * retourne le caractère correspondant à l'état.
	 * @return
	 */
	public char getChar() {
		return this.type;
	}
	
	
	
}
