
import java.util.ArrayList;

import description.*;

public class EssaiDescription {

	public static void main(String[] args) {
		// Charge le jeu en mémoire
		ArrayList<Tache> taches = new ArrayList<>();
		taches.add(new UneTache("OUI", 12 , "1"));
		taches.add(new UneTache("NON", 1,"2"));
		taches.add(new UneTache("SALUT", 5,"3"));
		Description description = new Description(taches) ;
		// Récupère les données de la tache 1
		//Tache t = description.getTacheById("1");
		// Affiche la description de la tâche 1
		// Récupère l’évènement vert de la tâche 1
		//Alea a = new UnAlea(3, TypeAlea.DELAI);
		// Affiche le nom de l’aléa obtenu
		//System.out.println(t.getDescription() + " " + a.toString());
		
	
		System.out.println(description.getDebut());
		System.out.println(description.getTacheById("2"));
		System.out.println(description.getFin());
		}
}
