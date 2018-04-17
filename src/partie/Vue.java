/**
 * 
 */
package partie;

import java.util.HashMap;

import description.*;

/**
 * @author baudrym
 *
 */
public class Vue implements VueJoueur { 
	private Description description;
	private DonneesJoueur donnees;
	
	
	
	public Vue( Donnees donnees, Description description) {
		this.donnees = donnees;
		this.description = description;
		
	}
	
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
		return donnees.getCaisse();
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
	 * Le résultat sera le même pour toutes les équipes.
	 * @return id de la tâche initiale
	 */

	public String getDebutId() {

		return description.getDebut().getId();
	}
	
	/**
	 * Retourne l'objet permettant d'acquérir la description de la configuration de jeu. 
	 * @return L'objet contenant la description statique du jeu.
	 */
	public Description getDescription() {
		
		return description;
	}
	
	/**
	 * Fournit la durée réelle d'une tâche pour l'équipe.
	 * Si l'aléa lié à la tâche a été déterminée la valeur retournée inclus l'aléa éventuel,
	 * sinon il s'agit de la durée minimale.
	 * Pour obtenir la durée maximale, il faut interroger l'objet Description.
	 * @param id de la tâche dont on veut connaitre sa durée 
	 * @return La durée réelle d'une tâche pour l'équipe (en semaines).
	 */
	
	public int getDuree(String id) {
	
		return donnees.getRealisation(id).getDuree_reelle();
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
	
	public Etat getEtat(String id) {
	
		return null;
	}
	
	/**
	 * Fournit l'identifiant de la dernière tâche du PERT. 
	 * @return l'id de la dernière tâche du jeu 
	 */

	public String getFinId() {

		return description.getFin().getId();
	}


	public String getNom() {

		return donnees.getNom();
	}


	public int getQualite() {

		return donnees.getQualite();
	}

	/**
	 * Active ou désactive l'accélération de la tâche (réducton de 1 de la durée réelle).
	 * @param id de la tâche 
	 * @param active  true pour l'activation, false pour la désactivation.
	 */
	public void setAcceleration(String id, boolean active) {
		Tache t = description.getTacheById(id);
		if(active && ! t.isAcceleration()){
			int newDuree = t.getDureeReelle()-1;
			t.setDureeReelle(newDuree);
			t.setAcceleration(true);
			donnees.depense(t.getCoutAcceleration());
			
		}else {
			int newDuree = t.getDureeReelle()+1;
			t.setDureeReelle(newDuree);
			t.setAcceleration(false);
			donnees.depense(-(t.getCoutAcceleration()));

		}
		//VOIR PLACE : il faut annuler l'accélération???
	}
	
	/**
	 * Active ou désactive la protection contre un alea. 
	 * L'aléa est diretement déduit la la tâche associée et de la couleur choisie.
	 * @param id de la tâche
	 * @param couleur La couleur de l'alea prévenu (ou non)
	 * @param active   true pour l'activation, false pour la désactivation.
	 */
	public void setProtection(String id, Couleur couleur, boolean active) {
		Tache t = description.getTacheById(id);
		if(active) {
		//	t.getAlea(couleur)
		}
	}


	public int getNumeroTour() {
		return 0;
	}

	/**
	 * @return the donnees
	 */
	public DonneesJoueur getDonnees() {
		return donnees;
	}
	
	

}
