package cn.yun.go.test.embeded;

import cn.yun.go.decoder.FixedLengthFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * 使用EmbeddedChannel来测试你的handler
 * channel的创建不太容易
 *
 * @author jinyun liu
 * @date 2020/6/3
 */
public class EmbeddedChannelTest {

    @Test
    public void fixedLengthFrameDecoderTest() {
        // 256
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        ByteBuf duplicate = buffer.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(8));
        ByteBuf retain = duplicate.retain();
        Assert.assertTrue(channel.writeInbound(retain));
    }

}
