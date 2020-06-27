package cn.yun.go.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.logging.SocketHandler;

/**
 * @author jinyun liu
 * @date 2020/6/3
 */
public class MyExceptionHandler extends SimpleChannelInboundHandler<SocketHandler> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketHandler msg) {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ChannelFuture channel = ctx.close();
        channel.addListener(k -> {
            if (k.isSuccess()) {
                InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
                int port = socketAddress.getPort();
                System.out.printf("端口号为:%d的客户端主机中断了连接！%n 服务端成功关闭了socket通道", port);
            }
        });
    }
}
