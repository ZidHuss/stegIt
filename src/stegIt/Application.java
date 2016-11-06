package stegIt;

public class Application {

	public static void main(String[] args) {

		Encoder enc = new Encoder();
		Decoder dec = new Decoder();
		
		
		ByteMedia audio = new ByteMedia("video.mkv");
		enc.encodeMessage(audio, "video2.mkv", "Hello From the other side again", ByteMedia.OFFSET);

		ByteMedia audio2 = new ByteMedia("video2.mkv");
		String msg = dec.decode(audio2, ByteMedia.OFFSET);

		System.out.println(msg);

	}

}
