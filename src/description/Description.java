package description;

import java.util.ArrayList;

/*Objet qui permet d'obtenir la structure du jeu. Au moyen des éléments suivants:
La liste des tâches,
la liste des aleas envisagés,
les relations entre tâches (i.e. le dessin du graphe).
 **/
public class Description{

	private ArrayList<Tache> liste_taches;

	/*Construit l'environnement standard pour le jeu.**/
	public Description() {
		liste_taches = new ArrayList<>();
	}
	

	public Description(ArrayList<Tache> liste_taches) {
		super();
		this.liste_taches = liste_taches;
	}



	/*Fournit la tâche initiale (elle est unique).
	 * @returns La tâche initiale.
	 */
	public Tache getDebut() {		
		return liste_taches.get(0);
	}

	/*Fournit la tâche terminale (elle est unique).
	 * @returns La tâche terminale.
	 */
	public  Tache getFin() {
		return liste_taches.get(liste_taches.size()-1);
	}

	/*Fournit une couleur d'alea au hasard (cf Couleur.tirage()).
	 * @returns La couleur choisie.
	 */
	public Couleur getRandom() {
		return Couleur.tirage();
	}

	/*Fournit la tâche désignée par un identifiant donné. Si aucune tâche n'est trouvée, retourne null.
	 * @param id 
	 * 			 La chaîne de caractère désignant la tâche.
	 * @returns L'objet Tache.**/
	public Tache getTacheById(String id) {
		for(Tache t : liste_taches) {
			if(t.getId().equals(id)) return t ;
		}
		return null;		
	}
}
