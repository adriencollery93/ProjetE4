package m2lmedical;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class medical {
	
	public static byte[] encrypter(final String message, SecretKey cle)
	throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("DESede");// getInstance() = Renvoyer une instance de l'objet pour un algorithme particulier dont l'impl�mentation est celle fournie par le fournisseur pr�cis�
		cipher.init(Cipher.ENCRYPT_MODE, cle); //Initialiser la classe pour le mode de fonctionnement pr�cis� (Cipher.ENCRYPT_MODE et Cipher.DECRYPT_MODE)
		byte[] donnees = message.getBytes(); // getBytes() = encode un jeu de caract�res en une s�quence d'octets
	
		return cipher.doFinal(donnees); //Ajouter la derni�re partie des donn�es � traiter et g�n�rer le r�sultat
	}
	 
	public static String decrypter(final byte[] donnees, SecretKey cle)	
	throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, cle);
	
		return new String(cipher.doFinal(donnees));
	}
	
	public static SecretKey cleCrypt()
	{
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("DESede");
			keyGen.init(112);
			SecretKey cle = keyGen.generateKey();
			
			return cle;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		final String message = "Mon message � traiter d'urgence ";
		
		try {
			SecretKey cle = cleCrypt(); 
			System.out.println("cle:" + cle.getEncoded());

			byte[] enc = encrypter(message, cle);
			System.out.println("texte encrypte : " + enc);

			String dec = decrypter(enc, cle);
			System.out.println("texte decrypte : " + dec);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
