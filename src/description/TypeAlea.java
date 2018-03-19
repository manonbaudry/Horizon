package description;

/*Un TypeAlea définit l'impact qu'a l'apparition d'un alea non contré sur l'environnement d'un joueur.
 * 
 */

public enum TypeAlea implements java.io.Serializable, java.lang.Comparable<TypeAlea>{
	
	DELAI, //Impact sur le temps de réalisation d'une tâche. Chaque niveau de gravité ajoute 1 semaine.
	EURO, //Impact sur le cout.  Chaque niveau diminue la qualité finale de 2%
	QUALITE; //Impact sur la qualité du produit fini.  Pour chaque niveau, on déduit immédiatement 10% de la caisse.

}
