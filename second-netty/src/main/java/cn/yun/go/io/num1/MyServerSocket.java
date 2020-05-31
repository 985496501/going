package cn.yun.go.io.num1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 最原始的java提供的socket BIO
 *
 * 1.下面这种我们可以改进，使用一个子线程同时接受和处理IO,
 *   创建死循环来无脑查看是否有socket连接上来，如果有就再无脑循环循环监听IO。注意接收和处理都是阻塞的。
 * 2.上面这种是一个线程，系统吞吐量不高，现在使用多线程，如果有连接过来就创建一个新的线程处理IO, 就是在accept()
 *   获取一个socket就可以创建线程，监听IO事件，这样会出现什么问题呢，就是连接的socket不能同时一直发送消息，同时
 *   也就几个发送消息，这就造成了线程的浪费，一个连接一个线程，太几把累了，有什么办法让操作系统告诉我那些有连接事件
 *   哪些有IO事件呢，java提供了selector, 叫做选择器也叫多路复用器。
 *
 * @author: Liu Jinyun
 * @date: 2020/5/31/15:54
 */
public class MyServerSocket {
    // 模拟接收一个socket即可
    public static void main(String[] args) {
        // 创建一个 serverSocket 负责 kernel 通信
        try {
            ServerSocket serverSocket = new ServerSocket(8999);
            System.out.println("server socket startup... port:8999");

            // accept: default blocking. When a client socket is connecting,
            // it calls a event to drive the CPU to handle, and return a new Socket.
            // 默认serverSocket会一直监听指定的端口 有没有socket连接上来 accept是阻塞方法
            // 直到 有一个socket连接上来 然后调用了网卡的中断事件 调用了相应的回调函数 处理了
            // 这次事件 返回了一个封装了 客户端socket的对象
            Socket socket = serverSocket.accept();
            new Thread().run();
            System.out.println("client socket port:" + socket.getPort());

            // 模拟客户端会发送消息 因为不知道什么时候发 这里必须死循环监听消息
            InputStream inputStream = socket.getInputStream();
            while (true) {
                // read() 也是一个阻塞方法
                int read = inputStream.read();
                if (read > 0) {
                    byte[] buffer = new byte[1024];
                    inputStream.read(buffer);
                    System.out.println(new String(buffer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
