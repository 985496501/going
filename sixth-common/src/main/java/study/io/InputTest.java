package study.io;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 输入流学习
 *
 * @author jinyun liu
 * @date 2020/5/30
 */
public class InputTest {

    @Test
    public void ReaderTest() throws IOException {
        // 读取resources下面的文件, 读取成流文件, 先把文件转成了字节输入流
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/1.txt");
        // 把字节输入流inputStream ->字符输入流 InputStreamReader extends Reader.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        // 默认读取一行
//        String s = bufferedReader.readLine();
//        System.out.println(s);
        // 读取所有行
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
        // 所有的数据读完就是把流管道数据拿出来。
    }


    /**
     * 字节转换
     * 把汉字, 英文标准符号 转成UTF-8的对应的数字。
     */
    @Test
    public void byteArrayTest() {
        String str = ".";
        // str = "hello world";
        // "1": 49
        // "a": 97
        // "A": 97-32=65
        // UTF-8, 一个字符一个字节。
        // "你好": -28 -67 -96 -27 -91 -67, 一个汉字对应3个字节UTF-8字符集
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
    }




    @Test
    public void bufferTest() {
        // A container for data of a specific primitive type. abstract class.
        // capacity, limit, position.
        // Buffer buffer = new Buffer();
    }














}
