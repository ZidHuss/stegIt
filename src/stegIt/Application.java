package stegIt;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "";
		for(int i = 0; i <= 50; i++) {
			str += "A";
		}
		Encoder enc = new Encoder();
		enc.encodeMessage("cat.png","cat3.png",str);

		Decoder dec = new Decoder(); 
		byte[] msg = dec.decode("cat3.png");
		
		System.out.println(new String(msg));
		

	}

}
