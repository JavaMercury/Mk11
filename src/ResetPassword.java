import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ResetPassword extends Initializer implements MouseListener, KeyListener {
    JMenu properties = new JMenu("选项");
    JMenu about = new JMenu("关于");
    JMenuItem exit = new JMenuItem("取消修改");
    JTextField phoneNumberJTF = new JTextField();
    JPasswordField passwordAgainJPF = new JPasswordField();
    JTextField codeJTF = new JTextField();
    JLabel inputPhoneNumberMessage = new JLabel("输入手机号码用于验证");
    JLabel inputNewPasswordMessage = new JLabel("输入新的密码");
    JLabel inputNewPasswordAgainMessage = new JLabel("再次输入密码");
    JButton submit = new JButton("确定");
    JButton codeJB = new JButton();
    String phoneNumber;
    String codeTemp;
    String passwordAgain;
    String code;
    JLabel invalidPhoneNumberWarning = new JLabel("手机号码无效，请重新输入");
    JLabel invalidCodeWarning = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidPasswordWarning = new JLabel("密码无效，密码长度在8-20之间，必须包括大小写字母和数字");
    JLabel differentPasswordWarning = new JLabel("两次输入的密码不一致");
    JLabel samePasswordWarning = new JLabel("新密码不能与旧密码相同");

    public ResetPassword(String username) {
        super.username = username;
        setResizable(false);
        initJFrame();
        initMenuBar();
        initContent();
        setVisible(true);
    }

    ///判断手机号码是否重复
    public static boolean checkSamePhoneNumber(ArrayList<User> library, String username, String phoneNumber) {
        User user = library.get(getUserIndex(library, username));
        return phoneNumber.equals(user.getPhoneNumber());
    }

    @Override
    void initJFrame() {
        setLayout(null);
        setSize(640, 648);
        setTitle("水银第十代 重设密码");
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        properties.add(exit);
        menuBar.add(properties);
        menuBar.add(about);
        about.addMouseListener(this);
        exit.addMouseListener(this);
        setJMenuBar(menuBar);
    }

    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        phoneNumberJTF.setBounds(100, 50, 200, 30);
        inputPhoneNumberMessage.setBounds(100, 20, 200, 30);
        submit.setBounds(100, 500, 100, 30);
        invalidPhoneNumberWarning.setBounds(100, 80, 200, 30);
        passwordJPF.setBounds(100, 150, 200, 30);
        passwordAgainJPF.setBounds(100, 250, 200, 30);
        inputNewPasswordMessage.setBounds(100, 120, 200, 30);
        inputNewPasswordAgainMessage.setBounds(100, 220, 200, 30);
        codeJTF.setBounds(100, 350, 100, 30);
        codeJB.setBounds(200, 350, 80, 30);
        invalidPasswordWarning.setBounds(100, 180, 400, 30);
        differentPasswordWarning.setBounds(100, 280, 200, 30);
        invalidCodeWarning.setBounds(100, 380, 400, 30);
        samePasswordWarning.setBounds(100, 180, 400, 30);

        submit.addMouseListener(this);
        codeJB.addMouseListener(this);

        getContentPane().add(phoneNumberJTF);
        getContentPane().add(inputPhoneNumberMessage);
        getContentPane().add(submit);
        getContentPane().add(invalidPhoneNumberWarning);
        getContentPane().add(passwordJPF);
        getContentPane().add(passwordAgainJPF);
        getContentPane().add(inputNewPasswordMessage);
        getContentPane().add(inputNewPasswordAgainMessage);
        getContentPane().add(codeJTF);
        getContentPane().add(codeJB);
        getContentPane().add(invalidPasswordWarning);
        getContentPane().add(differentPasswordWarning);
        getContentPane().add(invalidCodeWarning);
        getContentPane().add(samePasswordWarning);

        invalidPhoneNumberWarning.setVisible(false);
        codeJB.setContentAreaFilled(false);
        codeJB.setBorderPainted(false);
        invalidPasswordWarning.setVisible(false);
        differentPasswordWarning.setVisible(false);
        invalidCodeWarning.setVisible(false);
        samePasswordWarning.setVisible(false);

        codeTemp = getVerificationCode();
        codeJB.setText(codeTemp);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        invalidPhoneNumberWarning.setForeground(Color.RED);
        invalidCodeWarning.setForeground(Color.RED);
        invalidPasswordWarning.setForeground(Color.RED);
        differentPasswordWarning.setForeground(Color.RED);
        samePasswordWarning.setForeground(Color.RED);
        submit.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        passwordAgainJPF.addKeyListener(this);
        codeJTF.addKeyListener(this);
        revealPassword.setBounds(70, 150, 30, 30);
        revealPasswordPressed.setBounds(70, 150, 30, 30);
        getContentPane().add(revealPassword);
        getContentPane().add(revealPasswordPressed);
        revealPassword.addMouseListener(this);
        revealPasswordPressed.addMouseListener(this);
        revealPasswordPressed.setVisible(false);
    }

    void resetPassword() {
        if (code.equals(codeTemp) && checkSamePhoneNumber(library, username, phoneNumber) && checkPassword(password) && !checkSamePassword(library, username, password) && password.equals(passwordAgain)) {
            User user = library.get(getUserIndex(library, username));
            user.setPassword(password);
            setVisible(false);
            new Login();
        } else {
            invalidCodeWarning.setVisible(!code.equals(codeTemp));
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
            invalidPhoneNumberWarning.setVisible(!checkSamePhoneNumber(library, username, phoneNumber));
            invalidPasswordWarning.setVisible(!checkPassword(password));
            if (!password.equals(passwordAgain)) {
                invalidPasswordWarning.setVisible(false);
                samePasswordWarning.setVisible(false);
                differentPasswordWarning.setVisible(true);
            } else differentPasswordWarning.setVisible(false);
            if (checkSamePassword(library, username, password)) {
                invalidPasswordWarning.setVisible(false);
                samePasswordWarning.setVisible(true);
            }
        }
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
            new Menu(username);
        } else if (thing == codeJB) {
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
        } else if (thing == submit) {
            collectData();
            resetPassword();
        } else if (thing == revealPassword) {
            revealPassword.setVisible(false);
            revealPasswordPressed.setVisible(true);
            showPassword();
        }
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
        if (thing == backJB || thing == submit || thing == revealPassword || thing == revealPasswordPressed || thing == codeJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submit || thing == revealPassword || thing == revealPasswordPressed || thing == codeJB)
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
            resetPassword();
        } else if (code == 27) {
            setVisible(false);
            new Menu(username);
        }
    }

    void collectData() {
        passwordSB.delete(0, passwordSB.length());
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
}
