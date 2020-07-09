package net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;

public class PlayerJoinMsg extends Msg {
	
	private static final MsgType TYPE = MsgType.playerJoin;
	
	int x, y;
	Dir dir;
	boolean moving;
	Group group;
	public UUID id;
	String name;
	
	public PlayerJoinMsg() {}
	
	public PlayerJoinMsg(Player player) {
		super();
		this.x = player.x;
		this.y = player.y;
		this.moving = player.isMoving();
		this.dir = player.getDir();
		this.group = player.getGroup();
		this.id = player.getId();
		this.name = player.getName();
	}
	
	@Override
	public byte[] getBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeInt(TYPE.ordinal());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeBoolean(moving);
			dos.writeInt(dir.ordinal());
			dos.writeInt(group.ordinal());
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			dos.writeUTF(name);
			dos.flush();
			bytes = baos.toByteArray();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos!=null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos!=null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}

class Player {
	int x, y;
	boolean moving;
	Dir dir;
	Group group;
	UUID id;
	String name;
	
	public boolean isMoving() {
		return moving;
	}
	public Dir getDir() {
		return dir;
	}
	public Group getGroup() {
		return group;
	}
	public UUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
