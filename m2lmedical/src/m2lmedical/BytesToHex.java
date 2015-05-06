package m2lmedical;

public abstract class BytesToHex 
{
	 
	  public static String bytesToHex(byte[] bytes) 
	  {
	    StringBuilder builder = new StringBuilder();
	    for (byte b: bytes) 
	    {
	      builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	  }

	  public static byte[] hexStringToByteArray(String s) 
	  {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) 
		    {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
}
