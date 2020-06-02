package cn.yun.go;

import cn.yun.go.channel.WebsocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * websocket 的服务器
 *
 * @author jinyun liu
 * @date 2020/6/2
 */
public class WebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();

        server.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebsocketChannelInitializer());

        ChannelFuture channelFuture = server.bind(8999).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
