package description;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Objet qui permet d'obtenir la structure du jeu. Au moyen des éléments suivants:
La liste des tâches,
la liste des aleas envisagés,
les relations entre tâches (i.e. le dessin du graphe).
 **/
public class Description{

	private ArrayList<Tache> liste_taches;

	/**
	 * Construit l'environnement standard pour le jeu.**/
	public Description() {
		liste_taches = new ArrayList<>();
		
		Tache t1 = new UneTache("Reflechir\t", 2, "1");
		t1.getMap().put(new UnAlea("A  ", 1, TypeAlea.DELAI), Couleur.ROUGE);
		t1.getMap().put(new UnAlea("BB ", 2, TypeAlea.DELAI), Couleur.JAUNE);
		t1.getMap().put(new UnAlea("a  ", 1, TypeAlea.COUT), Couleur.VERT);
		
		Tache t2 = new UneTache("Dire\t", 3, "2");
		t2.getMap().put(new UnAlea("C  ", 1, TypeAlea.DELAI), Couleur.ROUGE);
		t2.getMap().put(new UnAlea("D  ", 1, TypeAlea.DELAI), Couleur.JAUNE);
		t2.getMap().put(new UnAlea("bb ", 2, TypeAlea.COUT), Couleur.VERT);
		
		Tache t3 = new UneTache("Ecouter\t", 2, "3");
		t3.getMap().put(new UnAlea("EE ",2, TypeAlea.DELAI), Couleur.ROUGE);
		t3.getMap().put(new UnAlea("c  ", 1, TypeAlea.COUT), Couleur.JAUNE);
		t3.getMap().put(new UnAlea("FF ", 2, TypeAlea.DELAI), Couleur.VERT);

		
		Tache t4 = new UneTache("Faire\t", 2,"4");
		t4.getMap().put(new UnAlea("d  ", 1, TypeAlea.COUT), Couleur.ROUGE);
		t4.getMap().put(new UnAlea("GG ", 2, TypeAlea.DELAI), Couleur.JAUNE);
		t4.getMap().put(new UnAlea("e  ", 1, TypeAlea.COUT), Couleur.VERT);
		
		Tache t5 = new UneTache("Demander\t", 1,"5");
		t5.getMap().put(new UnAlea("H  ", 1, TypeAlea.DELAI), Couleur.ROUGE);
		t5.getMap().put(new UnAlea("III", 3, TypeAlea.DELAI), Couleur.JAUNE);
		t5.getMap().put(new UnAlea("ff ", 2, TypeAlea.COUT), Couleur.VERT);
		
		Tache t6 = new UneTache("Contrôler\t", 3,"6");
		t6.getMap().put(new UnAlea("J  ", 1, TypeAlea.DELAI), Couleur.ROUGE);
		t6.getMap().put(new UnAlea("f  ", 1, TypeAlea.COUT), Couleur.JAUNE);
		t6.getMap().put(new UnAlea("y  ", 1, TypeAlea.QUALITE), Couleur.VERT);
		
		Tache t7 = new UneTache("Planifier\t", 3, "7");
		t7.getMap().put(new UnAlea("KKK", 3, TypeAlea.DELAI), Couleur.ROUGE);
		t7.getMap().put(new UnAlea("L  ", 1, TypeAlea.DELAI), Couleur.JAUNE);
		t7.getMap().put(new UnAlea("M  ", 1, TypeAlea.DELAI), Couleur.VERT);
		
		Tache t8 = new UneTache("Présenter\t", 2, "8");
		t8.getMap().put(new UnAlea("O  ", 1, TypeAlea.DELAI), Couleur.ROUGE);
		t8.getMap().put(new UnAlea("pp ", 2, TypeAlea.COUT), Couleur.JAUNE);
		t8.getMap().put(new UnAlea("zz ", 2, TypeAlea.QUALITE), Couleur.VERT);
		
			
		t1.addSuccesseurs(new Tache[] {t2,t3,t4,t5,t6,t7,t8});
		t2.addPredecesseurs(new Tache[] {t1});
		t2.addSuccesseurs(new Tache[] {t5,t6,t7,t8});
		t3.addPredecesseurs(new Tache[] {t1});
		t3.addSuccesseurs(new Tache[] {t5,t6,t7,t8});
		t4.addPredecesseurs(new Tache[] {t1});
		t4.addSuccesseurs(new Tache[] {t5,t6,t7,t8});
		t5.addPredecesseurs(new Tache[] {t1,t2,t3,t4});
		t5.addSuccesseurs(new Tache[] {t6,t8});
		t6.addPredecesseurs(new Tache[] {t1,t2,t3,t4,t5});
		t6.addSuccesseurs(new Tache[] {t8});
		t7.addPredecesseurs(new Tache[] {t1,t2,t3,t4});
		t7.addSuccesseurs(new Tache[] {t8});
		t8.addPredecesseurs(new Tache[] {t1,t2,t3,t4,t5,t6,t7});
		
		liste_taches.add(t1);
		liste_taches.add(t2);
		liste_taches.add(t3);
		liste_taches.add(t4);
		liste_taches.add(t5);
		liste_taches.add(t6);
		liste_taches.add(t7);
		liste_taches.add(t8);
		
	}
	
	/**
	 * Construit l'environnement avec la possibilité d'ajouter directement la liste des taches
	 * @param liste_taches
	 * 						La liste des taches
	 */
	public Description(ArrayList<Tache> liste_taches) {
		super();
		this.liste_taches = liste_taches;
		
		
		
	}


	/**Fournit la tâche initiale (elle est unique).
	 * @return La tâche initiale.
	 */
	public Tache getDebut() {		
		return liste_taches.get(0);
	}

	/**Fournit la tâche terminale (elle est unique).
	 * @return La tâche terminale.
	 */
	public  Tache getFin() {
		return liste_taches.get(liste_taches.size()-1);
	}

	/**Fournit une couleur d'alea au hasard (cf Couleur.tirage()).
	 * @return La couleur choisie.
	 */
	public Couleur getRandom() {
		return Couleur.tirage();
	}

	/**Fournit la tâche désignée par un identifiant donné. Si aucune tâche n'est trouvée, retourne null.
	 * @param id 
	 * 			 La chaîne de caractère désignant la tâche.
	 * @return  L'objet Tache.**/
	public Tache getTacheById(String id) {
		for(Tache t : liste_taches) {
			if(t.getId().equals(id)) return t ;
		}
		return null;		
	}
	
	public String toString() {
		String res="";
		for (Tache tache : liste_taches) {
			res+=tache.toString()+'\n';
		}
		return res;
	}
}
