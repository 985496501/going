package cn.yun.go.io;

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

        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
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
        }).start();
    }




}

