package partie;

import graphe.Graphe;
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
	 */
	public void depense(int somme);
	
	/**
	 * Fournit le montant actuel de la caisse de l'équipe (en euros).
	 */
	public int getCaisse();
	
	/**
	 * Fournit le nom de l'équipe.
	 */
	public String getNom();
	
	/**
	 * Fournit la qualité actuelle du produit.
	 */
	public int getQualite();
	
	/**
	 * 
	 */
	public Realisation getRealisation(String id);
	
	/**
	 * Fournit l'objet qui prend les décisions pour l'équipe.
	 */
	public Strategie getStrategie();

	void baisseQualite(int gravite);
	
}
