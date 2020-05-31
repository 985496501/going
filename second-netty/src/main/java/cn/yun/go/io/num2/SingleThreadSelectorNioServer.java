package cn.yun.go.io.num2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


/**
 * 第一种模型： boss和worker 都注册同一个selector.
 * 单线程的reactor模型
 *
 *
 * @author: Liu Jinyun
 * @date: 2020/5/31/16:24
 */
public class SingleThreadSelectorNioServer {
    private Selector selector;

    private ServerSocketChannel server;

    private final int port = 8999;


    private void init() {
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动了......");
        } catch (Exception e) {

        }
    }

    public void start0() {
        init();

        try {
            while (true) {
                while (selector.select(0) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()){
                            readHandler(key);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void readHandler(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();

            buffer.clear();
            while (true) {
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    buffer.clear();
                    System.out.println("端口号为:" + channel.socket().getPort() + ", 发送了消息: " + new String(bytes));
                } else if (read == 0) {
                    break;
                } else {
                    channel.close();
                }
            }

        } catch (Exception e) {

        }
    }

    private void acceptHandler(SelectionKey selectionKey) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("有一个新的客户端连接上来了, 端口号: " + channel.socket().getPort());
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        SingleThreadSelectorNioServer singleThreadSelectorNioServer = new SingleThreadSelectorNioServer();
        singleThreadSelectorNioServer.start0();
    }

}
