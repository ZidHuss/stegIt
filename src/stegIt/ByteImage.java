package stegIt;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ByteImage extends ByteData {

	static final int OFFSET = 0;
	private BufferedImage image;

	public ByteImage(String filePath) {
		super();
		image = loadImage(filePath);
		super.data = generateImageBytes(image);
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

	public void write(String destination) {

		File file = new File(destination);

		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
