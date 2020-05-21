package cn.yun.go.io;

import java.io.IOException;
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
                        socket.getOutputStream().write((LocalDateTime.now() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
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
