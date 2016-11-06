package stegIt;

public class Decoder {

	public Decoder() {
	}

	public String decode(String sourceFile) {

		BufferedByteImage image = new BufferedByteImage(sourceFile);

		byte[] imgBytes = image.getImageBytes();
		byte[] msgBytes = returnMessageLength(imgBytes);

		msgBytes = generateMessage(imgBytes, msgBytes);

		return new String(msgBytes);
	}

	private byte[] generateMessage(byte[] imgBytes, byte[] msgBytes) {

		int offset = 32;
		for (int i = 0; i < msgBytes.length; i++) {

			for (int j = 0; j < 8; j++, offset++) {

				msgBytes[i] = (byte) ((msgBytes[i] << 1) | (imgBytes[offset] & 1));

			}

		}

		return msgBytes;

	}

	private byte[] returnMessageLength(byte[] imgBytes) {

		int length = 0;

		for (int x = 0; x < 32; x++) {
			length = (length << 1) | (imgBytes[x] & 1);
			;
		}

		byte[] msgBytes = new byte[length];
		return msgBytes;

	}

}
