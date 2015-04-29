package m2lmedical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InterfaceG {
	
	private JFrame f = new JFrame();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu sportif = new JMenu("Sportif");
	private JMenu visite = new JMenu("Visite médicale");
	 
	private JMenuItem ajout = new JMenuItem("Ajouter un sportif");
	private JMenuItem consult = new JMenuItem("Consulter un dossier");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle visite");
	
	private JPanel panNom = new JPanel();
	private JTextField nom = new JTextField();
	private JLabel nomLabel = new JLabel("Saisir un nom :");
	private JPanel panPrenom = new JPanel();
	private JTextField prenom = new JTextField();
	private JLabel prenomLabel = new JLabel("Saisir un prenom :");
	private JPanel panAdresse = new JPanel();
	private JTextField adresse = new JTextField();
	private JLabel adresseLabel = new JLabel("Saisir une adresse :");
	private JPanel panVille = new JPanel();
	private JTextField ville = new JTextField();
	private JLabel villeLabel = new JLabel("Saisir une ville :");
	private JPanel panCp = new JPanel();
	private JTextField cp = new JTextField();
	private JLabel cpLabel = new JLabel("Saisir un code postal :");
	
	private JPanel control = new JPanel();
	private JButton okBouton = new JButton("OK");
	
	public InterfaceG()
	{
		f.setTitle("Medical");
	    f.setSize(600, 800);
	    f.setLayout(new FlowLayout());
	    f.setLocationRelativeTo(null);//Nous demandons maintenant à notre objet de se positionner au centre
	    f.setResizable(false);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    f.setJMenuBar(menuBar);
	    menuBar.add(sportif);
	    menuBar.add(visite);
	    sportif.add(ajout);
	    sportif.add(consult);
	    visite.add(nouvelle);
	    
	    ajoutSportif();
	    
	    f.setVisible(true);
	}
	
	public void ajoutSportif()
	{
		ajout.addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  formAjoutSportif();
		    	  f.revalidate();
		      }            
		   });
	}
	
	public void formAjoutSportif()
	{
	    panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(220, 100));
	    panNom.setBorder(BorderFactory.createTitledBorder("Nom du sportif"));
	    nom.setPreferredSize(new Dimension(100, 25));	    
	    panNom.add(nomLabel);
	    panNom.add(nom);
	    f.add(panNom);
	    
	    panPrenom.setBackground(Color.white);
	    panPrenom.setPreferredSize(new Dimension(220, 100));
	    panPrenom.setBorder(BorderFactory.createTitledBorder("Prénom du sportif"));
	    prenom.setPreferredSize(new Dimension(100, 25));	    
	    panPrenom.add(prenomLabel);
	    panPrenom.add(prenom);
	    f.add(panPrenom);
	    
	    panAdresse.setBackground(Color.white);
	    panAdresse.setPreferredSize(new Dimension(220, 100));
	    panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse du sportif"));
	    adresse.setPreferredSize(new Dimension(100, 25));	    
	    panAdresse.add(adresseLabel);
	    panAdresse.add(adresse);
	    f.add(panAdresse);
	    
	    panVille.setBackground(Color.white);
	    panVille.setPreferredSize(new Dimension(220, 100));
	    panVille.setBorder(BorderFactory.createTitledBorder("Ville du sportif"));
	    ville.setPreferredSize(new Dimension(100, 25));	    
	    panVille.add(villeLabel);
	    panVille.add(ville);
	    f.add(panVille);
	    
	    panCp.setBackground(Color.white);
	    panCp.setPreferredSize(new Dimension(220, 100));
	    panCp.setBorder(BorderFactory.createTitledBorder("Code postal du sportif"));
	    cp.setPreferredSize(new Dimension(100, 25));	    
	    panCp.add(cpLabel);
	    panCp.add(cp);
	    f.add(panCp);
	
	    
	    okBouton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{   
	    	
	    		Connect sportifInfo = new Connect(nom.getText(), prenom.getText(), adresse.getText(), cp.getText(), ville.getText());
	    		sportifInfo.insertSportif(nom.getText(), prenom.getText(), adresse.getText(), cp.getText(), ville.getText());
	    		JLabel sportifInfoStr = new JLabel(sportifInfo.toString());
	    		f.add(sportifInfoStr);
	    		
	    		f.remove(panNom);
	    		f.remove(panPrenom);
	    		f.remove(panAdresse);
	    		f.remove(panVille);
	    		f.remove(panCp);
	    		f.remove(control);
	    		f.revalidate();
	    	}	
	    });
	    
	    f.add(control);
	    control.add(okBouton);
	}	    

	
	public static void main(String[] args) {
	
		new InterfaceG();
			
	}
}
