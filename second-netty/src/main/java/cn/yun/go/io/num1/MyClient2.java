package cn.yun.go.io.num1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/31/16:14
 */
public class MyClient2 {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.bind(new InetSocketAddress(9000));
        channel.connect(new InetSocketAddress(8999));
        ByteBuffer buffer = ByteBuffer.allocate(128);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            String s = "hello, I'm a client,  " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            buffer.put(s.getBytes());
            buffer.flip();
            // write from position to limit
            channel.write(buffer);
            buffer.clear();
        }
    }
}
