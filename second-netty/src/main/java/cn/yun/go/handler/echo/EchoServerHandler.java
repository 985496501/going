package cn.yun.go.handler.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * Echo服务器会响应传入的信息，所以我们定义一个处理传入消息的处理器
 *
 * @author jinyun liu
 * @date 2020/6/3
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 使用这个方法需要显式的清空资源
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf: equivalent to the ByteBuffer in JDK.
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received: " + in.toString(CharsetUtil.UTF_8));
        // 直接写回客户端，不刷出站
        ctx.write(in);
        // 释放，这个是必须的释放，具体可以采用可以查看工具进行查看细节。
        ReferenceCountUtil.release(msg);
        // simpleChannelInboundHandlerAdapter 这个方法已经写好了资源的释放，只需关注
        // read0(), 关系自己业务的读出即可。
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                // future.channel.close();
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
