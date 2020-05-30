package cn.yun.go.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用原生的NIOServer写法。
 * 仅作参考。
 * 朴素的单线程Reactor模型
 * java的NIO模式的Selector网络通讯，就是一个简单的 Reactor模型。
 *
 *
 * @author jinyun liu
 * @date 2020/5/20
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //WindowsSelectorImpl

        // 1.获取Selector选择器
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        // 单线程的Reactor模型。
        new Thread(()->{
            try {
                //ServerSocketChannelImpl
                // 2.获取通道
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                // 3.设为非阻塞
                listenerChannel.configureBlocking(false);
                // 4.绑定连接
                listenerChannel.socket().bind(new InetSocketAddress(8000));

                // 5.将通道注册到选择器上，并注册操作为：”接收“ 操作， 把serverChannel注册到serverSelector上，返回一个id(token：令牌，一个映射的标识)
                SelectionKey register = listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为1ms
                    // Selects a set of keys whose corresponding channels are ready for I/O operations.
                    // 如果有需要进行IO操作的channel就计算出数量返回, 理论上是可以阻塞的，就是没有查询出来需要IO操作的channel阻塞1毫秒，等待有
                    // 如果真没有就返回 0
                    if (serverSelector.select(1) > 0) {
                        // 6.获取当前选择器上所有注册过的选择键
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            // 7.一个选择键
                            SelectionKey key = keyIterator.next();
                            // whether this key's channel is ready to accept a new socket connection.
                            if (key.isAcceptable()) {
                                try {
                                    // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    // 把连接这个serverChannel的channel注册到客户端选择器上。
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }

                        }
                    }
                }
            } catch (IOException ignored) {

            }

        }).start();


        new Thread(() -> {
        try {
            while (true) {
                // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
                if (clientSelector.select(1) > 0) {
                    Set<SelectionKey> set = clientSelector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        // Tests whether this key's channel is ready for reading.
                        if (key.isReadable()) {
                            try {
                                SocketChannel clientChannel = (SocketChannel) key.channel();

                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                // (3) 读取数据以块为单位批量读取
                                clientChannel.read(byteBuffer);
                                byteBuffer.flip();

                                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
                                        .toString());
                            } finally {
                                keyIterator.remove();
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }

                    }
                }
            }
        } catch (IOException ignored) {
        }
    }).start();

    }

}
