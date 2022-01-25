package in.conceptarchitect.utils.encryption;

public class Encrypt {

	public String encrypt(String str,int salt) {
		return generateHash(str, salt);
	}

	public boolean match(String hash,String plain,int salt) {
		return generateHash(plain,salt).equals(hash);
	}
	
	private String generateHash(String str, int salt) {
		String newPassword="";
		for(int i=0;i<str.length();i++) {
			int ch=(int) str.charAt(i)+salt;
			newPassword+=""+ch+".";
		}
		return newPassword;
	}
	
	public static Encrypt instance=new Encrypt();
}
