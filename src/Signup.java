import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;

//注册
public class Signup extends Initializer{

    JMenu propertiesJM = new JMenu("选项");
    JMenuItem exitJMI = new JMenuItem("中止注册");
    JTextField usernameJTF = new JTextField();
    JPasswordField passwordAgainJPF = new JPasswordField();
    JTextField phoneNumberJTF = new JTextField();
    JButton submitJB = new JButton("确定");
    JTextField codeJTF = new JTextField();
    JButton codeJB = new JButton();
    String passwordAgain;
    String phoneNumber;
    String code;
    String codeTemp;
    JLabel invalidCodeJL = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidUsernameJL = new JLabel("用户名无效，用户名长度在5-15之间，只能使用字母、数字及下划线，且不能为纯数字");
    JLabel occupiedUsernameJL = new JLabel("用户名已被占用");
    JLabel validUsernameJL = new JLabel("用户名可用");
    JLabel invalidPasswordJL = new JLabel("密码无效，密码长度在8-20之间，必须包括大小写字母和数字");
    JLabel differentPasswordJL = new JLabel("两次输入的密码不一致");
    JLabel validPasswordJL = new JLabel("密码可用");
    JLabel inputPasswordJL = new JLabel("输入密码");
    JLabel inputPasswordAgainJL = new JLabel("再次输入密码");
    JLabel invalidPhoneNumberJL = new JLabel("手机号码无效");
    JLabel occupiedPhoneNumberJL = new JLabel("手机号已被占用");
    JLabel inputUsernameJL = new JLabel("输入用户名");
    JLabel inputPhoneNumberJL = new JLabel("输入大陆手机号码");

