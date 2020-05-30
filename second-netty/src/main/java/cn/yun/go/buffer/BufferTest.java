package cn.yun.go.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * buffer test
 *
 * @author jinyun liu
 * @date 2020/5/30
 */
public class BufferTest {
    @Test
    public void byteBufferTest() {
        // super(-1, 0, lim, cap, new byte[cap], 0);
        // --> new HeapByteBuffer(16, 16)
        // mark: -1, position: 0, limit: 16,
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
    }
}
