package stegIt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedByteImage {

	private byte[] imageBytes;
	private BufferedImage image;

	public BufferedByteImage(String filePath) {
		image = loadImage(filePath);
		imageBytes = generateImageBytes(image);
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}


	private BufferedImage loadImage(String source) {
		File file = new File(source);

		BufferedImage image = null;

		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	private byte[] generateImageBytes(BufferedImage image) {

		WritableRaster raster = image.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

		return buffer.getData();
	}

	public void saveImage(String destination) {

		File file = new File(destination);

		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
