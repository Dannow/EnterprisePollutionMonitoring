package com.loctek.intelligent_bed_monitoring_platform;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressServer;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.SpringContextUtil;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.webSocket.WebSocketNettyServer;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan({"com.loctek.intelligent_bed_monitoring_platform.permission_management.dao"})
public class IntelligentBedMonitoringPlatformApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentBedMonitoringPlatformApplication.class, args);
    }

    /**
     * 使用异步注解方式启动netty服务端服务
     */
    @Async
    public void run(String... args) throws Exception {

        // 启动Netty服务端，绑定端口
        IntelligentMattressServer NettyServer = new IntelligentMattressServer(9998);
        // 启动WebSocket服务端
        WebSocketNettyServer webSocketNettyServer = new WebSocketNettyServer();

        // 创建线程
        Thread NettyServerThread = new Thread(NettyServer);
        Thread webSocketNettyServerThread = new Thread(webSocketNettyServer);

        // 开启线程
        try
        {
            NettyServerThread.start();
            webSocketNettyServerThread.start();
        }
        catch (Exception e)
        {

            NettyServer.connectCountReduce();
            throw new RuntimeException(e);
        }
    }
}
