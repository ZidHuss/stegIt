package stegIt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Encoder {

	public Encoder() {
	}

	public BufferedImage sandboxImage(String sourcePath) {
		BufferedImage img = null;
		File file = new File(sourcePath);

		try {
			img = ImageIO.read(file);
		} catch (IOException e) {

			e.printStackTrace();
		}

		BufferedImage sandboxedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D graphics = sandboxedImg.createGraphics();
		graphics.drawRenderedImage(img, null);
		graphics.dispose();

		return sandboxedImg;
	}

	public byte[] generateImageBytes(BufferedImage image) {

		WritableRaster raster = image.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

		return buffer.getData();

	}

	public void saveImage(BufferedImage image, String destinationPath) {

		File file = new File(destinationPath);

		try {
			file.delete();
			ImageIO.write(image, "png", file);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void encodeMessage(String source, String destination, String msg) {

		BufferedImage image = sandboxImage(source);
		
		try {
		image = addText(image, msg, 32);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		saveImage(image, destination);

	}

	public void addMessageLength(byte[] image, int length) {

		
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

	public BufferedImage addText(BufferedImage image, String msg, int offset) {
	
		byte[] imgBytes = generateImageBytes(image);
		
		if((msg.length() + offset) > imgBytes.length) {
			throw new IllegalArgumentException("File is too small for message!"); 
		}
		
		addMessageLength(imgBytes, msg.length());
		byte[] msgBytes = msg.getBytes();

		for (int i = 0; i < msgBytes.length; i++) {

			int byt = msgBytes[i];
			for (int j = 7; j >= 0; j--, offset++) {

				int lsb = (byt >>> j) & 1;
				imgBytes[offset] = (byte) ((imgBytes[offset] & 0xFE) | lsb);

			}
		}

		return image;
	}

}
