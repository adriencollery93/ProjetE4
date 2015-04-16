package m2lmedical;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

//CTRL + SHIFT + O pour générer les imports
public class Connect {
public static void main(String[] args) {      
  try {
	Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver O.K.");

    String url = "jdbc:mysql://localhost/m2lmedical";
    String user = "root";
    String passwd = "";

    @SuppressWarnings("unused")
	Connection conn = (Connection) DriverManager.getConnection(url, user, passwd);
    System.out.println("Connexion effective !");         
       
  } catch (Exception e) {
    e.printStackTrace();
  }      
}
}