    public Signup() {
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///判断注册时用户名是否被占用
    public static boolean checkUsernameUsed(HashSet<User> library, String username) {
        boolean flag = false;
        for (User user : library) {
            flag = user.getUsername().equals(username);
            if (flag) break;
        }
        return flag;
    }

    ///判断用户名是否合法
    public static boolean checkUsername(String username) {
        return username.matches("(?!\\d+$)\\w{5,15}");
    }

    ///窗口初始化
    @Override
    void initJFrame() {
        setLayout(null);
        setSize(640, 648);
        setTitle("水银第十代 注册");
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    ///菜单初始化
    @Override
    void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        propertiesJM.add(exitJMI);
        menuBar.add(propertiesJM);
        menuBar.add(aboutJM);
        aboutJM.addMouseListener(this);
        exitJMI.addMouseListener(this);
        setJMenuBar(menuBar);
    }

    ///内容初始化
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        usernameJTF.setBounds(100, 50, 200, 30);
        passwordJPF.setBounds(100, 130, 200, 30);
        passwordAgainJPF.setBounds(100, 210, 200, 30);
        phoneNumberJTF.setBounds(100, 290, 200, 30);
        codeJTF.setBounds(100, 370, 100, 30);
        codeJB.setBounds(200, 370, 80, 30);
        invalidCodeJL.setBounds(100, 330, 400, 30);
        invalidUsernameJL.setBounds(100, 10, 500, 30);
        occupiedUsernameJL.setBounds(100, 10, 400, 30);
        validUsernameJL.setBounds(100, 10, 400, 30);
        invalidPasswordJL.setBounds(100, 90, 400, 30);
        validPasswordJL.setBounds(100, 90, 400, 30);
        differentPasswordJL.setBounds(100, 170, 400, 30);
        inputPasswordJL.setBounds(310, 130, 200, 30);
        inputPasswordAgainJL.setBounds(310, 210, 200, 30);
        invalidPhoneNumberJL.setBounds(100, 250, 200, 30);
        occupiedPhoneNumberJL.setBounds(100, 250, 200, 30);
        codeTemp = getVerificationCode();
        codeJB.setText(codeTemp);
        codeJB.setContentAreaFilled(false);
        codeJB.setBorderPainted(false);
        submitJB.setBounds(200, 460, 70, 30);
        submitJB.addMouseListener(this);
        codeJB.addMouseListener(this);
        getContentPane().add(usernameJTF);
        getContentPane().add(passwordJPF);
        getContentPane().add(passwordAgainJPF);
        getContentPane().add(phoneNumberJTF);
        getContentPane().add(codeJTF);
        getContentPane().add(codeJB);
        getContentPane().add(submitJB);
        getContentPane().add(invalidCodeJL);
        getContentPane().add(invalidUsernameJL);
        getContentPane().add(occupiedUsernameJL);
        getContentPane().add(validUsernameJL);
        getContentPane().add(invalidPasswordJL);
        getContentPane().add(validPasswordJL);
        getContentPane().add(differentPasswordJL);
        getContentPane().add(inputPasswordJL);
        getContentPane().add(inputPasswordAgainJL);
        getContentPane().add(invalidPhoneNumberJL);
        getContentPane().add(occupiedPhoneNumberJL);
        invalidCodeJL.setVisible(false);
        invalidUsernameJL.setVisible(false);
        occupiedUsernameJL.setVisible(false);
        validUsernameJL.setVisible(false);
        invalidPasswordJL.setVisible(false);
        validPasswordJL.setVisible(false);
        differentPasswordJL.setVisible(false);
        invalidPhoneNumberJL.setVisible(false);
        occupiedPhoneNumberJL.setVisible(false);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        inputUsernameJL.setBounds(310, 50, 200, 30);
        inputPhoneNumberJL.setBounds(310, 290, 200, 30);
        getContentPane().add(inputUsernameJL);
        getContentPane().add(inputPhoneNumberJL);
        usernameJTF.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        passwordAgainJPF.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        codeJTF.addKeyListener(this);
        invalidCodeJL.setForeground(Color.RED);
        invalidUsernameJL.setForeground(Color.RED);
        occupiedUsernameJL.setForeground(Color.RED);
        invalidPasswordJL.setForeground(Color.RED);
        differentPasswordJL.setForeground(Color.RED);
        invalidPhoneNumberJL.setForeground(Color.RED);
        occupiedPhoneNumberJL.setForeground(Color.RED);
        submitJB.addKeyListener(this);
        backJB.addKeyListener(this);
        revealPasswordJL.setBounds(70, 130, 30, 30);
        revealPasswordPressedJL.setBounds(70, 130, 30, 30);
        getContentPane().add(revealPasswordJL);
        getContentPane().add(revealPasswordPressedJL);
        revealPasswordPressedJL.setVisible(false);
        revealPasswordJL.addMouseListener(this);
        revealPasswordPressedJL.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == exitJMI || thing == backJB) {
            setVisible(false);
            new Login();
        } else if (thing == codeJB) {
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
        } else if (thing == submitJB) {
            collectData();
            signup();
        } else if (thing == revealPasswordJL) {
            revealPasswordJL.setVisible(false);
            revealPasswordPressedJL.setVisible(true);
            showPassword();
        }
    }

    ///注册
    void signup() {
        if (checkUsername(username) && !checkUsernameUsed(library, username) && checkPassword(password) && password.equals(passwordAgain) && checkPhoneNumber(phoneNumber) && !checkPhoneNumberUsed(library, phoneNumber) && code.equals(codeTemp)) {
            library.add(new User(username, password, phoneNumber, 0, 1, 0, lastLDT));
            setVisible(false);
            new Login();
        } else {
            invalidCodeJL.setVisible(!code.equals(codeTemp));
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
            invalidUsernameJL.setVisible(!checkUsername(username));
            occupiedUsernameJL.setVisible(checkUsernameUsed(library, username));
            validUsernameJL.setVisible(checkUsername(username) && !checkUsernameUsed(library, username));
            invalidPasswordJL.setVisible(!checkPassword(password));
            invalidPhoneNumberJL.setVisible(!checkPhoneNumber(phoneNumber));
            occupiedPhoneNumberJL.setVisible(checkPhoneNumberUsed(library, phoneNumber));
            if (!password.equals(passwordAgain)) {
                invalidPasswordJL.setVisible(false);
                differentPasswordJL.setVisible(true);
            } else differentPasswordJL.setVisible(false);
            if (!checkPhoneNumber(phoneNumber)) {
                invalidPhoneNumberJL.setVisible(true);
                occupiedPhoneNumberJL.setVisible(false);
            }
            if (checkPhoneNumberUsed(library, phoneNumber)) {
                invalidPhoneNumberJL.setVisible(false);
                occupiedPhoneNumberJL.setVisible(true);
            }
        }
    }

    ///收集用户输入的数据
    @Override
    void collectData() {
        passwordSB.delete(0, passwordSB.length());
        username = usernameJTF.getText();
        for (char c : passwordJPF.getPassword()) {
            passwordSB.append(c);
        }
        password = passwordSB.toString();
        passwordSB.delete(0, passwordSB.length());
        for (char c : passwordAgainJPF.getPassword()) {
            passwordSB.append(c);
        }
        passwordAgain = passwordSB.toString();
        phoneNumber = phoneNumberJTF.getText();
        code = codeJTF.getText();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == revealPasswordPressedJL) {
            revealPasswordJL.setVisible(true);
            revealPasswordPressedJL.setVisible(false);
            hidePassword();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submitJB || thing == revealPasswordJL || thing == revealPasswordPressedJL || thing == codeJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submitJB || thing == revealPasswordJL || thing == revealPasswordPressedJL || thing == codeJB)
            setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 10) {
            collectData();
            signup();
        } else if (code == 27) {
            setVisible(false);
            new Login();
        }
    }
}
