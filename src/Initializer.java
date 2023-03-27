import org.apache.commons.io.input.ReversedLinesFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class Initializer extends JFrame implements KeyListener, MouseListener {
    //数字字符-48为实际数字，大写字母-55

    //创建集合，存储用户信息
    static HashSet<User> library = new HashSet<>();
    //默认的上次签到时间，用于用户第一次签到
    static LocalDateTime lastLDT = LocalDateTime.of(2020, 3, 24, 0, 0, 0);

    //所有子类图形的统一getContentPane方法
    Container con = getContentPane();
    JMenu aboutJM = new JMenu("关于(G)");
    String version = "水银第11代 0.11.16.20230327";
    String username;
    String password;
    JDialog aboutJD = new JDialog();
    Runtime rt = Runtime.getRuntime();
    JButton backJB = new JButton("返回");
    JLabel aboutJL = new JLabel("<html>水银的开发开始于2022年9月16日， <br />起源于初中作者自学的VBS语言，<br />" +
            "当时作者用此语言和同学制作了一个简陋的\"悦豪客户端\"，<br />而水银继承了这个传统。<br />当前版本：" + version + "<br />作者：邹上豪<br />感谢所有测试员！" +
            "<br /><br />当前系统运行环境对象：" + rt + "<br />" +
            "当前计算机CPU线程数：" + rt.availableProcessors() + "<br />" +
            "当前虚拟机能获取的总内存大小：" + rt.maxMemory() / 1024 / 1024 + " Mb<br />" +
            "当前虚拟机已获取的内存大小：" + rt.totalMemory() / 1024 / 1024 + " Mb<br />" +
            "当前虚拟机剩余内存大小：" + rt.freeMemory() / 1024 / 1024 + " Mb<br />" +
            "当前虚拟机已使用的内存大小：" + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " Mb");
    JPasswordField passwordJPF = new JPasswordField();
    StringBuilder passwordSB = new StringBuilder();
    JLabel revealPasswordJL = new JLabel(new ImageIcon(new ImageIcon("image\\login\\密码隐藏.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
    JLabel revealPasswordPressedJL = new JLabel(new ImageIcon(new ImageIcon("image\\login\\密码显示.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

    ///检验整数
    public static boolean notInteger(String str) {
        return !str.matches("\\d+");
    }

    ///检验密码
    public static boolean checkPassword(String password) {
        return password.matches("\\S*(?=\\S{8,20})(?=\\S*[0-9])(?=\\S*[a-z])(?=\\S*[A-Z])\\S*");
    }

    ///生成验证码
    public static String getVerificationCode() {
        Random r = new Random();
        ArrayList<Integer> numberList = new ArrayList<>();
        ArrayList<Character> letterList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            numberList.add(index);
        }
        for (int index = 0; index < 26; index++) {
            letterList.add((char) ('a' + index));
            letterList.add((char) ('A' + index));
        }
        int letterLength = r.nextInt(6);
        int numberLength = 5 - letterLength;
        StringBuilder sequence = new StringBuilder();
        for (int index = 0; index < letterLength; index++) {
            sequence.append(letterList.get(r.nextInt(52)));
        }
        for (int index = 0; index < numberLength; index++) {
            sequence.append(numberList.get(r.nextInt(10)));
        }
        char[] seqArray = sequence.toString().toCharArray();
        int randomIndex;
        char temp;
        for (int index = 0; index < seqArray.length; index++) {
            randomIndex = r.nextInt(seqArray.length);
            temp = seqArray[index];
            seqArray[index] = seqArray[randomIndex];
            seqArray[randomIndex] = temp;
        }
        StringBuilder result = new StringBuilder();
        for (char c : seqArray) {
            result.append(c);
        }
        return result.toString();
    }

    ///判断手机号码是否合法
    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("1\\d{10}");
    }

    ///判断手机号码是否重复
    public boolean checkSamePhoneNumber(String username, String phoneNumber) throws IOException {
        File file = new File("User\\" + username);
        decrypt(file, username);
        File temp = new File("Temp\\" + username);
        BufferedReader br = new BufferedReader(new FileReader(temp));
        br.readLine();
        br.readLine();
        boolean result = phoneNumber.equals(br.readLine());
        br.close();
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！Initializer-checkSamePhoneNumber");
            System.exit(-1);
        }
        return result;
    }

    ///判断密码是否重复
    public boolean checkSamePassword(String username, String password) throws IOException {
        File file = new File("User\\" + username);
        decrypt(file, username);
        File temp = new File("Temp\\" + username);
        BufferedReader br = new BufferedReader(new FileReader(temp));
        br.readLine();
        boolean result = password.equals(br.readLine());
        br.close();
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！Initializer-checkSamePassword");
            System.exit(-1);
        }
        return result;
    }

    void saveData(String content, int lineLocation) throws IOException {
        File file = new File("User\\" + username);
        File temp = new File("Temp\\" + username);
        decrypt(file, username);
        File tempReversed = new File("Temp\\" + username);
        ReversedLinesFileReader rlf = new ReversedLinesFileReader(tempReversed, StandardCharsets.UTF_8);
        String lastLine = rlf.readLine();
        RandomAccessFile raf = new RandomAccessFile(temp, "rw");
        ArrayList<Integer> lineBytes = new ArrayList<>();
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
        System.out.println("array length: " + lineBytes.size());
        //lineLocation等于8说明此时要保存的是拼图小游戏的最佳记录，这时使用常规保存方法是会报错的，因为这是最后一行
        if (lineLocation == 8) {
            raf.setLength(raf.length() - lastLine.length());
            System.out.println("length after delete: " + raf.length());
            raf.seek(lineBytes.get(lineLocation - 1) + 1);
            raf.write("-".getBytes());
            raf.write(content.getBytes());
            System.out.println("length after write: " + raf.length());
            raf.close();
            rlf.close();
            encrypt(new File("Temp\\" + username));
            if (!temp.delete()) {
                System.out.println(username + "数据删除失败，程序紧急中止！Initializer-saveData-1");
                System.exit(-1);
            }
            return;
        }
        rlf.close();
        String line;
        //把要修改的数据以及后面的数据保存为临时文件
        raf.seek(lineBytes.get(lineLocation) + 1);
        File tempTemp = new File("Temp\\" + username + "Temp");
        BufferedReader br = new BufferedReader(new FileReader(raf.getFD()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempTemp));
        bw.write(content);
        bw.newLine();
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        raf.seek(lineBytes.get(lineLocation - 1) + 1);
        String oldContent = raf.readLine();
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
            System.out.println(username + "临时数据删除失败，程序紧急中止！Initializer-saveData-2");
            System.exit(-1);
        }
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！Initializer-saveData-3");
            System.exit(-1);
        }
    }

    ///判断重设的手机号码是否被占用
    public boolean checkPhoneNumberUsed(String phoneNumber, String username) throws IOException {
        File[] files = new File("User").listFiles();
        assert files != null;
        if (files.length == 0) return false;
        BufferedReader br = null;
        File temp = null;
        for (File file : files) {
            temp = new File("Temp\\" + file.getName());
            decrypt(file, file.getName());
            br = new BufferedReader(new FileReader(temp));
            br.readLine();
            br.readLine();
            if (phoneNumber.equals(br.readLine())) {
                br.close();
                if (!temp.delete()) {
                    System.out.println(username + "数据删除失败，程序紧急中止！");
                    System.exit(-1);
                }
                return true;
            }
        }
        br.close();
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！");
            System.exit(-1);
        }
        return false;
    }

    ///使用异或运算进行加密
    public void encrypt(File src) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("User\\" + username);
        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 114514);
        fos.close();
        fis.close();
    }

    ///使用异或运算进行解密
    public void decrypt(File src, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("Temp\\" + fileName);
        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 114514);
        fos.close();
        fis.close();
    }

    abstract void collectData();

    ///显示密码
    void showPassword() {
        passwordJPF.setEchoChar((char) 0);
    }

    ///隐藏密码
    void hidePassword() {
        passwordJPF.setEchoChar('•');
    }

    ///显示关于界面
    void showAbout() {
        aboutJD.getContentPane().setBackground(Color.WHITE);
        aboutJD.setResizable(false);
        aboutJD.setTitle("关于");
        aboutJD.setIconImage(new ImageIcon("image\\login\\MkLogInBackground.jpg").getImage());
        aboutJD.getContentPane().add(aboutJL);
        aboutJD.setSize(400, 300);
        aboutJD.setLocationRelativeTo(null);
        aboutJD.setAlwaysOnTop(true);
        aboutJD.setVisible(true);
    }

    ///统一窗口左上角图标
    void setIcon() {
        setIconImage(new ImageIcon("image\\login\\MkLogInBackground.jpg").getImage());
    }

    abstract void initJFrame();

    ///菜单初始化
    abstract void initMenuBar();

    abstract void initContent() throws IOException;

}
