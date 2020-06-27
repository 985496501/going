package cn.yun.go.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 固定长度帧解码器
 *
 * @author jinyun liu
 * @date 2020/6/3
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        if (frameLength <= 0) {
            throw new IllegalArgumentException("解析帧的长度至少为1");
        }

        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // readable: 返回3段中的一段，就是写索引-读索引。
        while (in.readableBytes() >= frameLength) {
            // 返回一个新的ByteBuf[0, frameLength - 1], 这个buf的读，会read_index + frameLength.
            ByteBuf byteBuf = in.readBytes(frameLength);
            out.add(byteBuf);
        }
    }
}
