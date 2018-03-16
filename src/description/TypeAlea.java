package description;

/*Un TypeAlea définit l'impact qu'a l'apparition d'un alea non contré sur l'environnement d'un joueur.
 * 
 */

public enum TypeAlea implements java.io.Serializable, java.lang.Comparable<TypeAlea>{
	
	DELAI, //Impact sur le temps de réalisation d'une tâche.
	EURO, //Impact sur le cout.
	QUALITE; //Impact sur la qualité du produit fini.

}
