package stegIt;

public class Encoder {

	public Encoder() {
	}

	public void encodeMessage(byte[] sourceData, byte[] encodeData, int sourceOffset) {

		addText(sourceData, encodeData, sourceOffset);

	}

	private void addMessageLength(byte[] data, int length, int offset) {

		byte byte3 = (byte) ((length & 0xFF000000) >>> 24);
		byte byte2 = (byte) ((length & 0x00FF0000) >>> 16);
		byte byte1 = (byte) ((length & 0x0000FF00) >>> 8);
		byte byte0 = (byte) ((length & 0x000000FF));

		byte[] lengthBytes = new byte[] { byte3, byte2, byte1, byte0 };

		for (int i = 0; i < lengthBytes.length; i++) {

			int byt = lengthBytes[i];
			for (int j = 7; j >= 0; j--, offset++) {

				int lsb = (byt >>> j) & 1;
				data[offset] = (byte) ((data[offset] & 0xFE) | lsb);
			}
		}

	}

	private void addText(byte[] dataBytes, byte[] encodeData, int offset) {

		if ((encodeData.length + offset) > dataBytes.length) {
			throw new IllegalArgumentException("File is too small for message!");
		}

		addMessageLength(dataBytes, encodeData.length, offset);
		offset += 32; // increase offset by 32 bits (4 bytes) to account for
						// encoding message length

		for (int i = 0; i < encodeData.length; i++) {
			

			int byt = encodeData[i];
			for (int j = 7; j >= 0; j--, offset++) {
				int lsb = (byt >>> j) & 1;
				dataBytes[offset] = (byte) ((dataBytes[offset] & 0xFE) | lsb);
			}
		}
	}

}
