package partie;

import strategie.Strategie;

/**
 * @author forestav
 *Représentation de l'état d'une équipe au cours de la partie. 'état est complètement décrit par:

    Le montant de la caisse (300 € au départ),
    La qualité du produit (0% au départ),
    L'ensemble des Realisation pour cette équipe.

    A l'initialisation une réalisation (et une seule) est créée pour chaque tâche définie dans le jeu. Par la suite, les réalisations de chaque équipe évoluent indépendamment les une des autres (en fonction du choix des joueurs et des évènements.

    nb. Comme chaque tâche est identifiée par un id (String), Toute réalisation est déterminée par l'équipe concernée et l'id de la tâche correspondante
 */
public interface DonneesJoueur {
	/**
	 * Méthode de calcul du niveau d'avancement d'un projet.
	 */
	public void actualisation();
	
	/**
	 * Méthode de décrémentation de la qualité du produit.
	 */
	
	/**
	 * Méthode qui met à jour la cagnotte du Joueur en lui retirant la "somme".
	 * @param somme ; la somme à prélever
	 */
	public void depense(int somme);
	
	/**
	 * Fournit le montant actuel de la caisse de l'équipe (en euros).
	 * @return la caisse du joueur
	 */
	public int getCaisse();
	
	/**
	 * Fournit le nom de l'équipe.
	 * @return le nom du joueur
	 */
	public String getNom();
	
	/**
	 * Fournit la qualité actuelle du produit.
	 * @return la qualité du projet
	 */
	public int getQualite();
	
	/**
	 * @param id, id de la réalisation
	 * @return la réalistion ayant pour id l'id donné en paramètre
	 */
	public Realisation getRealisation(String id);
	
	/**
	 * Fournit l'objet qui prend les décisions pour l'équipe.
	 * @return la stratégie associée au joueur
	 */
	public Strategie getStrategie();
	
	/**
	 * Baisse de la qualité en fonction de la gravité (2% par niveau de gravité)s
	 * @param gravite, le niveau de gravité
	 */

	void baisseQualite(int gravite);
	
}
