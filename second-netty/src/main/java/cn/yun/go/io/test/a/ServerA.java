package cn.yun.go.io.test.a;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * 简单的服务器
 *
 * @author jinyun liu
 * @date 2020/5/30
 */
@Slf4j
public class ServerA {
    public static final int PORT = 9998;
    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            log.info("server is running, port: {}", PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                // 处理socket
                handle(socket);
            }
        } catch (IOException e) {
            log.error("server is gg");
        }
    }


    public static void handle(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            // byte -> char
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            // default = 8KB
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = bufferedReader.readLine();
            System.out.println(s);
            // blocking method.
//            int read = inputStream.read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
