package cn.yun.go.channel;

import cn.yun.go.handler.MyExceptionHandler;
import cn.yun.go.handler.PrintHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @author jinyun liu
 * @date 2020/6/2
 */
public class ClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new PrintHandler());
        ch.pipeline().addLast(new MyExceptionHandler());
    }
}
