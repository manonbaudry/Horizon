package strategie;

import javax.swing.JOptionPane;

import partie.*;

public class JoueurSimple implements Strategie{

	@Override
	public void jouerEtape( VueJoueur vue) {
		System.out.println(vue.getNom()+vue.getCaisse()+vue.getDescription());
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) vue.FinDuTour();
		 
	}

	@Override
	public void jouerJalon( VueJoueur vue) {
		System.out.println(vue.getNom()+vue.getCaisse()+vue.getDescription());
		String saisieTache= JOptionPane.showInputDialog("Saisissez un numéro de tâche ");
		String saisieCouleur= JOptionPane.showInputDialog("Saisissez une couleur");
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour ou appuyer sur entrée");
		if(saisie.equals("fin")) vue.FinDuTour();
	}

	@Override
	public void jouerTest( VueJoueur vue) {
		// TODO Auto-generated method stub
		
	}

	
}
