package com.mashibing.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {
	
	public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
	public static BufferedImage badTankL, badTankR, badTankU, badTankD;
	public static BufferedImage bL, bR, bU, bD;
	public static BufferedImage[] fire = new BufferedImage[3];
	public static BufferedImage[] explode = new BufferedImage[16];
	
	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);

			bU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bL = ImageUtil.rotateImage(bU, -90);
			bR = ImageUtil.rotateImage(bU, 90);
			bD = ImageUtil.rotateImage(bU, 180);
			
			for(int i=0;i<fire.length;i++) {
				fire[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/fire" + (i+1) + ".png"));
			}
			
			for(int i=0;i<explode.length;i++) {
				explode[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
