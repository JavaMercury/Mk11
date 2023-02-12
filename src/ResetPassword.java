import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//重设密码
public class ResetPassword extends Initializer implements MouseListener, KeyListener {
    JMenu propertiesJM = new JMenu("选项");
    JMenuItem exitJMI = new JMenuItem("取消修改");
    JTextField phoneNumberJTF = new JTextField();
    JPasswordField passwordAgainJPF = new JPasswordField();
    JTextField codeJTF = new JTextField();
    JLabel inputPhoneNumberJL = new JLabel("输入手机号码用于验证");
    JLabel inputNewPasswordJL = new JLabel("输入新的密码");
    JLabel inputNewPasswordAgainJL = new JLabel("再次输入密码");
    JButton submitJB = new JButton("确定");
    JButton codeJB = new JButton();
    String phoneNumber;
    String codeTemp;
    String passwordAgain;
    String code;
    JLabel invalidPhoneNumberJL = new JLabel("手机号码无效，请重新输入");
    JLabel invalidCodeJL = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidPasswordJL = new JLabel("密码无效，密码长度在8-20之间，必须包括大小写字母和数字");
    JLabel differentPasswordJL = new JLabel("两次输入的密码不一致");
    JLabel samePasswordJL = new JLabel("新密码不能与旧密码相同");

    public ResetPassword(String username) {
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
        setTitle("水银第十代 重设密码");
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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

    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        phoneNumberJTF.setBounds(100, 50, 200, 30);
        inputPhoneNumberJL.setBounds(100, 20, 200, 30);
        submitJB.setBounds(100, 500, 100, 30);
        invalidPhoneNumberJL.setBounds(100, 80, 200, 30);
        passwordJPF.setBounds(100, 150, 200, 30);
        passwordAgainJPF.setBounds(100, 250, 200, 30);
        inputNewPasswordJL.setBounds(100, 120, 200, 30);
        inputNewPasswordAgainJL.setBounds(100, 220, 200, 30);
        codeJTF.setBounds(100, 350, 100, 30);
        codeJB.setBounds(200, 350, 80, 30);
        invalidPasswordJL.setBounds(100, 180, 400, 30);
        differentPasswordJL.setBounds(100, 280, 200, 30);
        invalidCodeJL.setBounds(100, 380, 400, 30);
        samePasswordJL.setBounds(100, 180, 400, 30);

        submitJB.addMouseListener(this);
        codeJB.addMouseListener(this);

        getContentPane().add(phoneNumberJTF);
        getContentPane().add(inputPhoneNumberJL);
        getContentPane().add(submitJB);
        getContentPane().add(invalidPhoneNumberJL);
        getContentPane().add(passwordJPF);
        getContentPane().add(passwordAgainJPF);
        getContentPane().add(inputNewPasswordJL);
        getContentPane().add(inputNewPasswordAgainJL);
        getContentPane().add(codeJTF);
        getContentPane().add(codeJB);
        getContentPane().add(invalidPasswordJL);
        getContentPane().add(differentPasswordJL);
        getContentPane().add(invalidCodeJL);
        getContentPane().add(samePasswordJL);

        invalidPhoneNumberJL.setVisible(false);
        codeJB.setContentAreaFilled(false);
        codeJB.setBorderPainted(false);
        invalidPasswordJL.setVisible(false);
        differentPasswordJL.setVisible(false);
        invalidCodeJL.setVisible(false);
        samePasswordJL.setVisible(false);

        codeTemp = getVerificationCode();
        codeJB.setText(codeTemp);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        invalidPhoneNumberJL.setForeground(Color.RED);
        invalidCodeJL.setForeground(Color.RED);
        invalidPasswordJL.setForeground(Color.RED);
        differentPasswordJL.setForeground(Color.RED);
        samePasswordJL.setForeground(Color.RED);
        submitJB.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        passwordAgainJPF.addKeyListener(this);
        codeJTF.addKeyListener(this);
        revealPasswordJL.setBounds(70, 150, 30, 30);
        revealPasswordPressedJL.setBounds(70, 150, 30, 30);
        getContentPane().add(revealPasswordJL);
        getContentPane().add(revealPasswordPressedJL);
        revealPasswordJL.addMouseListener(this);
        revealPasswordPressedJL.addMouseListener(this);
        revealPasswordPressedJL.setVisible(false);
    }

    ///重设密码
    void resetPassword() {
        if (code.equals(codeTemp) && !checkSamePhoneNumber(library, username, phoneNumber) && checkPassword(password) && !checkSamePassword(library, username, password) && password.equals(passwordAgain)) {
            User user = getUser(username);
            user.setPassword(password);
            setVisible(false);
            new Login();
        } else {
            invalidCodeJL.setVisible(!code.equals(codeTemp));
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
            invalidPhoneNumberJL.setVisible(checkSamePhoneNumber(library, username, phoneNumber));
            invalidPasswordJL.setVisible(!checkPassword(password));
            if (!password.equals(passwordAgain)) {
                invalidPasswordJL.setVisible(false);
                samePasswordJL.setVisible(false);
                differentPasswordJL.setVisible(true);
            } else differentPasswordJL.setVisible(false);
            if (checkSamePassword(library, username, password)) {
                invalidPasswordJL.setVisible(false);
                samePasswordJL.setVisible(true);
            }
        }
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
            new Menu(username);
        } else if (thing == codeJB) {
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
        } else if (thing == submitJB) {
            collectData();
            resetPassword();
        } else if (thing == revealPasswordJL) {
            revealPasswordJL.setVisible(false);
            revealPasswordPressedJL.setVisible(true);
            showPassword();
        }
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
            resetPassword();
        } else if (code == 27) {
            setVisible(false);
            new Menu(username);
        }
    }

    ///收集用户输入的数据
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
