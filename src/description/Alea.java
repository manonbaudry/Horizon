package description;

/*
 * Représente un des aleas envisagé durant le déroulement du jeu.
 * */
public interface Alea {


	/*Indique la gravité d'un alea.
	 * @returns  Le niveau de gravité de l'alea.
	 * */
	public int getGravite();


	/*Indique le type d'impact de l'alea.
	 * @returns Le type de l'alea.
	 * */
	public TypeAlea getType();


}
