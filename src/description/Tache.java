package description;

import java.util.Collection;

/*Représente une tâche telle que définie par la structure du jeu.
 *  On n'inclut pas dans cette classe les données qui dépendent des équipes
 *   (comme par exemple la durée réelle de la tâche modifiée par les aleas).
 *    La liste complète des tâches et leur initialisation est de la responsabilité de la classe Description.
 * */

public interface Tache {

	/*Le cout de l'accélération en euros.
	 *  Une accélération réduit la durée effective de la tâche d'une semaine. 
	 *  Une tâche ne peut être accélérée qu'une seule fois.
	 *  @returns Le cout d'accélération de la tâche
	 */
	public int coutAcceleration();
	
	/*Fournit l'alea associé à une couleur pour la tâche courante. Chaque couleur détermine un alea et un seul.
	 * @param la couleur 
	 * @returns L'alea associé à la couleur
	 */
	
	public Alea getAlea(Couleur couleur);
	
	/*Fournit la description de la tâche (exemple: "Réaliser la promotion du produit"). Purement documentaire.
	 * @returns La description de la tâche (de l'ordre de 10 à 30 caractères).
	 */	
	public String getDescription();
	
	/*Fournit la durée initiale de la tâche. 
	 * C'est la durée qu'a la tâche quand aucun alea n est actif et que la tâche n'est pas accélérée.
	 * @returns La durée initiale de la tâche.	 * 
	 */	
	public int getDureeInitiale();
	
	/*Fournit la durée maximale de la tâche. 
	 * C'est la durée que prend la tâche quand elle n'est pas accélérée et que l'alea de type DELAI le plus grave se produit.
	 * @returns La durée maximale de la tâche
	 */
	public int getDureeMax();
	
	
	/*Donne l'identifiant de la tâche. Toute tâche a 1 identifiant et un seul.
	 *  Un identifiant désigne ne désigne pas plus d'une tâche.
	 *  @returns La tâche désignée 
	 */
	public String getId();
	
	/*Fournit la liste des tâches précédentes.
	 *  La tâche courante ne peut pas être entamée tant que toutes les tâches précédentes n'ont pas été achevées.
	 *  @returns La collection des tâches précédentes. La tâche initiale retourne une liste vide.
	 */
	public Collection<Tache> getPredecesseurs();
	
	/*Fournit la lliste des tâches suivantes.
	 *  La terminaison de la tâche courante peut éventuellement permettre 
	 *  le commencement de chacune des tâches suivantes à conditions que tous leurs prédécesseurs respectifs soit terminés.
	 *  @returns La liste des tâches suivante. La tâche terminale retourne une liste vide.
	 */
	public Collection<Tache> getSuccesseurs();
	
}
