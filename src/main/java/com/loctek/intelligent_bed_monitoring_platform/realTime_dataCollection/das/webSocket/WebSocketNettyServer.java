package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketNettyServer implements Runnable{
    @Override
    public void run() {
        // 创建一个主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建一个工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 链式编程
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketServerChannelInitializer());

            // 绑定端口,开始接收进来的连接
            ChannelFuture channelFuture = bootstrap.bind(7001).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e){
            // 打印错误
            e.printStackTrace();

        }finally {
            // 关闭主线程组
            bossGroup.shutdownGracefully();
            // 关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }
}
