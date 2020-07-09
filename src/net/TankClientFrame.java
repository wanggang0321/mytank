package net;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClientFrame extends Frame {
	
	private static final TankClientFrame INSTANCE = new TankClientFrame();
	
	private TankClientFrame() {
		
		this.setSize(600, 400);
		this.setLocation(100, 20);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static TankClientFrame getInstance() {
		return INSTANCE;
	}
	
	public void startGame() {
		TankClient c = new TankClient();
		c.clientStart();
	}
	
	public static void main(String[] args) {
		TankClientFrame.INSTANCE.setVisible(true);
		TankClientFrame.INSTANCE.startGame();
	}
	
}
