package stegIt;

public class Decoder {

	public Decoder() {
	}

	/**
	 * Decode message from source image
	 * @param sourceFile path to image 
	 * @return decoded message as a String
	 */
	public String decode(String sourceFile) {

		BufferedByteImage image = new BufferedByteImage(sourceFile);

		byte[] imageBytes = image.getImageBytes();
		byte[] messageBytes = returnMessageLength(imageBytes);

		messageBytes = generateMessage(imageBytes, messageBytes);

		return new String(messageBytes);
	}
	
	/**
	 * Searches through image byte data extracted message (if it exists)
	 * @param imageBytes
	 * @param messageBytes
	 * @return array of bytes containing the ASCII values for each char
	 */
	private byte[] generateMessage(byte[] imageBytes, byte[] messageBytes) {

		int offset = 32;
		for (int i = 0; i < messageBytes.length; i++) {

			for (int j = 0; j < 8; j++, offset++) {

				messageBytes[i] = (byte) ((messageBytes[i] << 1) | (imageBytes[offset] & 1));

			}

		}

		return messageBytes;

	}

	private byte[] returnMessageLength(byte[] imageBytes) {

		int length = 0;

		for (int x = 0; x < 32; x++) {
			length = (length << 1) | (imageBytes[x] & 1);
			;
		}

		byte[] messageBytes = new byte[length];
		return messageBytes;

	}

}
