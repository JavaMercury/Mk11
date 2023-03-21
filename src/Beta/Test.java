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
        String username = "aperture";
        File file = new File("User\\" + username);
        File temp = new File("Temp\\" + username);
        decrypt(file, username);
        File tempReversed = new File("Temp\\" + username);
        ReversedLinesFileReader rlf = new ReversedLinesFileReader(tempReversed, StandardCharsets.UTF_8);
        String lastLine = rlf.readLine();
        rlf.close();
        ArrayList<Integer> lineBytes = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile(temp, "rw");
        int totalBytes = 0;
        int b;
        //获取每一行开头的索引
        while ((b = raf.read()) != -1) {
            totalBytes++;
            if (b == 13) {
                lineBytes.add(totalBytes++);
                raf.read();
            }
        }
        //把要修改的数据以及后面的数据保存为临时文件
        raf.seek(lineBytes.get(lineLocation) + 1);
        File tempTemp = new File("Temp\\" + username + "Temp");
        BufferedReader br = new BufferedReader(new FileReader(raf.getFD()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempTemp));
        String line;
        bw.write(content);
        bw.newLine();
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        raf.seek(lineBytes.get(lineLocation - 1) + 1);
        String oldContent = raf.readLine();
        System.out.println(oldContent);
        //raf.write((content + "").getBytes());
        BufferedReader brTemp = new BufferedReader(new FileReader(tempTemp));
        long l = tempTemp.length();
        raf.setLength(raf.length() - l - oldContent.length() + content.length());
        raf.write("\r\n".getBytes());
        while ((line = brTemp.readLine()) != null) {
            if (line.equals(lastLine)) {
                raf.write(line.getBytes());
                break;
            }
            raf.write(line.getBytes());
            raf.write("\r\n".getBytes());
        }
        brTemp.close();
        br.close();
        raf.close();
        encrypt(new File("Temp\\" + username));
        if (!tempTemp.delete()) {
            System.out.println(username + "临时数据删除失败，程序紧急中止！");
            System.exit(-1);
        }
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        saveData(3 + "", 3);
        saveData(4 + "", 4);
        saveData(55555 + "", 5);
        saveData(LocalDateTime.now().toString(), 6);
    }
}