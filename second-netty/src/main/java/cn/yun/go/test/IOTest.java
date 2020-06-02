package cn.yun.go.test;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author jinyun liu
 * @date 2020/5/20
 */
public class IOTest {

    @Test
    public void systemTest() {
        String property = System.getProperty("cn.yun.go.service");
    }

    @Test
    public void inputStreamTest() {
        // 1. 模拟一个源数据把它搞进输入流
        String resource = "I'm good!";
        // 2. 所有的数据都是转成了字节数组
        byte[] rBytes = resource.getBytes(StandardCharsets.UTF_8);
        // 3. 把这个输入写入输出流里面发出去 abstract class is the superclass of all classes representing
        // an output stream of bytes
        //OutputStream out = new BufferedOutputStream();




        // 3. 创建一个输入流往程序里面写入数据
        //
        //InputStream in = new BufferedInputStream();
    }
}
