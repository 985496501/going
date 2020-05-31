package cn.yun.go.io.num1;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import	java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/31/16:14
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8999);
            OutputStream stream = socket.getOutputStream();


            stream.write("hello world".getBytes());

            TimeUnit.SECONDS.sleep(2);

            stream.write("你好啊, 服务端。".getBytes());

            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
