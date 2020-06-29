package junit;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {
	
	@Test
	public void test() {
		try {
//			BufferedImage image = ImageIO.read(new File("C:/Users/Home/Desktop/1.png"));
//			assertNotNull(image);
			
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/1.png"));
			assertNotNull(image2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
