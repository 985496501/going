package cn.yun.go.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author jinyun liu
 * @date 2020/6/2
 */
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .connect(new InetSocketAddress(8999));

        // 添加的是一个通用的回调监听器
        channelFuture.addListener( k -> {
            System.out.println("连接端口号为: 8999的netty server 已经有了响应");
        });
    }
}
