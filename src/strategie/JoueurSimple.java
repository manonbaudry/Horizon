package strategie;

import java.util.Map;

import javax.swing.JOptionPane;

import description.Alea;
import description.Couleur;
import description.Description;
import partie.*;

public class JoueurSimple implements Strategie{

	@Override
	public void jouerEtape( Donnees d) {
		affichage(d);
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) d.FinDuTour();
		 
	}

	@Override
	public void jouerJalon( Donnees d) {
		affichage(d);
		//String saisieTache= JOptionPane.showInputDialog("Saisissez un numéro de tâche ");
		//String saisieCouleur= JOptionPane.showInputDialog("Saisissez une couleur");
		String saisie= JOptionPane.showInputDialog("Saisissez \"fin\" pour finir le tour");
		if(saisie.equals("fin")) d.FinDuTour();
	}

	@Override
	public void jouerTest( Donnees d) {
		// TODO Auto-generated method stub
		
	}
	
	private void affichage(Donnees d) {
		String res="Tour : "+ d.getNumeroTour()
					+"\nNom : "+d.getNom()
					+"\nCaisse : "+d.getCaisse()+"\tQualité : "+d.getQualite()+"\n\n";
		for(int i=0;i<d.getDescription().getListe_taches().size();i++ ) {
			res+=d.getRealisation(Integer.toString(i+1)).toString()
					+"\tAvancement : "+d.getCurrent(Integer.toString(i+1))+"\n\n";
		}
		System.out.println(res);
	}

	//test
	public static void main(String[] args) {
		Donnees d= new Donnees("1",new JoueurSimple());
		d.getStrategie().jouerEtape(d);
	} 
	
}
