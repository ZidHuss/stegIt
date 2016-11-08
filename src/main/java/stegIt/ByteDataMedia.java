package stegIt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDataMedia extends ByteData {

	static final int OFFSET = 500;
	private BufferedInputStream mediaInput;

	public ByteDataMedia(String filePath) {
		super();
		mediaInput = loadMedia(filePath);
		super.data = generateMediaBytes();

	}

	private BufferedInputStream loadMedia(String filePath) {

		BufferedInputStream mediaInput = null;

		try {
			mediaInput = new BufferedInputStream(new FileInputStream(filePath));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return mediaInput;

	}

	private byte[] generateMediaBytes() {

		byte[] mediaBytes = null;
		try {
			mediaBytes = new byte[mediaInput.available()];
			mediaInput.read(mediaBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mediaBytes;
	}

	public void write(String destinationPath) {

		File file = new File(destinationPath);
		BufferedOutputStream fileOutput = null;

		try {
			fileOutput = new BufferedOutputStream(new FileOutputStream(file));
			fileOutput.write(data);
			fileOutput.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
