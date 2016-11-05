package stegIt;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Encoder enc = new Encoder();
		enc.encodeMessage("cat.jpg","cat3.jpg","Hello From the Other Side");

		Decoder dec = new Decoder(); 
		byte[] msg = dec.decode("cat3.jpg");
		
		System.out.println(new String(msg));
		
		
	
	}

}
