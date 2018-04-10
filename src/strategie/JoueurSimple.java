package strategie;

import javax.swing.JOptionPane;

import partie.*;

public class JoueurSimple implements Strategie{

	@Override
	public void jouerEtape( VueJoueur vue) {
		System.out.println("Tour : "+ vue.getNumeroTour()
							+"Nom : \n\t"+vue.getNom()
							+"\nCaisse : \n\t"+vue.getCaisse()
							+"\n Description : \n\t"+vue.getDescription());
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) vue.FinDuTour();
		 
	}

	@Override
	public void jouerJalon( VueJoueur vue) {
		System.out.println("Tour : "+ vue.getNumeroTour()
							+"Nom : \n\t"+vue.getNom()
							+"\nCaisse : \n\t"+vue.getCaisse()
							+"\n Description : \n\t"+vue.getDescription());
		//String saisieTache= JOptionPane.showInputDialog("Saisissez un numéro de tâche ");
		//String saisieCouleur= JOptionPane.showInputDialog("Saisissez une couleur");
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) vue.FinDuTour();
	}

	@Override
	public void jouerTest( VueJoueur vue) {
		// TODO Auto-generated method stub
		
	}

	
}
