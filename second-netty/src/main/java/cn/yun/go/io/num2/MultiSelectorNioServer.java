package cn.yun.go.io.num2;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Base64;

/**
 * boss和worker分开
 * boss: 负责accept()
 * worker: to do IO ops.
 *
 *
 * @author: Liu Jinyun
 * @date: 2020/5/31/19:48
 */
public class MultiSelectorNioServer {

    private ServerSocketChannel server;

    private Selector boss;

    private Selector worker1;

    private Selector worker2;

    private final int port = 8999;

    private void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            boss = Selector.open();
            worker1 = Selector.open();
            worker2 = Selector.open();

            server.register(boss, SelectionKey.OP_ACCEPT);
            System.out.println("server startup");
        } catch (Exception e) {

        }
    }



    public void start0() {
        initServer();







    }
}
