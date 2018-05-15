package strategie;


import javax.swing.JOptionPane;
import description.Couleur;
import partie.*;

public class JoueurSimple implements Strategie{

	@Override
	public void jouerSemaine( Donnees d) {		
		
		affichage(d);	
		d.FinDuTour();
	}

	@Override
	public void jouerJalon( Donnees d) {

		Integer acceleration;
		//affichage(d);
		Integer protection = JOptionPane.showConfirmDialog(null , "Desirez-vous protéger?");
		if(protection == 0){
			String saisietache= JOptionPane.showInputDialog("Quelle tache à proteger?");
			String saisieCouleur = JOptionPane.showInputDialog("Quelle couleur?");
			saisieCouleur.toLowerCase();
			if(saisieCouleur.equals("rouge")) d.setProtection(saisietache, Couleur.ROUGE, true);
			if(saisieCouleur.equals("vert")) d.setProtection(saisietache, Couleur.VERT, true);
			if(saisieCouleur.equals("jaune")) d.setProtection(saisietache, Couleur.JAUNE, true);
		}

		do {
			acceleration = JOptionPane.showConfirmDialog(null , "Desirez-vous accélerer?");
			if(acceleration != 1 ) {
				String saisieTache= JOptionPane.showInputDialog("Quelle tache à accélerer?");
				d.setAcceleration(saisieTache, true);
				
			}
		}while(acceleration != 1);
	
		affichage(d);
		//d.FinDuTour();

		//affichage(d);
	}

	@Override
	public void jouerQuizz( Donnees d) {
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
	/*public static void main(String[] args) {
		Donnees d= new Donnees("1",new JoueurSimple());
		d.getStrategie().jouerJalon(d);
		d.getStrategie().jouerSemaine(d);
	} */

}
