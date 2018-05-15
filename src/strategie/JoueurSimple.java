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
		Integer acceleration;
		affichage(d);
		//String saisieCouleur= JOptionPane.showConfirmDialog(null, "Voulez-vous protéger?",  JOptionPane.YES_NO_OPTION);
		Integer protection = JOptionPane.showConfirmDialog(null , "Desirez-vous protéger?");
		//System.out.println(oui);
		if(protection == 0){
			String saisietache= JOptionPane.showInputDialog("Quelle tache à proteger?");
			String saisieCouleur = JOptionPane.showInputDialog("Quelle couleur?");
				if(saisieCouleur.equals("Rouge") || saisieCouleur.equals("rouge")) d.setProtection(saisietache, Couleur.ROUGE, true);
				if(saisieCouleur.equals("Vert") || saisieCouleur.equals("vert")) d.setProtection(saisietache, Couleur.VERT, true);
				if(saisieCouleur.equals("Jaune") || saisieCouleur.equals("jaune")) d.setProtection(saisietache, Couleur.JAUNE, true);
				d.depense(10);
		}
		
		do {
		 acceleration = JOptionPane.showConfirmDialog(null , "Desirez-vous accélerer?");
		 if(acceleration != 1 ) {
		 String saisieTache= JOptionPane.showInputDialog("Quelle tache à accélerer?");
		 d.setAcceleration(saisieTache, true);
		// d.depense(d.getRealisation(saisieTache).getTache().getCoutAcceleration()/2);
		 }
		 affichage(d);
		}while(acceleration != 1);
		
		
		//d.actualisation();
		
		affichage(d);
		
		
	}

	@Override
	public void jouerTest( Donnees d) {
		// TODO Auto-generated method stub
		
	}
	
	private void affichage(Donnees d) {
		
		String res="Tour : "+ d.getNumeroTour()
					+"\nNom : "+d.getNom()
					+"\nCaisse : "+d.getCaisse()+"\tQualité : "+d.getQualite()+"%\n\n";
		for(int i=0;i<d.getDescription().getListe_taches().size();i++ ) {
			
			res+=d.getRealisation(Integer.toString(i+1)).toString()
					+"\tAvancement : "+d.getCurrent(Integer.toString(i+1))+"\n\n";
		}
		System.out.println(res);
	}

	//test
	public static void main(String[] args) {
		Donnees d= new Donnees("1",new JoueurSimple());
		d.getStrategie().jouerJalon(d);
	} 
	
}
