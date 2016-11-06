package stegIt;

public class Decoder {

	public Decoder() {
	}

	/**
	 * Decode message from source data
	 * 
	 * @param sourceFile
	 *            path to image
	 * @return decoded message as a String
	 */
	public String decode(ByteData source, int dataOffset) {


		byte[] dataBytes = source.getData();
		int length = returnMessageLength(dataBytes, dataOffset);
		dataOffset += 32; // increase offset by 32 bits (4 bytes) to account for encoding message length

		return new String(generateMessage(dataBytes, length, (dataOffset)));
	}

	/**
	 * Loops through byte data extracting message (if it exists)
	 * 
	 * @param dataBytes
	 *             array of bytes representing the source data
	 * @param messageBytes
	 *            array to be populated with ASCII codes (stored as bytes)
	 * @return populated array of bytes
	 */
	private byte[] generateMessage(byte[] dataBytes, int length, int offset) {

		byte[] messageBytes = new byte[length];

		
		for (int i = 0; i < messageBytes.length; i++) {

			for (int j = 0; j < 8; j++, offset++) {

				messageBytes[i] = (byte) ((messageBytes[i] << 1) | (dataBytes[offset] & 1));

			}

		}

		return messageBytes;

	}

	/**
	 * Loops through first 32 bytes of data to extract length
	 * starts at offset position
	 * @param dataBytes
	 *            the byte array corresponding to the data to be checked
	 * @return length of message encoded in data
	 */
	private int returnMessageLength(byte[] dataBytes, int offset) {

		int length = 0;
		

		for (int x = 0; x < 32; x++, offset++) {
			length = (length << 1) | (dataBytes[offset] & 1);
			;
		}

		return length;

	}

}
