package m2lmedical;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Connect extends medical{
	
	private String nom, prenom, adresse, ville, cp, consult;
	private Statement stmt = null;

	public Connect(String nom, String prenom, String adresse, String cp, String ville, String consult)
	{
		this.nom = nom;
	    this.prenom = prenom;
	    this.adresse = adresse;
	    this.cp = cp;
	    this.ville = ville;
	    this.consult = consult;
	}
	
	public Connect() {}

	public String toStringSportif()
	{
		String str;
		if(this.nom != null && this.prenom != null && this.adresse != null && this.ville != null && this.cp != null)
		{
		      str = "<HTML>Information sur le sportif <BR>";
		      str += "Nom : " + this.nom + "<BR>";
		      str += "Prénom : " + this.prenom + "<BR>";
		      str += "Adresse : " + this.adresse + "<BR>";
		      str += "Ville : " + this.ville + "<BR>";
		      str += "Code postal : " + this.cp + "</HTML>";
		}else
		{
		      str = "Aucune information !";
		}
		return str;
	}
	 
	public String toStringConsult()
	{
		String str;
		if(this.nom != null && this.prenom != null && this.consult != null)
		{
		      str = "<HTML>Information sur la consultation du sportif <BR>";
		      str += "Nom : " + this.nom + "<BR>";
		      str += "Prénom : " + this.prenom + "<BR>";
		      str += "Information sur la consultation : " + this.consult + "<BR></HTML>";
		}else
		{
		      str = "Aucune information !";
		}
		return str;
	}	
	
	public Connection Connexion()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    //System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost/collerym2lmedical";//192.168.60.41:88 qui est le serveur du lycee
		    String user = "root";
		    String passwd = "";
		    
			Connection conn = (Connection) DriverManager.getConnection(url, user, passwd);
		    //System.out.println("Connexion effective !");         
		    
			return conn;
			
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  }      
	}

	public void insertSportif(String nom, String prenom, String adresse, String cp, String ville)
	{		
		try{    
			stmt = (Statement) Connexion().createStatement();  
			String sql = "INSERT INTO Sportif " +
		                 "VALUES (default, '"+ nom +"', '"+ prenom +"','"+ adresse +"', "+ cp +", '"+ ville +"' )";
			//System.out.println(sql);
		    stmt.executeUpdate(sql);
		    stmt.close();
		    
		   }
		catch(SQLException se)
		{
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public boolean rechercheSportifExiste(String nom, String prenom)
	{
		boolean verifSportifExiste = false;
		try{    
			stmt = (Statement) Connexion().createStatement();  
			String sql = "SELECT * FROM sportif" +
		                 " WHERE nom LIKE \""+ nom +"\" " +
		                 "AND prenom LIKE \""+ prenom+"\" ";
			//System.out.println(sql);
		    ResultSet result = stmt.executeQuery(sql);
		    
		    while( result.next() ) 
		    {
		    	result.getObject(1);
		    	//System.out.println(result.getObject(1));
		    	if (!result.wasNull()) 
		    	{
		    		//System.out.println("test");
		    		verifSportifExiste = true;
		    	}
		    }

		    //System.out.println(verifSportifExiste);
		    
		    return verifSportifExiste;
		    
		   }
		catch(SQLException se)
		{
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	    return verifSportifExiste;
	}
	
	public int recupIdSportif(String nom, String prenom)
	{	
		int id=0;
		try{    
			stmt = (Statement) Connexion().createStatement();  
			String sql = "SELECT id FROM sportif" +
		                 " WHERE nom LIKE \""+ nom +"\" " +
		                 "AND prenom LIKE \""+ prenom+"\" ";
			//System.out.println(sql);
		    ResultSet result = stmt.executeQuery(sql);
		  
		    while(result.next())
		    {
		    	id = result.getInt("id");
		    }
		    
		    //System.out.println(id);

		    result.close();
		    stmt.close();
		    
		    return id;
		   }
		catch(SQLException se)
		{
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return id;
	}
	
	public void insertConsultation(String nom, String prenom, String consult) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		int id = recupIdSportif(nom, prenom);
		/*byte[] cryptInfoConsult = encrypter(consult, cleCrypt());
		String infoConsult = BytesToHex.bytesToHex(cryptInfoConsult);*/
		try{    
			stmt = (Statement) Connexion().createStatement();  
			String sql = "INSERT INTO Visite " +
		                 "VALUES (default, "+ id +", current_date(), \""+ consult +"\" )";
			//System.out.println(sql);
		    stmt.executeUpdate(sql);	    

		    stmt.close();
		    
		   }
		catch(SQLException se)
		{
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void affichageDossier(String nom, String prenom) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		int id = recupIdSportif(nom, prenom);
		try {
				         
		    //Création d'un objet Statement
		    Statement state = (Statement) Connexion().createStatement();
		    //L'objet ResultSet contient le résultat de la requête SQL
		    ResultSet result = state.executeQuery(	"SELECT nom, prenom, numero_v, date_v, info_visite "+
		    										"FROM visite, sportif "+
		    										"WHERE sportif.id = visite.id AND visite.id ="+ id +" ");
		    //On récupère les MetaData
		    ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();
		    
		    for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		    {
		    	System.out.println(resultMeta.getColumnName(i).toUpperCase());
		    }		    
		         
		    while(result.next())
		    {         
		    	for(int i = 1; i <= resultMeta.getColumnCount()-1; i++)
		    	{
		    		System.out.println(result.getObject(i).toString());		    		
		    	}
		   
		    	String textConsultEnc = result.getObject(resultMeta.getColumnCount()).toString();
		    	System.out.println("Récupération du stockage en base: "+ textConsultEnc);
		    	
		    	byte[] textEnc = BytesToHex.hexStringToByteArray(textConsultEnc);
		    	System.out.println("Conversion: "+ textEnc);
		    	
		    	System.out.println("Texte décryté: " + decrypter(textEnc, cleCrypt()));
		    }

		    result.close();
		    state.close();
		    
			} catch(SQLException se)
			{
			      //Handle errors for JDBC
			      se.printStackTrace();
			}
	}
	public static void main(String[] args)
	{
		Connect connn = new Connect();
		
		try {
			connn.affichageDossier("Collery", "Adrien");
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		/*System.out.println(connn.rechercheSportifExiste("Collery","Adrien")); 
		System.out.println(connn.recupIdSportif("Collery","Adrien"));
		try {
			connn.insertConsultation("Collery", "Adrien", "Test de message pour une consultation");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}*/
	}
}
