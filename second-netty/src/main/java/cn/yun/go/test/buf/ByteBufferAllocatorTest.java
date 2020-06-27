package cn.yun.go.test.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.Test;

/**
 * @author jinyun liu
 * @date 2020/6/3
 */
public class ByteBufferAllocatorTest {
    @Test
    public void allocTest() {

        // AbstractByteBufAllocator(false) default heap buffer.
        // 下面非池化的缓冲区分配器是 (true), 直接分配内存缓冲区 ByteBufAllocator 最简单的不使用池化
        UnpooledByteBufAllocator aDefault = UnpooledByteBufAllocator.DEFAULT;
        System.out.printf("UnpooledByteBufAllocator.DEFAULT 是不是直接缓冲池化的：%s%n", aDefault.isDirectBufferPooled());

        // 256.
        ByteBuf buffer = aDefault.buffer();

        System.out.println("buffer() 的 capacity: " + buffer.capacity());
        System.out.println("buffer() 的 max capacity: " + buffer.maxCapacity());
    }
}
