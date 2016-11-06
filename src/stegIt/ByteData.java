package stegIt;

public abstract class ByteData {

	protected byte[] data;

	public ByteData() {

		data = null;
	}

	public byte[] getData() {
		return data;
	}

	public abstract void write(String destinationPath);

}
