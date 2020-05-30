package cn.yun.go.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * @author jinyun liu
 * @date 2020/5/20
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 8000)) {
                while (true) {
                    try {
                        // OutputStream的write(byte[]) --> this.foreach(write(byte[i]))
                        OutputStream outputStream = socket.getOutputStream();
                        // 实际上是调用的 not public SocketOutputStream的write(), socketWrite(byte[0], 0, 1)
                        // native socketWrite0(): Write to the Socket.
                        // 实际上就是一个一个byte的往socket写数据
                        outputStream.write((LocalDateTime.now() + ": 您好").getBytes());
                        // to do nothing.
                        // outputStream.flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
