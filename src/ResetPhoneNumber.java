import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//重设手机号码
public class ResetPhoneNumber extends Initializer{

    JMenu properties = new JMenu("选项");
    JMenuItem exitJMI = new JMenuItem("取消修改");
    JTextField phoneNumberJTF = new JTextField();
    JTextField codeJTF = new JTextField();
    JLabel inputPasswordJL = new JLabel("输入密码用于验证");
    JLabel inputNewPhoneNumberJL = new JLabel("输入新的手机号码");
    JButton submitJB = new JButton("确定");
    JButton codeJB = new JButton();
    String phoneNumber;
    String codeTemp;
    String code;
    JLabel invalidPhoneNumberJL = new JLabel("手机号码无效，请重新输入");
    JLabel invalidCodeJL = new JLabel("验证码输入有误，请重新输入，或点击验证码重新生成");
    JLabel invalidPasswordJL = new JLabel("密码无效，请重新输入");
    JLabel samePhoneNumberJL = new JLabel("新手机号码不能与旧手机号码相同");
    JLabel occupiedPhoneNumberJL = new JLabel("手机号码已被占用");

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
        properties.add(exitJMI);
        menuBar.add(properties);
        menuBar.add(aboutJM);
        aboutJM.addMouseListener(this);
        exitJMI.addMouseListener(this);
        setJMenuBar(menuBar);
    }

    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        passwordJPF.setBounds(100, 50, 200, 30);
        inputPasswordJL.setBounds(100, 20, 200, 30);
        submitJB.setBounds(100, 500, 100, 30);
        invalidPasswordJL.setBounds(100, 80, 200, 30);
        phoneNumberJTF.setBounds(100, 150, 200, 30);
        inputNewPhoneNumberJL.setBounds(100, 120, 200, 30);
        codeJTF.setBounds(100, 250, 100, 30);
        codeJB.setBounds(200, 257, 80, 16);
        invalidPhoneNumberJL.setBounds(100, 180, 400, 30);
        invalidCodeJL.setBounds(100, 280, 400, 30);
        samePhoneNumberJL.setBounds(100, 180, 400, 30);
        occupiedPhoneNumberJL.setBounds(100,180,400,30);

        submitJB.addMouseListener(this);
        codeJB.addMouseListener(this);

        getContentPane().add(phoneNumberJTF);
        getContentPane().add(inputPasswordJL);
        getContentPane().add(submitJB);
        getContentPane().add(invalidPhoneNumberJL);
        getContentPane().add(passwordJPF);
        getContentPane().add(inputNewPhoneNumberJL);
        getContentPane().add(codeJTF);
        getContentPane().add(codeJB);
        getContentPane().add(invalidPasswordJL);
        getContentPane().add(invalidCodeJL);
        getContentPane().add(samePhoneNumberJL);
        getContentPane().add(occupiedPhoneNumberJL);

        invalidPhoneNumberJL.setVisible(false);
        codeJB.setContentAreaFilled(false);
        codeJB.setBorderPainted(false);
        invalidPasswordJL.setVisible(false);
        invalidCodeJL.setVisible(false);
        samePhoneNumberJL.setVisible(false);

        codeTemp = getVerificationCode();
        codeJB.setText(codeTemp);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        backJB.addKeyListener(this);
        getContentPane().add(backJB);
        invalidPhoneNumberJL.setForeground(Color.RED);
        invalidCodeJL.setForeground(Color.RED);
        invalidPasswordJL.setForeground(Color.RED);
        samePhoneNumberJL.setForeground(Color.RED);
        occupiedPhoneNumberJL.setForeground(Color.RED);
        submitJB.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        phoneNumberJTF.addKeyListener(this);
        codeJTF.addKeyListener(this);
        revealPasswordJL.setBounds(70, 50, 30, 30);
        revealPasswordPressedJL.setBounds(70, 50, 30, 30);
        getContentPane().add(revealPasswordJL);
        getContentPane().add(revealPasswordPressedJL);
        revealPasswordPressedJL.setVisible(false);
        revealPasswordJL.addMouseListener(this);
        revealPasswordPressedJL.addMouseListener(this);
        occupiedPhoneNumberJL.setVisible(false);
    }

    ///重设手机号码
    private void resetPhoneNumber() {
        if (checkSamePassword(username, password) && checkPhoneNumber(phoneNumber) && checkSamePhoneNumber(username, phoneNumber) && !checkPhoneNumberUsed(library, phoneNumber) && code.equals(codeTemp)) {
            User user = getUser(username);
            user.setPhoneNumber(phoneNumber);
            setVisible(false);
            new Login();
        } else {
            invalidCodeJL.setVisible(!code.equals(codeTemp));
            codeTemp = getVerificationCode();
            codeJB.setText(codeTemp);
            //invalidPhoneNumberJL.setVisible(!checkSamePhoneNumber(library, username, phoneNumber));
            invalidPasswordJL.setVisible(!checkPassword(password));
            if (!checkPhoneNumber(phoneNumber)) {
                occupiedPhoneNumberJL.setVisible(false);
                samePhoneNumberJL.setVisible(false);
                invalidPhoneNumberJL.setVisible(true);
            }
            if (checkPhoneNumberUsed(library, phoneNumber)) {
                invalidPhoneNumberJL.setVisible(false);
                samePhoneNumberJL.setVisible(false);
                occupiedPhoneNumberJL.setVisible(true);
            }
            if (!checkSamePhoneNumber(username, phoneNumber)) {
                invalidPhoneNumberJL.setVisible(false);
                occupiedPhoneNumberJL.setVisible(false);
                samePhoneNumberJL.setVisible(true);
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
            resetPhoneNumber();
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
        if (thing == backJB || thing == submitJB || thing == revealPasswordJL || thing == revealPasswordPressedJL)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else if (thing == codeJB) {
            codeJB.setText(String.format("<HTML><U>%s", codeTemp));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submitJB || thing == revealPasswordJL || thing == revealPasswordPressedJL)
            setCursor(Cursor.getDefaultCursor());
        else if (thing == codeJB) {
            codeJB.setText(codeTemp);
            setCursor(Cursor.getDefaultCursor());
        }
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
        }else if (code == 71) showAbout();
    }

    ///收集用户输入的数据
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
