package stegIt;

public class Application {

	public static void main(String[] args) {

		Encoder enc = new Encoder();
		Decoder dec = new Decoder();
		
		
		ByteMedia audio = new ByteMedia("audio.mp3");
		enc.encodeMessage(audio, "audio2.mp3", "", ByteMedia.OFFSET);

		ByteMedia audio2 = new ByteMedia("audio2.mp3");
		String msg = dec.decode(audio2, ByteMedia.OFFSET);

		System.out.println(msg);

	}

}
