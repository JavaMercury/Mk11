package Beta;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        String username = "aperture";
        int point = 1;
        File file = new File("User\\" + username);
        decrypt(file, username);
        ArrayList<Integer> lineBytes = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile("Temp\\" + username, "rw");
        int totalBytes = 0;
        int b;
        while ((b = raf.read()) != -1) {
            totalBytes++;
            if (b == 13) {
                lineBytes.add(totalBytes++);
                raf.read();
            }
        }
        raf.seek(lineBytes.get(3) + 1);
        File tempTemp = new File("Temp\\" + username + "Temp");
        BufferedReader br = new BufferedReader(new FileReader(raf.getFD()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempTemp));
        String line;
        bw.write(point + "\r\n");
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        raf.seek(lineBytes.get(2));
        raf.write((point + "").getBytes());
        BufferedReader brTemp = new BufferedReader(new FileReader(tempTemp));
        long l = tempTemp.length();
        raf.setLength(raf.length() - l);
        raf.write("\r\n".getBytes());
        while ((line = brTemp.readLine()) != null) {
            raf.write(line.getBytes());
            raf.write("\r\n".getBytes());
        }
        brTemp.close();
        bw.close();
        br.close();
        raf.close();
        encrypt(new File("Temp\\" + username));
    }
}
