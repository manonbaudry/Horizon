
import java.util.ArrayList;
import description.*;


/**
 * Cette classe permet de tester la classe Desciption et vérifier que toutes
 * les informations apparaissent correctement lors de la création de taches.
 */
public class EssaiDescription {

	public static void main(String[] args) {
		

		Description description = new Description() ;
		
		
		//L'ordre d'ajout se fait du début à) la fin.
		description.getTacheById("2").estLeSucceseurDe(description.getTacheById("1"));
		description.getTacheById("3").estLeSucceseurDe(description.getTacheById("1"));
		description.getTacheById("4").estLeSucceseurDe(description.getTacheById("1"));
		
		description.getTacheById("5").estLeSucceseurDe(description.getTacheById("2"));
		description.getTacheById("5").estLeSucceseurDe(description.getTacheById("3"));
		description.getTacheById("5").estLeSucceseurDe(description.getTacheById("4"));
		
		description.getTacheById("7").estLeSucceseurDe(description.getTacheById("2"));
		description.getTacheById("7").estLeSucceseurDe(description.getTacheById("3"));
		description.getTacheById("7").estLeSucceseurDe(description.getTacheById("4"));
		
		description.getTacheById("6").estLeSucceseurDe(description.getTacheById("5"));
		
		description.getTacheById("8").estLeSucceseurDe(description.getTacheById("6"));
		description.getTacheById("8").estLeSucceseurDe(description.getTacheById("7"));
		
			
		System.out.println(description.toString());


		description.getTacheById("8").affichePredecesseurs();
		description.getTacheById("1").afficheSuccesseurs();
		
	
		//System.out.println(description.getDebut());
		//System.out.println(description.getTacheById("2"));
		//System.out.println(description.getFin());
		}
}
