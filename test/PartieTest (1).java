package partie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import description.Couleur;
import description.Description;

/**
 * Classe de test du moteur du jeu.
 * note sur le code: Les noms des méthodes utilisées pour passer ou jouer les tours 
 * ou forcer le tirage d'un événement ne sont pas imposées.<br>
 * 
 * @author place
 *
 */
public class PartieTest {

	static final String NOM_JOUEUR = "NOM_JOUEUR";
	Description description;
	Partie partie;
	DonneesJoueur donneesJoueur;
	
	/**
	 * Initialisation avant chaque test.
	 * <ul>
	 * <li>Obtenir la description du jeu,</li>
	 * <li>créer une partie (avec un seul joueur),</li>
	 * <li>obtenir une vue vers les infos du jeueur.</li>
	 * </ul>
	 */
	@Before
	public void init() {
		description = new DescriptionProjet();
		Partie partie = new Partie(description, new String[] {NOM_JOUEUR});
		donneesJoueur = partie.getVueJoueur(NOM_JOUEUR);
	}
	/**
	 * Testé: La caisse d'un joueur est diminuée de 10€ quand un aléa EURO de gravité 1 est tiré.<br>
	 * Scénario (après initialisation partie et joueur):<br>
	 * <ol>
	 * <li>tour 1 (jalon) : ignoré: pas de décision</li>
	 * <li>tour 2 (semaine) : tirage VERT (evt a)</li>
	 * <li> verif: la caisse du joueur est de 290 €</li>
	 * </ol>
	 */
	@Test
	public void test_effet_alea_a() {
		partie.passerTour();  //passer le tour 1
		partie.tourSemaine(Couleur.VERT);
		assertEquals(290, donneesJoueur.getCaisse());
	}
	/**
	 * Testé: La durée de la tâche 1 est augmentée de 2 semaines quand l'aléa BB (DELAI de gravité 2)
	 * est tiré.<br>
	 * Scénario (après initialisation partie et joueur):<br>
	 * <ol>
	 * <li>tour 1 (jalon) : ignoré: pas de décision</li>
	 * <li>tour 2 (semaine) : tirage JAUNE (evt BB)</li>
	 * <li> verif: la durée de la t*ache 1 est de 4 semaines (2 + 2 extra)</li>
	 * </ol>
	 */
	@Test
	public void test_effet_alea_BB() {
		partie.passerTour();  //passer le tour 1
		partie.tourSemaine(Couleur.JAUNE);
		assertEquals(4, donneesJoueur.getRealisation("1").getDuree());
	}

}
