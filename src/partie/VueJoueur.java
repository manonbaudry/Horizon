package partie;
import description.*;


public interface VueJoueur {
	/**
	 * Termine le tour pour le joueur courant. 
	 */
	public void 	FinDuTour();
	
	/**
	 * Fournit le montant actuel de la caisse de l'équipe (en euros).
	 * Valeur initiale : 300€
	 * Peut être augmenté (de 5€) lors des tests.
	 * Est décrémenté en fonction des décisions des joueurs.
	 * @return la montant de la caisse (€)
	 */
	public int getCaisse();
	
	
	/**
	 * Fournit l'état d'avancement de la réalisation d'une tâche pour l'équipe (en semaines).
	 * C'est une valeur entre 0 et la durée réélle de la réalisation.
	 * Cette valeur est mise à jour par la méthode de calcul Equipe.actualisation(int temps).
	 * @param id : id d'une tâche
	 * @return le nb de semaines déjà réalisés pour la tâche don l'id est passée en paramètre
	 */
	public int getCurrent(String id);
	
	/**
	 * Fournit l'identifiant de la première tâche du PERT. 
	 * Equivalent à getDescription().getDebut().getId() . 
	 * Le résultat sera le même pour toutes les équipes.
	 * @return id de la tâche initiale
	 */
	public String getDebutId();
	
	/**
	 * Retourne l'objet permettant d'acquérir la description de la configuration de jeu. 
	 * @return L'objet contenant la description statique du jeu.
	 */
	public Description getDescription();
	
	/**
	 * Fournit la durée réelle d'une tâche pour l'équipe.
	 * Si l'aléa lié à la tâche a été déterminée la valeur retournée inclus l'aléa éventuel,
	 * sinon il s'agit de la durée minimale.
	 * Pour obtenir la durée maximale, il faut interroger l'objet Description.
	 * @param id de la tâche dont on veut connaitre sa durée 
	 * @return La durée réelle d'une tâche pour l'équipe (en semaines).
	 */
	public int 	getDuree(String id);
	
	/**
	 * Fournit l'état courant de la réalisation. 
	 * Directement calculée par à partie de l'état d'avancement:
        - 0 : Etat.NON_ENTAMEE ,
        - > 0 et < durée réelle : Etat.EN_COURS
        - durée rélle : Etat.TERMINEE.
	 * @param id de la tâche
	 * @return l'état courant de la réalisation.
	 */
	public Etat getEtat(String id);
	
	/**
	 * Fournit l'identifiant de la dernière tâche du PERT. 
	 * @return l'id de la dernière tâche du jeu 
	 */
	public String 	getFinId();

	/**
	 * Fournit l'identifiant de la dernière tâche du PERT. Equivalent à getDescription().getDebut().getId() 
	 * Le résultat sera le même pour toutes les équipes.
	 * @return le nom de l'équipe sous forme d'une chaine de carcatères
	 */
	public String 	getNom();
	
	/**
	 *Fournit la qualité actuelle du produit.
	 *La valeur initiale est de 0. Elle est décrémentée de 2 (donc 2%) selon les aleas éventuels.
	 *La qualité finale du produit est calculée à partir de la durée de réalisation du projet et du montant restant de la caisse.
	 *La qualité propre à chaque équipe sera ensuite déduite du résultat.
	 * @return Le modificateur de qualité du produit pour l'équipe considérée.
	 */
	public int 	getQualite();

	/**
	 * Active ou désactive l'accélération de la tâche (réducton de 1 de la durée réelle).
	 * @param id de la tâche 
	 * @param active  true pour l'activation, false pour la désactivation.
	 */
	public void 	setAcceleration(String id, boolean active);
	
	/**
	 * Active ou désactive la protection contre un alea. 
	 * L'aléa est diretement déduit la la tâche associée et de la couleur choisie.
	 * @param id de la tâche
	 * @param couleur La couleur de l'alea prévenu (ou non)
	 * @param active   true pour l'activation, false pour la désactivation.
	 */
	public void 	setProtection(String id, Couleur couleur, boolean active);

	/**
	 * Fournit le numéro du tour courant. Le tour 0 existe. c'est normalement un tour Jalon.
	 * @return Le numéro du tour courant
	 */
	public int getNumeroTour();
}