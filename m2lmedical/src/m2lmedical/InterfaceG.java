package m2lmedical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;


public class InterfaceG extends Connect {
	
	private JFrame f = new JFrame();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu sportif = new JMenu("Sportif");
	private JMenu visite = new JMenu("Visite médicale");
	 
	private JMenuItem ajout = new JMenuItem("Ajouter un sportif");
	private JMenuItem consult = new JMenuItem("Consulter un dossier");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle visite");
	
	private String typeForm = null;
	
	
	public InterfaceG()
	{
		f.setTitle("Medical");
	    f.setSize(600, 800);
	    f.setLayout(new FlowLayout());
	    f.setLocationRelativeTo(null);//Nous demandons maintenant à notre objet de se positionner au centre
	    f.setResizable(false);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    
	    menu();
	    ajoutSportif();
	    nouvelleVisite();
	    consulterDossier();
	    
	    f.setVisible(true);
	}
	
	public void menu()
	{
		f.setJMenuBar(menuBar);
	    menuBar.add(sportif);
	    menuBar.add(visite);
	    sportif.add(ajout);
	    sportif.add(consult);
	    visite.add(nouvelle);
	}

	public void titreFenAjout()
	{
		JPanel titrePan = new JPanel();
		JLabel titre = new JLabel("Ajout d'un sportif ", SwingConstants.CENTER);
		titre.setFont(new Font("Arial", Font.BOLD, 15));
		titre.setBorder(BorderFactory.createLineBorder(Color.black));
		titre.setPreferredSize(new Dimension(200, 100));
		titrePan.add(titre);
		f.add(titrePan);
	}
	
	public void titreFenNouvel()
	{
		JPanel titrePan = new JPanel();
		JLabel titre = new JLabel("Nouvelle consultation ", SwingConstants.CENTER);
		titre.setFont(new Font("Arial", Font.BOLD, 15));
		titre.setBorder(BorderFactory.createLineBorder(Color.black));
		titre.setPreferredSize(new Dimension(200, 100));
		titrePan.add(titre);
		f.add(titrePan);
	}
	
	public void titreFenConsult()
	{
		JPanel titrePan = new JPanel();
		JLabel titre = new JLabel("Consulter le dossier d'un sportif ", SwingConstants.CENTER);
		titre.setFont(new Font("Arial", Font.BOLD, 15));
		titre.setBorder(BorderFactory.createLineBorder(Color.black));
		titre.setPreferredSize(new Dimension(200, 100));
		titrePan.add(titre);
		f.add(titrePan);
	}
	
