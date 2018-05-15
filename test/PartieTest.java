
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import partie.Partie;
import partie.Donnees;
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
	Donnees donneesJoueur;
	
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
		description = new Description();
		partie = new Partie(description, new String[] {NOM_JOUEUR});
		donneesJoueur = partie.getVueJoueur(NOM_JOUEUR);
		//partie.passerTour();
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
	public void test_description() {
		assertNotNull(description);
	}
	
	@Test
	public void test_donnees_joueurs() {
		assertNotNull(donneesJoueur);
	}
	
	@Test
	public void test_partie() {
		assertNotNull(partie);
	}
	
	
	@Test
	public void test_effet_alea_a() {	
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
		partie.tourSemaine(Couleur.JAUNE);
		assertEquals(4, donneesJoueur.getRealisation("1").getDuree_reelle());
	}
	
	/**
	 * Testé: La qualité à la tache 6 est est diminuée quand l'aléa y (QUALITE de gravité 1)
	 * est tiré.<br>
	 * Scénario (après initialisation partie et joueur):<br>
	 * <ol>
	 * <li>tour 1-5 : ignoré: pas de décision</li>
	 * <li>tour 6 (semaine) : tirage VERT (evt y)</li>
	 * <li> verif: la qualité passe à 98% (baisse de 2%)</li>
	 * </ol>
	 */
	@Test
	public void test_effet_alea_y() {
		partie.passerTour();
		partie.passerTour();
		partie.passerTour();
		partie.passerTour();
		partie.passerTour();
		partie.tourSemaine(Couleur.VERT);
		assertEquals(98.0, donneesJoueur.getQualite(), 0.0);
	}

}
