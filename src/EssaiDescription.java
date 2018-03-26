
import java.util.ArrayList;
import description.*;


/**
 * Cette classe permet de tester la classe Desciption et vérifier que toutes
 * les informations apparaissent correctement lors de la création de taches.
 */
public class EssaiDescription {

	public static void main(String[] args) {
		
		ArrayList<Tache> taches = new ArrayList<>();
		taches.add(new UneTache("OUI", 12 , "1"));
		taches.add(new UneTache("NON", 1,"2"));
		taches.add(new UneTache("SALUT", 5,"3"));
		Description description = new Description(taches) ;
		
	
		System.out.println(description.getDebut());
		System.out.println(description.getTacheById("2"));
		System.out.println(description.getFin());
		}
}
