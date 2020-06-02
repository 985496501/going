package cn.yun.go.handler.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author jinyun liu
 * @date 2020/6/2
 */
public class WebsocketHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        ctx.channel().writeAndFlush(msg);
    }
}
