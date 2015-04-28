package m2lmedical;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

//CTRL + SHIFT + O pour générer les imports
public class Connect {
	
	public void Connexion()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost/collerym2lmedical";//192.168.60.41:88 qui est le serveur du l
		    String user = "root";
		    String passwd = "";

		    @SuppressWarnings("unused")
			Connection conn = (Connection) DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");         
		       
		  } catch (Exception e) {
		    e.printStackTrace();
		  }      
	}

	/*public static void main(String[] args) {
		Connect connex = new Connect();
		connex.Connexion();
	}*/
	
}
