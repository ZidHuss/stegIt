package stegIt;

public class Application {

	public static void main(String[] args) {

		Encoder enc = new Encoder();
		enc.encodeMessage("cat.png", "cat6.png", "Hello Ben it is Rhiannon make a cup of tea");

		Decoder dec = new Decoder();
		String msg = dec.decode("cat6.png");

		System.out.println(new String(msg));

	}

}
