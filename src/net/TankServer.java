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
		
		//大管家
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		//服务员
		EventLoopGroup workerGroup = new NioEventLoopGroup(1);
		
		//靴子带
		ServerBootstrap b = new ServerBootstrap();
		
		try {
			//ChannelFuture用来判断connect这件事成功没成功
			ChannelFuture f = b.group(bossGroup, workerGroup) //指定线程池
					.channel(NioServerSocketChannel.class) //指定channel类型
					.childHandler(new ServerlChannelInitializer()) //当channel上有事件来的时候，交给哪个handler处理
					.bind(8888)
					.sync();
			
			System.out.println("Tank server started!");
			TankServerFrame.getInstance().updateServerMsg("敌军还有5秒到达战场，请做好准备！");
			
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
