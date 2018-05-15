package description;

/**
 * Représente un des aleas envisagé durant le déroulement du jeu.
 * */
public interface Alea {


	/**
	 * Indique la gravite d'un alea.
	 * @return  Le niveau de gravité de l'alea.
	 * */
	public int getGravite();


	/**
	 * Indique le type d'impact de l'alea.
	 * @return Le type de l'alea.
	 * */
	public TypeAlea getType();

	/**
	 * Indique le nom de l'aléa
	 * @return Le nom de l'aléa sous forme d'une chaîne de caractères
	 */
	public String getNom();
	

}
