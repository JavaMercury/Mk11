import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public abstract class Initializer extends JFrame implements KeyListener, MouseListener {
    Container con = getContentPane();
    //数字字符-48为实际数字，大写字母-55

    //创建集合，存储用户信息
    static HashSet<User> library = new HashSet<>();
    //默认的上次签到时间，用于用户第一次签到
    static LocalDateTime lastLDT = LocalDateTime.of(2020, 3, 24, 0, 0, 0);

    //测试用账号
    static {
        library.add(new User("qwerqwer", "12341234Aa", "12312341234", 0, 1, 0, lastLDT, LocalDateTime.of(2022, 9, 16, 0, 0, 0), 980));
    }

    JMenu aboutJM = new JMenu("关于(G)");
    String version = "水银第11代 0.11.07.20230221";
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

    ///判断重设的手机号码是否被占用
    public static boolean checkPhoneNumberUsed(HashSet<User> library, String phoneNumber) {
        boolean flag = false;
        for (User user : library) {
            flag = user.getPhoneNumber().equals(phoneNumber);
            if (flag) break;
        }
        return flag;
    }

    ///判断手机号码是否重复
    public static boolean checkSamePhoneNumber(String username, String phoneNumber) {
        User user = getUser(username);
        return !phoneNumber.equals(user.getPhoneNumber());
    }

    ///检验密码
    public static boolean checkPassword(String password) {
        return password.matches("\\S*(?=\\S{8,20})(?=\\S*[0-9])(?=\\S*[a-z])(?=\\S*[A-Z])\\S*");
    }

    ///获取对象
    public static User getUser(String username) {
        Iterator<User> it = library.iterator();
        User user = null;
        while (it.hasNext()) {
            user = it.next();
            if (user.getUsername().equals(username)) break;
        }
        return user;
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

    ///判断密码是否重复
    public static boolean checkSamePassword(String username, String password) {
        User user = getUser(username);
        return user.getPassword().equals(password);
    }

    ///判断手机号码是否合法
    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("1\\d{10}");
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

    abstract void initContent();

}
