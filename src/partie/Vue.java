/**
 * 
 */
package partie;

import description.Couleur;
import description.Description;

/**
 * @author baudrym
 *
 */
public class Vue implements VueJoueur {
	private int caisse;
	private String nom; 
	private int qualite; 
	private Description description;
	
	
	
	/**
	 * Termine le tour pour le joueur courant. 
	 */
	
	public void FinDuTour() {
		
	}
	/**
	 * Fournit le montant actuel de la caisse de l'équipe (en euros).
	 * Valeur initiale : 300€
	 * Peut être augmenté (de 5€) lors des tests.
	 * Est décrémenté en fonction des décisions des joueurs.
	 * @return la montant de la caisse (€)
	 */

	public int getCaisse() {
		return 0;
	}
	
	/**
	 * Fournit l'état d'avancement de la réalisation d'une tâche pour l'équipe (en semaines).
	 * C'est une valeur entre 0 et la durée réélle de la réalisation.
	 * Cette valeur est mise à jour par la méthode de calcul Equipe.actualisation(int temps).
	 * @param id : id d'une tâche
	 * @return le nb de semaines déjà réalisés pour la tâche don l'id est passée en paramètre
	 */
	@Override
	public int getCurrent(String id) {
		return 0;
	}
	/**
	 * Fournit l'identifiant de la première tâche du PERT. 
	 * Equivalent à getDescription().getDebut().getId() . 
	 * Le résultat sera le ùmême pour toutes les équipes.
	 * @return id de la tâche initiale
	 */

	public String getDebutId() {

		return null;
	}
	/**
	 * Retourne l'objet permettant d'acquérir la description de la configuration de jeu. 
	 * @return L'objet contenant la description statique du jeu.
	 */
	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Fournit l'état courant de la réalisation. 
	 * Directement calculée par à partie de l'état d'avancement:
        - 0 : Etat.NON_ENTAMEE ,
        - > 0 et < durée réelle : Etat.EN_COURS
        - durée rélle : Etat.TERMINEE.
	 * @param id de la tâche
	 * @return l'état courant de la réalisation.
	 */
	@Override
	public int getDuree(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Fournit l'état courant de la réalisation. 
	 * Directement calculée par à partie de l'état d'avancement:
        - 0 : Etat.NON_ENTAMEE ,
        - > 0 et < durée réelle : Etat.EN_COURS
        - durée rélle : Etat.TERMINEE.
	 * @param id de la tâche
	 * @return l'état courant de la réalisation.
	 */
	@Override
	public Etat getEtat(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Fournit l'identifiant de la dernière tâche du PERT. 
	 * @return l'id de la dernière tâche du jeu 
	 */

	public String getFinId() {

		return null;
	}


	public String getNom() {

		return null;
	}


	public int getQualite() {

		return 0;
	}


	public void setAcceleration(String id, boolean active) {	
		
	}
	/**
	 * Active ou désactive la protection contre un alea. 
	 * L'aléa est diretement déduit la la tâche associée et de la couleur choisie.
	 * @param id de la tâche
	 * @param couleur La couleur de l'alea prévenu (ou non)
	 * @param active   true pour l'activation, false pour la désactivation.
	 */
	public void setProtection(String id, Couleur couleur, boolean active) {
	
	}
	

}
