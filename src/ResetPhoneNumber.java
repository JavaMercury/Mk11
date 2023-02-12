import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResetPhoneNumber extends Initializer implements MouseListener, KeyListener {

    JMenu properties = new JMenu("选项");
    JMenu about = new JMenu("关于");
    JMenuItem exit = new JMenuItem("取消修改");
    JTextField phoneNumberJTF = new JTextField();
    JTextField codeJTF = new JTextField();
    JLabel inputPasswordMessage = new JLabel("输入密码用于验证");
    JLabel inputNewPhoneNumberMessage = new JLabel("输入新的手机号码");
    JButton submit = new JButton("确定");
    JButton codeJB = new JButton();
    String phoneNumber;
    String codeTemp;
    String code;
    JLabel invalidPhoneNumberWarning = new JLabel("手机号码无效，请重新输入");
    JLabel invalidCodeWarning = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidPasswordWarning = new JLabel("密码无效，请重新输入");
    JLabel samePhoneNumberWarning = new JLabel("新手机号码不能与旧手机号码相同");
    JLabel occupiedPhoneNumberWarning = new JLabel("手机号码已被占用");

    public ResetPhoneNumber(String username) {
        super.username = username;
        setResizable(false);
        initJFrame();
        initMenuBar();
        initContent();
        setVisible(true);
    }

    @Override
    void initJFrame() {
        setLayout(null);
        setSize(640, 648);
        setTitle("水银第十代 重设手机号码");
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
        passwordJPF.setBounds(100, 50, 200, 30);
        inputPasswordMessage.setBounds(100, 20, 200, 30);
        submit.setBounds(100, 500, 100, 30);
        invalidPasswordWarning.setBounds(100, 80, 200, 30);
        phoneNumberJTF.setBounds(100, 150, 200, 30);
        inputNewPhoneNumberMessage.setBounds(100, 120, 200, 30);
        codeJTF.setBounds(100, 250, 100, 30);
        codeJB.setBounds(200, 250, 80, 30);
        invalidPhoneNumberWarning.setBounds(100, 180, 400, 30);
        invalidCodeWarning.setBounds(100, 280, 400, 30);
        samePhoneNumberWarning.setBounds(100, 180, 400, 30);

        submit.addMouseListener(this);
        codeJB.addMouseListener(this);

        getContentPane().add(phoneNumberJTF);
        getContentPane().add(inputPasswordMessage);
        getContentPane().add(submit);
        getContentPane().add(invalidPhoneNumberWarning);
        getContentPane().add(passwordJPF);
        getContentPane().add(inputNewPhoneNumberMessage);
        getContentPane().add(codeJTF);
        getContentPane().add(codeJB);
        getContentPane().add(invalidPasswordWarning);
        getContentPane().add(invalidCodeWarning);
        getContentPane().add(samePhoneNumberWarning);

        invalidPhoneNumberWarning.setVisible(false);
        codeJB.setContentAreaFilled(false);
        codeJB.setBorderPainted(false);
        invalidPasswordWarning.setVisible(false);
        invalidCodeWarning.setVisible(false);
        samePhoneNumberWarning.setVisible(false);

        codeTemp = getVerificationCode();
        codeJB.setText(codeTemp);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        invalidPhoneNumberWarning.setForeground(Color.RED);
        invalidCodeWarning.setForeground(Color.RED);
        invalidPasswordWarning.setForeground(Color.RED);
        samePhoneNumberWarning.setForeground(Color.RED);
        occupiedPhoneNumberWarning.setForeground(Color.RED);
        submit.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        codeJTF.addKeyListener(this);
        revealPassword.setBounds(70, 50, 30, 30);
        revealPasswordPressed.setBounds(70, 50, 30, 30);
        getContentPane().add(revealPassword);
        getContentPane().add(revealPasswordPressed);
        revealPasswordPressed.setVisible(false);
        revealPassword.addMouseListener(this);
        revealPasswordPressed.addMouseListener(this);
    }

    void resetPhoneNumber() {
        if (code.equals(codeTemp) && checkSamePassword(library, username, password) && checkPhoneNumber(phoneNumber) && checkSamePhoneNumber(library, username, phoneNumber) && !checkPhoneNumberUsed(library, phoneNumber)) {
            User user = library.get(getUserIndex(library, username));
            user.setPhoneNumber(phoneNumber);
            setVisible(false);
            new Login();
        } else {
            invalidCodeWarning.setVisible(!code.equals(codeTemp));
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
            invalidCodeWarning.setVisible(!code.equals(codeTemp));
            invalidPhoneNumberWarning.setVisible(!checkSamePhoneNumber(library, username, phoneNumber));
            invalidPasswordWarning.setVisible(!checkPassword(password));
            if (!checkPhoneNumber(phoneNumber)) {
                occupiedPhoneNumberWarning.setVisible(false);
                samePhoneNumberWarning.setVisible(false);
                invalidPhoneNumberWarning.setVisible(true);
            }
            if (checkPhoneNumberUsed(library, phoneNumber)) {
                invalidPhoneNumberWarning.setVisible(false);
                samePhoneNumberWarning.setVisible(false);
                occupiedPhoneNumberWarning.setVisible(true);
            }
            if (!checkSamePhoneNumber(library, username, phoneNumber)) {
                invalidPhoneNumberWarning.setVisible(false);
                occupiedPhoneNumberWarning.setVisible(false);
                samePhoneNumberWarning.setVisible(true);
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
            resetPhoneNumber();
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
            resetPhoneNumber();
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
        phoneNumber = phoneNumberJTF.getText() + "";
        code = codeJTF.getText();
    }
}
