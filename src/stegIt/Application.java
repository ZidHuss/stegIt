package stegIt;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Encoder enc = new Encoder();
		enc.encodeMessage("cat.png","cat3.png","Hello From the Other Side");

		Decoder dec = new Decoder(); 
		byte[] msg = dec.decode("cat3.png");
		
		System.out.println(new String(msg));
		
		
	
	}

}
