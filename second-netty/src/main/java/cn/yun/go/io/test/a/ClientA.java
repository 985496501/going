package cn.yun.go.io.test.a;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 简单的客户端
 *
 * @author jinyun liu
 * @date 2020/5/30
 */
public class ClientA {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9998);
        // 1. 往输出流里面写入数据
        OutputStream outputStream = socket.getOutputStream();
        // 2. 说一句话, 获取字节数组
        String message = "hello world!";
        byte[] bytes = message.getBytes(Charset.defaultCharset());
        // 3. bytes -> char
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, Charset.defaultCharset());
        // 4.
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        outputStreamWriter.write("hello world!");
        outputStreamWriter.flush();
    }
}
