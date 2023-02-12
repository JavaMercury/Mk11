import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SignUp extends Initializer implements MouseListener, KeyListener {

    JMenu me = new JMenu("我的");
    JMenu about = new JMenu("关于");
    JMenuItem exit = new JMenuItem("中止注册");
    JTextField usernameJTF = new JTextField();
    JPasswordField passwordAgainJTF = new JPasswordField();
    JTextField phoneNumberJTF = new JTextField();
    JButton submit = new JButton("确定");
    JTextField inputCodeJTF = new JTextField();
    JButton verificationCodeJB = new JButton();
    String passwordAgain;
    String phoneNumber;
    String inputCode;
    String codeTemp;
    JLabel wrongCodeWarning = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidUsernameWarning = new JLabel("用户名无效，用户名长度在5-15之间，只能使用字母、数字及下划线，且不能为纯数字");
    JLabel occupiedUsernameWarning = new JLabel("用户名已被占用");
    JLabel validUsernameMessage = new JLabel("用户名可用");
    JLabel invalidPasswordWarning = new JLabel("密码无效，密码长度在8-20之间，必须包括大小写字母和数字");
    JLabel differentPasswordWarning = new JLabel("两次输入的密码不一致");
    JLabel validPasswordMessage = new JLabel("密码可用");
    JLabel passwordInputHint = new JLabel("输入密码");
    JLabel passwordAgainInputHint = new JLabel("再次输入密码");
    JLabel invalidPhoneNumberWarning = new JLabel("手机号码无效");
    JLabel occupiedPhoneNumberWarning = new JLabel("手机号已被占用");
    JLabel inputUsernameMessage = new JLabel("输入用户名");
    JLabel inputPhoneNumberMessage = new JLabel("输入大陆手机号码");

    public SignUp() {
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///判断注册时用户名是否被占用
    public static boolean checkUsernameUsed(ArrayList<User> library, String username) {
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
        me.add(exit);
        menuBar.add(me);
        menuBar.add(about);
        about.addMouseListener(this);
        exit.addMouseListener(this);
        setJMenuBar(menuBar);
    }

    ///内容初始化
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        usernameJTF.setBounds(100, 50, 200, 30);
        passwordJPF.setBounds(100, 130, 200, 30);
        passwordAgainJTF.setBounds(100, 210, 200, 30);
        phoneNumberJTF.setBounds(100, 290, 200, 30);
        inputCodeJTF.setBounds(100, 370, 100, 30);
        verificationCodeJB.setBounds(200, 370, 80, 30);
        wrongCodeWarning.setBounds(100, 330, 400, 30);
        invalidUsernameWarning.setBounds(100, 10, 500, 30);
        occupiedUsernameWarning.setBounds(100, 10, 400, 30);
        validUsernameMessage.setBounds(100, 10, 400, 30);
        invalidPasswordWarning.setBounds(100, 90, 400, 30);
        validPasswordMessage.setBounds(100, 90, 400, 30);
        differentPasswordWarning.setBounds(100, 170, 400, 30);
        passwordInputHint.setBounds(310, 130, 200, 30);
        passwordAgainInputHint.setBounds(310, 210, 200, 30);
        invalidPhoneNumberWarning.setBounds(100, 250, 200, 30);
        occupiedPhoneNumberWarning.setBounds(100, 250, 200, 30);
        codeTemp = getVerificationCode();
        verificationCodeJB.setText(codeTemp);
        verificationCodeJB.setContentAreaFilled(false);
        verificationCodeJB.setBorderPainted(false);
        submit.setBounds(200, 460, 70, 30);
        submit.addMouseListener(this);
        verificationCodeJB.addMouseListener(this);
        getContentPane().add(usernameJTF);
        getContentPane().add(passwordJPF);
        getContentPane().add(passwordAgainJTF);
        getContentPane().add(phoneNumberJTF);
        getContentPane().add(inputCodeJTF);
        getContentPane().add(verificationCodeJB);
        getContentPane().add(submit);
        getContentPane().add(wrongCodeWarning);
        getContentPane().add(invalidUsernameWarning);
        getContentPane().add(occupiedUsernameWarning);
        getContentPane().add(validUsernameMessage);
        getContentPane().add(invalidPasswordWarning);
        getContentPane().add(validPasswordMessage);
        getContentPane().add(differentPasswordWarning);
        getContentPane().add(passwordInputHint);
        getContentPane().add(passwordAgainInputHint);
        getContentPane().add(invalidPhoneNumberWarning);
        getContentPane().add(occupiedPhoneNumberWarning);
        wrongCodeWarning.setVisible(false);
        invalidUsernameWarning.setVisible(false);
        occupiedUsernameWarning.setVisible(false);
        validUsernameMessage.setVisible(false);
        invalidPasswordWarning.setVisible(false);
        validPasswordMessage.setVisible(false);
        differentPasswordWarning.setVisible(false);
        invalidPhoneNumberWarning.setVisible(false);
        occupiedPhoneNumberWarning.setVisible(false);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        inputUsernameMessage.setBounds(310, 50, 200, 30);
        inputPhoneNumberMessage.setBounds(310, 290, 200, 30);
        getContentPane().add(inputUsernameMessage);
        getContentPane().add(inputPhoneNumberMessage);
        usernameJTF.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        passwordAgainJTF.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        inputCodeJTF.addKeyListener(this);
        wrongCodeWarning.setForeground(Color.RED);
        invalidUsernameWarning.setForeground(Color.RED);
        occupiedUsernameWarning.setForeground(Color.RED);
        invalidPasswordWarning.setForeground(Color.RED);
        differentPasswordWarning.setForeground(Color.RED);
        invalidPhoneNumberWarning.setForeground(Color.RED);
        occupiedPhoneNumberWarning.setForeground(Color.RED);
        submit.addKeyListener(this);
        backJB.addKeyListener(this);
        revealPassword.setBounds(70, 130, 30, 30);
        revealPasswordPressed.setBounds(70, 130, 30, 30);
        getContentPane().add(revealPassword);
        getContentPane().add(revealPasswordPressed);
        revealPasswordPressed.setVisible(false);
        revealPassword.addMouseListener(this);
        revealPasswordPressed.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == about) showAbout();
        else if (thing == exit || thing == backJB) {
            setVisible(false);
            new Login();
        } else if (thing == verificationCodeJB) {
            codeTemp = getVerificationCode();
            verificationCodeJB.setText(codeTemp);
        } else if (thing == submit) {
            collectData();
            signup();
        } else if (thing == revealPassword) {
            revealPassword.setVisible(false);
            revealPasswordPressed.setVisible(true);
            showPassword();
        }
    }

    ///注册
    void signup() {
        if (checkUsername(username) && !checkUsernameUsed(library, username) && checkPassword(password) && password.equals(passwordAgain) && checkPhoneNumber(phoneNumber) && !checkPhoneNumberUsed(library, phoneNumber) && inputCode.equals(codeTemp)) {
            library.add(new User(username, password, phoneNumber));
            setVisible(false);
            new Login();
        } else {
            wrongCodeWarning.setVisible(!inputCode.equals(codeTemp));
            codeTemp = getVerificationCode();
            verificationCodeJB.setText(codeTemp);
            invalidUsernameWarning.setVisible(!checkUsername(username));
            occupiedUsernameWarning.setVisible(checkUsernameUsed(library, username));
            validUsernameMessage.setVisible(checkUsername(username) && !checkUsernameUsed(library, username));
            invalidPasswordWarning.setVisible(!checkPassword(password));
            invalidPhoneNumberWarning.setVisible(!checkPhoneNumber(phoneNumber));
            occupiedPhoneNumberWarning.setVisible(checkPhoneNumberUsed(library, phoneNumber));
            if (!password.equals(passwordAgain)) {
                invalidPasswordWarning.setVisible(false);
                differentPasswordWarning.setVisible(true);
            } else differentPasswordWarning.setVisible(false);
            if (!checkPhoneNumber(phoneNumber)) {
                invalidPhoneNumberWarning.setVisible(true);
                occupiedPhoneNumberWarning.setVisible(false);
            }
            if (checkPhoneNumberUsed(library, phoneNumber)) {
                invalidPhoneNumberWarning.setVisible(false);
                occupiedPhoneNumberWarning.setVisible(true);
            }
        }
    }

    ///采集注册信息
    @Override
    void collectData() {
        passwordSB.delete(0, passwordSB.length());
        username = usernameJTF.getText();
        for (char c : passwordJPF.getPassword()) {
            passwordSB.append(c);
        }
        password = passwordSB.toString();
        passwordSB.delete(0, passwordSB.length());
        for (char c : passwordAgainJTF.getPassword()) {
            passwordSB.append(c);
        }
        passwordAgain = passwordSB.toString();
        phoneNumber = phoneNumberJTF.getText();
        inputCode = inputCodeJTF.getText();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == revealPasswordPressed) {
            revealPassword.setVisible(true);
            revealPasswordPressed.setVisible(false);
            hidePassword();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submit || thing == revealPassword || thing == revealPasswordPressed || thing == verificationCodeJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submit || thing == revealPassword || thing == revealPasswordPressed || thing == verificationCodeJB)
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
