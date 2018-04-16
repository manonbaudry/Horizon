package strategie;

import java.util.Map;

import javax.swing.JOptionPane;

import description.Alea;
import description.Couleur;
import partie.*;

public class JoueurSimple implements Strategie{

	@Override
	public void jouerEtape( VueJoueur vue) {
		affichage(vue);
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) vue.FinDuTour();
		 
	}

	@Override
	public void jouerJalon( VueJoueur vue) {
		affichage(vue);
		//String saisieTache= JOptionPane.showInputDialog("Saisissez un numéro de tâche ");
		//String saisieCouleur= JOptionPane.showInputDialog("Saisissez une couleur");
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) vue.FinDuTour();
	}

	@Override
	public void jouerTest( VueJoueur vue) {
		// TODO Auto-generated method stub
		
	}
	
	private void affichage(VueJoueur vue) {
		String res="Tour : "+ vue.getNumeroTour()
					+"Nom : \n\t"+vue.getNom()
					+"\nCaisse : \tQualité \n: "+vue.getCaisse()+"\t  "+vue.getQualite();
		for(int i=0;i<vue.getRealisations().length;i++ ) {
			res+=vue.getRealisations()[i].toString();
		}
		System.out.println(res);
	}

	
}
