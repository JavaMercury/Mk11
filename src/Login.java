import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Login extends Initializer implements KeyListener, MouseListener {

    JMenu aboutJMenu = new JMenu("关于");
    JButton logInButton = new JButton(new ImageIcon("image\\login\\登录按钮.png"));
    JButton signUpButton = new JButton(new ImageIcon("image\\login\\注册按钮.png"));
    JTextField usernameJTF = new JTextField();
    JLabel wrongInputWarning = new JLabel(new ImageIcon("image\\login\\登录输入有误.png"));
    JLabel usernameJL = new JLabel("用户名");
    JLabel passwordJL = new JLabel("密码");

    public Login() {
        addMouseListener(this);
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///判断用户名是否重复
    public static boolean checkSameUsername(ArrayList<User> library, String username) {
        if (checkUserExist(library, username)) {
            User user = library.get(getUserIndex(library, username));
            return username.equals(user.getUsername());
        } else return false;
    }

    ///判断输入新密码的用户是否存在
    public static boolean checkUserExist(ArrayList<User> library, String username) {
        for (User user : library) {
            String rightUsername = user.getUsername();
            if (username.equals(rightUsername))
                return true;
        }
        return false;
    }

    ///获取用户名对应的索引
    public static int getUserIndex(ArrayList<User> library, String username) {
        int index;
        for (index = 0; index < library.size(); index++) {
            User user = library.get(index);
            if (user.getUsername().equals(username)) break;
        }
        return index;
    }

    ///判断密码是否重复
    public static boolean checkSamePassword(ArrayList<User> library, String username, String password) {
        if (checkUserExist(library, username)) {
            User user = library.get(getUserIndex(library, username));
            return user.getPassword().equals(password);
        } else return false;
    }

    ///窗口初始化
    @Override
    void initJFrame() {
        setLayout(null);
        setSize(555, 269);
        setTitle(version);
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    ///菜单初始化
    @Override
    void initMenuBar() {
        JMenuBar loginMenuBar = new JMenuBar();
        loginMenuBar.add(aboutJMenu);
        aboutJMenu.addMouseListener(this);
        setJMenuBar(loginMenuBar);
    }

    ///内容初始化
    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        usernameJTF.setBounds(100, 70, 340, 30);
        passwordJPF.setBounds(100, 170, 340, 30);
        logInButton.setBounds(110, 70, 128, 47);
        logInButton.setContentAreaFilled(false);
        logInButton.setBorderPainted(false);
        logInButton.addMouseListener(this);
        signUpButton.setBounds(302, 70, 128, 47);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBorderPainted(false);
        signUpButton.addMouseListener(this);
        wrongInputWarning.setBounds(100, 215, 221, 34);
        wrongInputWarning.setVisible(false);
        getContentPane().add(wrongInputWarning);
        getContentPane().add(logInButton);
        getContentPane().add(signUpButton);
        getContentPane().add(usernameJTF);
        getContentPane().add(passwordJPF);
        usernameJL.setBounds(100, 30, 100, 50);
        passwordJL.setBounds(100, 130, 100, 50);
        getContentPane().add(usernameJL);
        getContentPane().add(passwordJL);
        usernameJTF.addKeyListener(this);
        passwordJPF.addKeyListener(this);
        usernameJTF.setVisible(false);
        passwordJPF.setVisible(false);
        usernameJL.setVisible(false);
        passwordJL.setVisible(false);
        revealPassword.setBounds(70, 170, 30, 30);
        revealPasswordPressed.setBounds(70, 170, 30, 30);
        getContentPane().add(revealPassword);
        getContentPane().add(revealPasswordPressed);
        revealPasswordPressed.setVisible(false);
        revealPassword.addMouseListener(this);
        revealPasswordPressed.addMouseListener(this);
        revealPassword.setVisible(false);
        logInButton.addKeyListener(this);
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
            login();
        } else if (code == 109) {
            setVisible(false);
            new Menu("qwerqwer");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == aboutJMenu) {
            showAbout();
        } else if (thing == revealPassword) {
            revealPassword.setVisible(false);
            revealPasswordPressed.setVisible(true);
            showPassword();
        }
    }

    ///显示输入框
    void showLogin() {
        revealPassword.setVisible(true);
        usernameJTF.setVisible(true);
        passwordJPF.setVisible(true);
        usernameJL.setVisible(true);
        passwordJL.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == logInButton) {
            setSize(555, 469);
            logInButton.setLocation(110, 270);
            signUpButton.setLocation(302, 270);
            showLogin();
            collectData();
            login();
        } else if (thing == signUpButton) {
            setVisible(false);
            new SignUp();
        } else if (thing == revealPasswordPressed) {
            revealPassword.setVisible(true);
            revealPasswordPressed.setVisible(false);
            hidePassword();
        }
    }

    ///登录
    void login() {
        wrongInputWarning.setVisible(false);
        if (checkSameUsername(library, username) && checkSamePassword(library, username, passwordSB.toString())) {
            setVisible(false);
            new Menu(username);
        } else if ((!username.equals("") || passwordSB.length() != 0))
            wrongInputWarning.setVisible(true);
    }

    ///采集登录信息
    void collectData() {
        username = usernameJTF.getText();
        passwordSB.delete(0, passwordSB.length());
        for (char c : passwordJPF.getPassword()) {
            passwordSB.append(c);
        }
        password = passwordSB.toString();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == logInButton) logInButton.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        else if (thing == signUpButton) signUpButton.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        if (thing == logInButton || thing == signUpButton || thing == revealPassword || thing == revealPasswordPressed)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == logInButton) logInButton.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        else if (thing == signUpButton) signUpButton.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        if (thing == logInButton || thing == signUpButton || thing == revealPassword || thing == revealPasswordPressed)
            setCursor(Cursor.getDefaultCursor());
    }

}
