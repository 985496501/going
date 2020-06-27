package cn.yun.go.test.handler;

import io.netty.channel.*;
import org.junit.Test;

/**
 * @author jinyun liu
 * @date 2020/6/3
 */
public class ChannelHandlerTest {

    @Test
    public void addTest() {
        ChannelHandler channelHandler = new ChannelHandler() {
            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                System.out.println("只要我被添加就回调我");
            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                System.out.println("只要我被移除了就回调我");
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

            }
        };

       // ChannelPipeline channelPipeline = new DefaultChannelPipeline();
    }
}
