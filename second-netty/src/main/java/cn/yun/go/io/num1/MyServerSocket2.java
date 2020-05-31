package cn.yun.go.io.num1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


/**
 * 最原始的java提供的socket NIO
 * <p>
 * 1.下面这种我们可以改进，使用一个子线程同时接受和处理IO,
 * 创建死循环来无脑查看是否有socket连接上来，如果有就再无脑循环循环监听IO。注意接收和处理都是阻塞的。
 * 2.介绍java中对NIO的支持, java提供了全新的APi
 * 3.上面这种是一个线程，系统吞吐量不高，现在使用多线程，如果有连接过来就创建一个新的线程处理IO, 就是在accept()
 * 获取一个socket就可以创建线程，监听IO事件，这样会出现什么问题呢，就是连接的socket不能同时一直发送消息，同时
 * 也就几个发送消息，这就造成了线程的浪费，一个连接一个线程，太几把累了，有什么办法让操作系统告诉我那些有连接事件
 * 哪些有IO事件呢，java提供了selector, 叫做选择器也叫多路复用器。
 *
 * @author: Liu Jinyun
 * @date: 2020/5/31/15:54
 */
public class MyServerSocket2 {
    private static List<SocketChannel> list = new LinkedList<>();

    // 模拟接收一个socket即可
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8999));
        serverSocketChannel.configureBlocking(false);

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (socketChannel == null) {
                        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " <==> 暂时没有客户端连进来");
                    } else {
                        list.add(socketChannel);
                        System.out.println("连进来一个新的客户端 端口号:" + socketChannel.socket().getPort());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(() -> {
            while (true) {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {

                    System.out.println("当前socket连接数量:" + list.size());


                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    for (SocketChannel channel : list) {
                        Socket socket = channel.socket();
                        InputStream inputStream = socket.getInputStream();
                        int size;
                        if ((size = inputStream.available()) > 0) {
                            byte[] buffer = new byte[size];
                            inputStream.read(buffer);
                            System.out.println(new String(buffer));
                        }

                        if (channel.read(byteBuffer) > 0) {


                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.limit()];
                            byteBuffer.get(bytes);
                            System.out.println(String.format("端口号为 %d 的客户端发送了一则消息: %s%n", channel.socket().getPort(), new String(bytes)));
                            byteBuffer.clear();
                        }
                    }


                } catch (Exception e) {

                }

            }

        }).start();
    }

}
