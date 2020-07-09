package net;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//主要用来调试
//左边显示server的信息，右边显示client的信息
public class TankServerFrame extends Frame {
	
	private static final TankServerFrame INSTANCE = new TankServerFrame();
	
	public static TankServerFrame getInstance() {
		return INSTANCE;
	}
	
	private Button btnStart = new Button("start");
	private TextArea taLeft = new TextArea();
	private TextArea taRight = new TextArea();
	
	TankServer server = new TankServer();
	
	private TankServerFrame() {
		this.setSize(1600, 300);
		this.setLocation(300, 30);
		this.add(btnStart, BorderLayout.NORTH);
		Panel p = new Panel(new GridLayout(1, 2));
		p.add(taLeft);
		p.add(taRight);
		this.add(p);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		TankServerFrame.INSTANCE.setVisible(true);
		//主线程在这里面会卡死，因为f.channel().closeFuture().sync();
		TankServerFrame.INSTANCE.server.serverStart();
	}
	
	public void updateServerMsg(String string) {
		this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + string);
	}
	
	public void updateClientMsg(String string) {
		this.taRight.setText(taRight.getText() + System.getProperty("line.separator") + string);
	}
	
}
