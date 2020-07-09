package net;

import java.util.ArrayList;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TankServer {
	
	public static List<Channel> clients = new ArrayList<Channel>();
	
	public void serverStart() {
		
		//��ܼ�
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		//����Ա
		EventLoopGroup workerGroup = new NioEventLoopGroup(1);
		
		//ѥ�Ӵ�
		ServerBootstrap b = new ServerBootstrap();
		
		try {
			//ChannelFuture�����ж�connect����³ɹ�û�ɹ�
			ChannelFuture f = b.group(bossGroup, workerGroup) //ָ���̳߳�
					.channel(NioServerSocketChannel.class) //ָ��channel����
					.childHandler(new ServerlChannelInitializer()) //��channel�����¼�����ʱ�򣬽����ĸ�handler����
					.bind(8888)
					.sync();
			
			System.out.println("Tank server started!");
			TankServerFrame.getInstance().updateServerMsg("�о�����5�뵽��ս����������׼����");
			
			f.channel().closeFuture().sync();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}

class ServerlChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ChildServerHandler());
	}
}

class ChildServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		TankServer.clients.add(ctx.channel());
		System.out.println("Server channel active");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = null;
		try {
			buf = (ByteBuf) msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.getBytes(buf.readerIndex(), bytes);
			
			String mm = new String(bytes);
			System.out.println("server child channel handler :" + mm);
			TankServerFrame.getInstance().updateServerMsg(mm);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
	}
}
