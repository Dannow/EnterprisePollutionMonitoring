package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.webSocket;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;


public class webSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // 指定类初始化日志对象
    private static final Logger log = LoggerFactory.getLogger(WebSocketNettyServer.class);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 获取客户端的IP
        InetSocketAddress insocket = (InetSocketAddress)ctx.channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        log.info("=========客户端:"+ip+"发起连接！！！！==========");


        // 添加到channelGroup 通道组
        NettyConfig.getChannelGroup().add(ctx.channel());

        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息："+msg.text());

        // 获取用户ID,关联channel
        JSONObject webSocketData = JSONObject.parseObject(msg.text());
        String userID = (String) webSocketData.get("userID");
        NettyConfig.getUserChannelMap().put(userID,ctx.channel());

        // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(userID);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close(); //断开连接时，必须关闭，否则造成资源浪费，并发量很大情况下可能造成宕机
        System.out.println("断开的客户端IP:"+clientIp);

        // 删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());

        // 删除用户与channel的对应关系
        removeUserId(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        // 打印异常
        cause.printStackTrace();
        // 删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());
        // 删除用户与channel的对应关系
        removeUserId(ctx);
        // 关闭通道
        ctx.close();
    }

    /**
     * 删除用户与channel的对应关系
     */
    private void removeUserId(ChannelHandlerContext ctx){
        // 获得channel对应的userID
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userID = ctx.channel().attr(key).get();
        // 删除用户与channel的对应关系
        NettyConfig.getUserChannelMap().remove(userID);
    }
}
