package stegIt;

public class Application {

	public static void main(String[] args) {

		Encoder enc = new Encoder();
		Decoder dec = new Decoder();
		
		//ByteDataImage img = new ByteDataImage("cat.png");
		//enc.encodeMessage(img,"cat2.png","Hell Image", ByteDataImage.OFFSET);
		
		//ByteDataImage img2 = new ByteDataImage("cat2.png");
		//String msg = dec.decode(img2.getData(), ByteDataImage.OFFSET);
		
		ByteDataMedia video = new ByteDataMedia("audioTest.mp3");
		
		String msg = "Hello";
		enc.encodeMessage(video.getData(),msg.getBytes(), ByteDataMedia.OFFSET);
		video.write("audioTest.wav");

		ByteData video2 = new ByteDataMedia("audioTest.wav");
		byte[] data = dec.decode(video2.getData(),ByteDataMedia.OFFSET);
		System.out.println(new String(data));


	}

}
