package stegIt;

public class Application {

	public static void main(String[] args) {

		Encoder enc = new Encoder();
		ByteImage img = new ByteImage("cat.png");
		enc.encodeMessage(img, "cat6.png", "Hello Ben it is Rhiannon make a cup of tea", ByteImage.OFFSET);

		Decoder dec = new Decoder();
		ByteImage imgDec = new ByteImage("cat6.png");
		String msg = dec.decode(imgDec, ByteImage.OFFSET);

		System.out.println(new String(msg));

		ByteAudio audio = new ByteAudio("audioCheck.wav");
		enc.encodeMessage(audio, "audioCheck3.wav", "Hello From the other side", ByteAudio.OFFSET);

		ByteAudio audio2 = new ByteAudio("audioCheck3.wav");
		msg = dec.decode(audio2, ByteAudio.OFFSET);

		System.out.println(msg);

	}

}
