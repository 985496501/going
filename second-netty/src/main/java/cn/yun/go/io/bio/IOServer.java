package cn.yun.go.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jinyun liu
 * @date 2020/5/20
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(8000);
//        // (1) 接收新连接线程, It must create the thread pool manually.
//        ThreadPoolExecutor boss = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(200), new ThreadFactoryBuilder().setNameFormat("boss-thread-factory").build());
//
//        ThreadPoolExecutor worker = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(50), new ThreadFactoryBuilder().setNameFormat("worker-thread-factory").build());
//
//        boss.execute(() -> {
//            while (true) {
//                // (1) 阻塞方法  获取新的连接
//                try (Socket socket = serverSocket.accept()) {
//                    // (2) 每一个新的连接都创建一个线程，  负责读取数据
//                    worker.execute(() -> {
//                        byte[] data = new byte[1024];
//                        try (InputStream inputStream = socket.getInputStream()) {
//                            while (true) {
//                                int len;
//                                // (3) 按字节流方式读取数据
//                                while ((len = inputStream.read(data)) != -1) {
//                                    System.out.println(new String(data, 0, len));
//                                }
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//                } catch (IOException e) {
//
//                }
//            }
//        });



        ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程 创建一个子线程来接收线程 保护了主线程，这里使用主线程也是一样的
//        new Thread(() -> {
            // 死循环 CPU无脑扫描
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接，使用的是操作系统级别的阻塞, 不消耗CPU的资源
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // Once a socket connects to the server, there will create a new thread and a death-loop within it.
                            // Death-loop is always watching the socket IO event, even thought there is no incoming IO event.
                            // Problem: thread-tune, death-loop are exhausting the CPU resources.
                            // How to solve?
                            while (true) {
                                int len;
                                // (3) 按字节流方式读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (IOException e) {
                        }
                    }).start();

                } catch (IOException e) {
                }

            }
//        }).start();
    }




}

