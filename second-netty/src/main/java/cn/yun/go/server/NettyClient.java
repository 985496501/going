package cn.yun.go.server;

import cn.yun.go.channel.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jinyun liu
 * @date 2020/5/20
 */
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999);
            channelFuture.addListener(k -> {
                if (k.isSuccess()) {
                    System.out.println("连接端口号为: 8999的netty server 已经成功！");
                }

                if (k.isDone()) {
                    System.out.println("连接端口号为: 8999的netty server 已经结束！");
                }

                if (k.isCancellable()) {
                    System.out.println("连接端口号为: 8999的netty server 是可以取消的！");
                }

                if (k.isCancelled()) {
                    System.out.println("连接端口号为: 8999的netty server 已经取消！");
                }
            });

            Channel channel = channelFuture.channel();

//            while (true) {
                // channel write and flush right now.
                channel.writeAndFlush(getLocalTime() + ": hello world!");
                Thread.sleep(2000);
//            }
            channel.close().sync();
        } catch (Exception e) {
            group.shutdownGracefully();
        }

    }


    private static String getLocalTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}


