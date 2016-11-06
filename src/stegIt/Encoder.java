package stegIt;

public class Encoder {

	public Encoder() {
	}

	public void encodeMessage(String source, String destination, String msg) {

		BufferedByteImage image = new BufferedByteImage(source);
		addText(image.getImageBytes(), msg, 32);
		image.saveImage(destination);

	}

	private void addMessageLength(byte[] image, int length) {

		int offset = 0;

		byte byte3 = (byte) ((length & 0xFF000000) >>> 24);
		byte byte2 = (byte) ((length & 0x00FF0000) >>> 16);
		byte byte1 = (byte) ((length & 0x0000FF00) >>> 8);
		byte byte0 = (byte) ((length & 0x000000FF));

		byte[] lengthBytes = new byte[] { byte3, byte2, byte1, byte0 };

		for (int i = 0; i < lengthBytes.length; i++) {

			int byt = lengthBytes[i];
			for (int j = 7; j >= 0; j--, offset++) {

				int lsb = (byt >>> j) & 1;
				image[offset] = (byte) ((image[offset] & 0xFE) | lsb);
			}
		}

	}

	private void addText(byte[] imageBytes, String msg, int offset) {

		if ((msg.length() + offset) > imageBytes.length) {
			throw new IllegalArgumentException("File is too small for message!");
		}

		addMessageLength(imageBytes, msg.length());
		byte[] msgBytes = msg.getBytes();

		for (int i = 0; i < msgBytes.length; i++) {

			int byt = msgBytes[i];
			for (int j = 7; j >= 0; j--, offset++) {

				int lsb = (byt >>> j) & 1;
				imageBytes[offset] = (byte) ((imageBytes[offset] & 0xFE) | lsb);

			}
		}

	}

}
