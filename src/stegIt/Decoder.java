package stegIt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Decoder {
	
	
	
	public Decoder() {}
	
	public BufferedImage sandboxImage(String sourcePath) {
		BufferedImage img = null;
		File file = new File(sourcePath);

		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		BufferedImage sandboxedImg = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D graphics = sandboxedImg.createGraphics();
		graphics.drawRenderedImage(img, null);
		graphics.dispose();
		return sandboxedImg;
	}
	
	public byte[] generateBytes(String sourceFile) {
		
		BufferedImage img = sandboxImage(sourceFile);
		
		WritableRaster raster = img.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

		return buffer.getData();
		
	}
	
	public byte[] decode(String sourceFile) {
		
		int length = 0; 
		int offset  = 32; 
		
		byte[] imgBytes = generateBytes(sourceFile);
		
		for(int x = 0; x < 32; x++) {
			length = (length << 1) | (imgBytes[x] & 1); 
		}
		
		byte[] msgBytes = new byte[length];
		
		
		for(int i = 0; i < msgBytes.length; ++i) {
			
			for(int j = 0; j < 8; j++, offset++) {
				
				msgBytes[i] = (byte) ((msgBytes[i] << 1)| (imgBytes[offset] & 1));
				
			}
		}
	
		
		return msgBytes; 
	}

}
