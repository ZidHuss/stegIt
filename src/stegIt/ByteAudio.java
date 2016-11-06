package stegIt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteAudio extends ByteData {

	static final int OFFSET = 500;
	private FileInputStream audioInput;

	public ByteAudio(String filePath) {
		super();
		audioInput = loadAudio(filePath);
		super.data = generateAudioBytes();

	}

	private FileInputStream loadAudio(String filePath) {
		FileInputStream audioInput = null;

		try {
			audioInput = new FileInputStream(filePath);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return audioInput;

	}

	private byte[] generateAudioBytes() {

		byte[] audioBytes = null;
		try {
			audioBytes = new byte[audioInput.available()];
			audioInput.read(audioBytes);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return audioBytes;
	}

	public void write(String destinationPath) {

		File file = new File(destinationPath);
		FileOutputStream fileOutput = null;
		try {
			fileOutput = new FileOutputStream(file);
			fileOutput.write(data);
			fileOutput.flush();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
