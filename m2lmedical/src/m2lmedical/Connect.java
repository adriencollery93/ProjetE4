package m2lmedical;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Connect {
	
	private String nom, prenom, adresse, ville, cp;
	private Statement stmt = null;

	public Connect(String nom, String prenom, String adresse, String cp, String ville)
	{
		this.nom = nom;
	    this.prenom = prenom;
	    this.adresse = adresse;
	    this.cp = cp;
	    this.ville = ville;
	}
	
	public Connect() {}

	public String toString()
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
	  
	public Connection Connexion()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost/collerym2lmedical";//192.168.60.41:88 qui est le serveur du l
		    String user = "root";
		    String passwd = "";
		    
			Connection conn = (Connection) DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");         
		    
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
		                 "VALUES ('"+ nom +"', '"+ prenom +"','"+ adresse +"', "+ cp +", '"+ ville +"' )";

			System.out.println(sql);
		    stmt.executeUpdate(sql);
		    
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
	public static void main(String[] args) {
		Connect connn = new Connect("Collery","Adrien", "13 rue soulier", "93210", "Auber");
		connn.insertSportif("Collery","Adrien", "13 rue soulier", "93210", "Auber");

	}
}
