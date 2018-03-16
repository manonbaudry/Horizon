package description;

import java.util.Random;

/*La liste des couleurs possibles.
 *  Une couleur caractérise un alea possible parmi tous les aleas envisagés à chaque étape.
 *   Dans le jeu, chaque couleur est liée à une probabilité d'apparition.
 */

public enum Couleur implements java.io.Serializable, java.lang.Comparable<Couleur> {

	ROUGE, //Couleur rouge : 3 chances sur 6.
	JAUNE, //Couleur jaune (ou orange) : 2 chances sur 6.
	VERT; //Couleur verte : 1 chance sur 6.
	
	/*Choisit aléatoirement une couleur avec les probabilités associées.
	 * @return La couleur choisie
	 */
	public static Couleur tirage() {
		Couleur[] probabilité = new Couleur[]{Couleur.ROUGE, Couleur.ROUGE, Couleur.ROUGE,
												Couleur.JAUNE, Couleur.JAUNE,
												Couleur.VERT};
		return probabilité[new Random().nextInt(6)];
	}
	
	
	
}
