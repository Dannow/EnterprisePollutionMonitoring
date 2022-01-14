package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntelligentMattressServer implements Runnable{
	private static String classname = IntelligentMattressServer.class.getSimpleName();
	private final int port;
	private static int connectCount = 0;

	public IntelligentMattressServer(int port) {
		this.port = port;
	}

	public void connectCountReduce() {
		// log.debug("---------connectCountReduce-------");

		connectCount--;
	}

	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.option(ChannelOption.SO_BACKLOG, 1024);
			sb.group(group, bossGroup) // 绑定线程池
					.channel(NioServerSocketChannel.class) // 指定使用的channel
					.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
					.localAddress(this.port)// 绑定监听端口
					.childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							connectCount++;

							log.error("connectCount = "+ connectCount+ "!!!");
							// log.debug("---------connectCount="+connectCount+"-------");
//                            log.debug("报告");
//                            log.debug("信息：有一客户端链接到本服务端");
//                            log.debug("IP:" + ch.localAddress().getHostName());
//                            log.debug("Port:" + ch.localAddress().getPort());
//                            log.debug("报告完毕");
							log.error(" == 信息：有一客户端链接到本服务端:" + ch.localAddress().getHostName() + ":"
									+ ch.localAddress().getPort() + " =================================");

							ch.pipeline().addLast(new PackageDecoder());// 解码器
							ch.pipeline().addLast(new IntelligentMattressServerHandler()); // 客户端触发操作
							ch.pipeline().addLast(new ByteArrayEncoder());// 编码器
						}

					});
			ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定

			log.debug(classname," *******************************************************************************");
			log.debug(classname,IntelligentMattressServer.class + " 启动正在监听： " + cf.channel().localAddress());
			cf.channel().closeFuture().sync(); // 关闭服务器通道

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully(); // 释放线程池资源
			bossGroup.shutdownGracefully();
			log.error("release!!!");
			connectCount--;
			log.error("connectCount = "+ connectCount+ "!!!");
		}
	}


}
