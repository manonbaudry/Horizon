import description.*;


/**
 * Cette classe permet de tester la classe Desciption et vérifier que toutes
 * les informations apparaissent correctement lors de la création de taches.
 */
public class EssaiDescription {

	public static void main(String[] args) {

		Description description = new Description() ;
			
		System.out.println(description.toString());

		//la tache 8 a pour predecesseurs 7, 6, 5, 4, 3, 2 et 1 
		//description.getTacheById("4").affichePredecesseurs();

		//la tache 1 n'a pas de predecesseur
		//description.getTacheById("1").affichePredecesseurs();

		//la tache 2 a pour succcesseurs  5, 6, 7 et 8
	//	description.getTacheById("2").afficheSuccesseurs();
		//for(int i = 0; i < description.getListe_taches().size()-1; i++) {
			//description.getTacheById(String.valueOf(i+1)).afficheSuccesseurs();
		//}

	}
}
