package Beta;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test {
    ///使用异或运算进行解密
    public static void decrypt(File src, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("Temp\\" + fileName);
        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 114514);
        fos.close();
        fis.close();
    }

    ///使用异或运算进行加密
    public static void encrypt(File src) throws IOException {
        String username = "aperture";
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("User\\" + username);
        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 114514);
        fos.close();
        fis.close();
    }

    static void saveData(String content, int lineLocation) throws IOException {
    }

    public static void main(String[] args) throws IOException {
        saveData(3 + "", 3);
        saveData(4 + "", 4);
        saveData(55555 + "", 5);
        saveData(LocalDateTime.now().toString(), 6);
    }
}