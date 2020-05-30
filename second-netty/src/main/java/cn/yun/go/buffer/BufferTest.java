package cn.yun.go.buffer;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.sql.SQLOutput;

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
        byteBuffer.put("刘津运".getBytes());

        // 标识位：默认是-1, 它一般不会指定, 它用来表示reset()调用的时候
        // 将position = marker.
        Buffer mark = byteBuffer.mark();
        System.out.println("marker:" + mark);

        // the index of next element written or read.
        // position就是数组的一个游标，它绝对不会大于limit
        int position = byteBuffer.position();
        System.out.println("position:" + position);

        // the index of the first element not to be written or read.
        // 第一个不能读写的索引
        int limit = byteBuffer.limit();
        System.out.println("limit:" + limit);

        // array size, never change
        // 数组的大小
        int capacity = byteBuffer.capacity();
        System.out.println("capacity:" + capacity);


        boolean readOnly = byteBuffer.isReadOnly();
        System.out.println("readOnly:" + readOnly);



        // ---------------上面创建一个写，下面是要读。-------------------------

        // byte[position] and position ++
//        byte b = byteBuffer.get();
//        System.out.println(b);

        // read: length <= limit - position. len <= 16-9=7
        // 根据上面的公司, limit不变，我们把position还原就成0
        //
        byte[] buffer = new byte[byteBuffer.position()];
        // 下面不符合上面的公式不允许读到写入的数据。
//        byteBuffer.get(buffer);
//        System.out.println("read1:" + new String(buffer));

        // clear: position=0 limit=capacity, 双指针重新定位了，但是数组里面的数据可是没有清理的
        // new read: clear()
        byteBuffer.flip();
        byteBuffer.get(buffer);
        System.out.println("read2:" + new String(buffer));


        // new write: clear()
        byteBuffer.clear();// p=9,
        // 0, 3  --> 3 <= 16-0
        byteBuffer.put("王".getBytes());//p=3,
        byteBuffer.rewind();
        byteBuffer.get(buffer);

        System.out.println("read3:" + new String(buffer));

        System.out.println(byteBuffer.position());
//        byte b = byteBuffer.get();
//        System.out.println(b);
//        byte[] as = new byte[byteBuffer.limit()];
//        byteBuffer.get(as);
//        String s = new String(as);
//        System.out.println(s);
    }
}