	public void ajoutSportif()
	{
		ajout.addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  f.removeAll();
		    	  titreFenAjout();
		    	  formAjoutSportif();
		    	  f.revalidate();
		      }            
		   });
	}
	
	public void formAjoutSportif()
	{
		titreFenAjout();
		JPanel panNom = new JPanel();
		JTextField nom = new JTextField();
		JLabel nomLabel = new JLabel("Saisir un nom :");
		JPanel panPrenom = new JPanel();
		JTextField prenom = new JTextField();
		JLabel prenomLabel = new JLabel("Saisir un prenom :");
		JPanel panAdresse = new JPanel();
		JTextField adresse = new JTextField();
		JLabel adresseLabel = new JLabel("Saisir une adresse :");
		JPanel panVille = new JPanel();
		JTextField ville = new JTextField();
		JLabel villeLabel = new JLabel("Saisir une ville :");
		JPanel panCp = new JPanel();
		JTextField cp = new JTextField();
		JLabel cpLabel = new JLabel("Saisir un code postal :");
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		
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
	    
	    f.add(control);
	    control.add(okBouton);
	    
	    okBouton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{   	    	
	    		Connect sportifInfo = new Connect(nom.getText(), prenom.getText(), adresse.getText(), cp.getText(), ville.getText(), null);
	    		sportifInfo.insertSportif(nom.getText(), prenom.getText(), adresse.getText(), cp.getText(), ville.getText());
	    		JLabel sportifInfoStr = new JLabel(sportifInfo.toStringSportif());
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
	    
	}	    

	public void nouvelleVisite()
	{
		nouvelle.addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  typeForm="nouvVisite";
		    	  titreFenNouvel();
		    	  formNouvelleVisite();
		    	  f.revalidate();
		      }            
		   });
	}

	public void formNouvelleVisite()
	{
		titreFenNouvel();
		rechercheSportif();		
		f.revalidate();
	}

	public void rechercheSportif()
	{
		JPanel panRechNom = new JPanel();
		JTextField rechNom = new JTextField();
		JLabel rechNomLabel = new JLabel("Veuillez saisir le nom du sportif : ");
		JPanel panRechPrenom = new JPanel();
		JTextField rechPrenom = new JTextField();
		JLabel rechPrenomLabel = new JLabel("Veuillez saisir le prenom du sportif : ");
		
		JPanel rechBoutonPan = new JPanel();
		JButton rechercheBouton = new JButton("OK");		
		
		panRechNom.setBackground(Color.white);
	    panRechNom.setPreferredSize(new Dimension(220, 100));
	    panRechNom.setBorder(BorderFactory.createTitledBorder("Saisir un nom"));
	    rechNom.setPreferredSize(new Dimension(100, 25));	    
	    panRechNom.add(rechNomLabel);
	    panRechNom.add(rechNom);
	    f.add(panRechNom);

		panRechPrenom.setBackground(Color.white);
	    panRechPrenom.setPreferredSize(new Dimension(220, 100));
	    panRechPrenom.setBorder(BorderFactory.createTitledBorder("Saisir un prenom"));
	    rechPrenom.setPreferredSize(new Dimension(100, 25));	    
	    panRechPrenom.add(rechPrenomLabel);
	    panRechPrenom.add(rechPrenom);
	    f.add(panRechPrenom);	 

	    f.add(rechBoutonPan);
	    rechBoutonPan.add(rechercheBouton);
	    
	    rechercheBouton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{   
	    		String nom = rechNom.getText();
	    		String prenom = rechPrenom.getText();
	    		Connect verifSportif = new Connect(nom, prenom, null, null, null, null);
	    		boolean verif = verifSportif.rechercheSportifExiste(nom, prenom);
	    		//System.out.println( verifSportif.rechercheSportifExiste(nom, prenom ));
	    		//System.out.println("Nom: "+ nom +" Prenom: "+ prenom);
	    		if ( verif )
	    		{
	    			int verifRedirectionForm = typeForm.compareTo("nouvVisite");
	    			if(verifRedirectionForm == 0)
	    			{
		    			formNouvelleConsultation(nom, prenom);
		    			f.revalidate();
	    			}else
	    				{
	    				choixDossierDate(nom, prenom);
	    				}
	    					/*affichageDossier(nom, prenom);
	    					f.revalidate();
	    					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
	    							| IllegalBlockSizeException | BadPaddingException e1) {
	    						e1.printStackTrace();
	    					}*/		
	    		}else
	    		{	    			
	    			JPanel control = new JPanel();
	    			JButton creationBouton = new JButton("Le sportif n'existe pas, veuillez le creer.");
	    			f.add(control);
	    		    control.add(creationBouton);
	    		    
	    			f.revalidate();
	    		    
	    		    creationBouton.addActionListener(new ActionListener()
	    		    {
	    		    	public void actionPerformed(ActionEvent e) 
	    		    	{	    		    	
	    		    		formAjoutSportif();		    		    		
	    		    		f.revalidate();
	    		    	}	
	    		    });   		    		
	    		}
		    	f.revalidate();
	    	}	    	
	    });
	    	    
	}
	
	public void formNouvelleConsultation(String nom, String prenom)
	{
		titreFenNouvel();
		JPanel panNom = new JPanel();		
		JLabel nomLabel = new JLabel();
		JPanel panPrenom = new JPanel();		
		JLabel prenomLabel = new JLabel();
		JPanel panConsult = new JPanel();
		JTextArea consult = new JTextArea();
		JLabel consultLabel = new JLabel("Saisir les informations de la consultations :");
		
		JPanel control = new JPanel();
		JButton validerBouton = new JButton("Valider");
		
		panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(150, 50));
	    //panNom.setBorder(BorderFactory.createTitledBorder("Nom du sportif"));    
	    nomLabel.setText(nom);
	    panNom.add(nomLabel);
	    f.add(panNom);
	    
	    panPrenom.setBackground(Color.white);
	    panPrenom.setPreferredSize(new Dimension(150, 50));
	    //panNom.setBorder(BorderFactory.createTitledBorder("Nom du sportif"));    
	    prenomLabel.setText(prenom);
	    panPrenom.add(prenomLabel);
	    f.add(panPrenom);
	    
	    panConsult.setBackground(Color.white);
	    panConsult.setPreferredSize(new Dimension(300, 200));
	    panConsult.setBorder(BorderFactory.createTitledBorder("Information de la consultation"));
	    consult.setPreferredSize(new Dimension(280, 150));
	    consult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	    panConsult.add(consultLabel);
	    panConsult.add(consult);
	    f.add(panConsult);
	    
	    f.add(control);
	    control.add(validerBouton);
	    
	    validerBouton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{   
	    		Connect consultInfo = new Connect(nom, prenom, null, null, null,  consult.getText());
	    		JLabel consultInfoStr = new JLabel(consultInfo.toStringConsult());
	    		f.add(consultInfoStr);
	    		f.revalidate();
	    		try {
					insertConsultation(nom, prenom, consult.getText() );
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException e1) {
					e1.printStackTrace();
				}
	    	}
	    });	         
	}
	
	public void consulterDossier()
	{
		consult.addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  typeForm="consultDossier";
		    	  titreFenConsult();
		    	  rechercheSportif();
		    	  f.revalidate();
		      }            
		   });
	}
	
	public void choixDossierDate(String nom, String prenom)
	{
		titreFenConsult();
		int id = recupIdSportif(nom, prenom);
		int nbLigne=0;
		try {
		    //Création d'un objet Statement
		    Statement state = (Statement) Connexion().createStatement();
		    //L'objet ResultSet contient le résultat de la requête SQL
		    ResultSet resultNbLigne = state.executeQuery(	"SELECT COUNT(numero_v)  "+
		    												"FROM visite, sportif "+
		    												"WHERE sportif.id = visite.id AND visite.id ="+ id +" GROUP BY date_v ");
		    		    
		    while(resultNbLigne.next())
		    {
		    	nbLigne = resultNbLigne.getInt(1);
		    }
		    resultNbLigne.close();
		    
		    ResultSet result = state.executeQuery(	"SELECT date_v, COUNT(numero_v)  "+
													"FROM visite, sportif "+
													"WHERE sportif.id = visite.id AND visite.id ="+ id +" GROUP BY date_v ");
		    
		    JPanel panEnsembleContenu = new JPanel();
		    JButton[] dateBouton = new JButton[nbLigne];	    
		    f.add(panEnsembleContenu);
		    	    	
		    panEnsembleContenu.setBackground(Color.white);
		    panEnsembleContenu.setPreferredSize(new Dimension(300, 150));
		    panEnsembleContenu.setBorder(BorderFactory.createTitledBorder("Date de consultation"));
		    panEnsembleContenu.setLayout(new FlowLayout());
	    	f.add(panEnsembleContenu);
		    
		    while(result.next())
		    {         
		    	for(int i = 0; i <= nbLigne-1; i++)
		    	{
		    		String date_v = result.getObject(1).toString(); 
		    		dateBouton[i] = new JButton();
		    		dateBouton[i].setBackground(Color.white);
		    		//dateBouton[i].setPreferredSize(new Dimension(200, 100));	 
			    	dateBouton[i].setText(date_v);
			    	panEnsembleContenu.add(dateBouton[i]);
			    	dateBouton[i].addActionListener(new ActionListener()
				    {
				    	public void actionPerformed(ActionEvent e) 
				    	{  
							try {
								affichageDossier(id, date_v);
							} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
									| IllegalBlockSizeException | BadPaddingException e1) {
								e1.printStackTrace();
							}
							f.revalidate();				    		
				    	}
				    });			    
		    	}
		    }

		    result.close();
		    state.close();
		    
			} catch(SQLException se)
			{
			      //Handle errors for JDBC
			      se.printStackTrace();
			}
		
	}
	
	public void affichageDossier(int id, String date_v) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		titreFenConsult();
		try {
				         
		    //Création d'un objet Statement
		    Statement state = (Statement) Connexion().createStatement();
		    //L'objet ResultSet contient le résultat de la requête SQL
		    ResultSet result = state.executeQuery(	"SELECT numero_v, nom, prenom, date_v, info_visite "+
		    										"FROM visite, sportif "+
		    										"WHERE sportif.id = visite.id AND visite.id ="+ id +" "
		    										+ "AND date_v =\""+ date_v + "\" LIMIT 1");
		    //On récupère les MetaData
		    ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();
		    
		    JPanel panEnsembleContenu = new JPanel();
		    JPanel[] panNomChamps = new JPanel[5];
		    JLabel[] contenuChamps = new JLabel[4];
		    JLabel infoConsulContenuChamps = new JLabel();
		    
		    panEnsembleContenu.setLayout(new GridLayout(3,2));;
		    f.add(panEnsembleContenu);
		    
		    for(int i = 0; i <= resultMeta.getColumnCount()-2; i++)
		    {
		    	panNomChamps[i]= new JPanel();
		    	panNomChamps[i].setBackground(Color.white);
		    	panNomChamps[i].setPreferredSize(new Dimension(200, 100));
		    	panEnsembleContenu.add(panNomChamps[i]);	 
		    }

	    	panNomChamps[0].setBorder(BorderFactory.createTitledBorder("Numero de la visite"));
	    	panNomChamps[1].setBorder(BorderFactory.createTitledBorder("Nom"));
	    	panNomChamps[2].setBorder(BorderFactory.createTitledBorder("Prenom"));
	    	panNomChamps[3].setBorder(BorderFactory.createTitledBorder("Date"));
		    	
	    	panNomChamps[4]= new JPanel();		    	
	    	panNomChamps[4].setBackground(Color.white);
	    	panNomChamps[4].setPreferredSize(new Dimension(300, 150));
	    	panNomChamps[4].setBorder(BorderFactory.createTitledBorder("Informations sur la visite médicale"));
	    	panEnsembleContenu.add(panNomChamps[4]);
		    
		    while(result.next())
		    {         
		    	/*for(int i = 0; i <= resultMeta.getColumnCount()-2; i++)
		    	{*/
		    	contenuChamps[0]= new JLabel();
		    	contenuChamps[0].setPreferredSize(new Dimension(150, 50));	    
		    	contenuChamps[0].setText(result.getObject(1).toString());
			   	panNomChamps[0].add(contenuChamps[0]);		
			   	
			   	contenuChamps[1]= new JLabel();
		    	contenuChamps[1].setPreferredSize(new Dimension(150, 50));	    
		    	contenuChamps[1].setText(result.getObject(2).toString());
			   	panNomChamps[1].add(contenuChamps[1]);
			   	
			   	contenuChamps[2]= new JLabel();
		    	contenuChamps[2].setPreferredSize(new Dimension(150, 50));	    
		    	contenuChamps[2].setText(result.getObject(3).toString());
			   	panNomChamps[2].add(contenuChamps[2]);
			   	
			   	contenuChamps[3]= new JLabel();
		    	contenuChamps[3].setPreferredSize(new Dimension(150, 50));	    
		    	contenuChamps[3].setText(result.getObject(4).toString());
			   	panNomChamps[3].add(contenuChamps[3]);
		    	
		    	infoConsulContenuChamps.setPreferredSize(new Dimension(200, 150));
		    	String textConsultEnc = result.getString(5).toString();
		    	//byte[] textEnc = textConsultEnc.getBytes();
		    	infoConsulContenuChamps.setText(textConsultEnc  /*decrypter(textEnc, cleCrypt())*/ );
		    	panNomChamps[resultMeta.getColumnCount()-1].add(infoConsulContenuChamps);	
		    }

		    result.close();
		    state.close();
		    
			} catch(SQLException se)
			{
			      //Handle errors for JDBC
			      se.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
	
		new InterfaceG();
			
	}
}
