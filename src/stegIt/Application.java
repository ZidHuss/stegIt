package stegIt;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "";
		for(int i = 0; i <= 50; i++) {
			str += "A";
		}
		Encoder enc = new Encoder();
		enc.encodeMessage("cat.png","cat4.png",str);

		 Decoder dec = new Decoder(); 
		 String msg = dec.decode("cat4.png");
		
		 System.out.println(new String(msg));
		

	}

}
