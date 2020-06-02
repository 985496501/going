package cn.yun.go.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * simplest netty server demo.
 * 核心: 1. 创建引导类, 2. 指定线程模型, 3. IO模型, 4. 连接读写处理逻辑, 5. 绑定端口
 * 服务端就起来了
 *
 * @author jinyun liu
 * @date 2020/5/20
 */
@Slf4j
public class NettyServer {
    public static void main(String[] args) {

        // create boss thread group to listen the request event of the port.
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // create worker thread group to handle the request.
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // create a server bootstrap to start a convenient server.
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 给服务端的channel设置，最常用的就是 so_backlog: 标识系统用于临时存放已完成3次握手的请求队列的最大长度
                    // 如果连接建立频繁，服务器处创建新连接慢，可以调大这个参数。
//                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // TCP相关的属性设置, 开启TCP的心跳机制, 为每个连接设置配置
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 要求及时性就关闭 true，如果减少发送次数减少网络交互就false
//                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // ChannelHandler childHandler = new ChannelInitializer 处理新连接数据读写的处理逻辑
                    .childHandler(null);

            ChannelFuture f = serverBootstrap.bind(8000).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    private static ServerBootstrap bind(final ServerBootstrap bootstrap, final int port) {
        bootstrap.bind(port).addListener(k -> {
            if (k.isSuccess()) {
                System.out.println("端口绑定成功，端口号为:" + port);
            } else {
                System.out.println("端口绑定失败，端口号为:" + port);
                bind(bootstrap, port + 1);
            }
        });

        return bootstrap;
    }
}

