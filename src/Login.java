import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;

//用户登录界面
public class Login extends Initializer {

    JButton loginJB = new JButton(new ImageIcon("image\\login\\登录按钮.png"));
    JButton signupJB = new JButton(new ImageIcon("image\\login\\注册按钮.png"));
    JTextField usernameJTF = new JTextField();
    JLabel invalidInputJL = new JLabel(new ImageIcon("image\\login\\登录输入有误.png"));
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
    public static boolean checkSameUsername(HashSet<User> library, String username) {
        if (checkUserExist(library, username)) {
            User user = getUser(username);
            return username.equals(user.getUsername());
        } else return false;
    }

    ///判断输入新密码的用户是否存在
    public static boolean checkUserExist(HashSet<User> library, String username) {
        for (User user : library) {
            String rightUsername = user.getUsername();
            if (username.equals(rightUsername))
                return true;
        }
        return false;
    }

    ///判断密码是否重复
    public static boolean checkSamePassword(HashSet<User> library, String username, String password) {
        if (checkUserExist(library, username)) {
            User user = getUser(username);
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
        loginMenuBar.add(aboutJM);
        aboutJM.addMouseListener(this);
        setJMenuBar(loginMenuBar);
    }

    ///内容初始化
    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        usernameJTF.setBounds(100, 70, 340, 30);
        passwordJPF.setBounds(100, 170, 340, 30);
        loginJB.setBounds(110, 70, 128, 47);
        loginJB.setContentAreaFilled(false);
        loginJB.setBorderPainted(false);
        loginJB.addMouseListener(this);
        signupJB.setBounds(302, 70, 128, 47);
        signupJB.setContentAreaFilled(false);
        signupJB.setBorderPainted(false);
        signupJB.addMouseListener(this);
        invalidInputJL.setBounds(100, 215, 221, 34);
        invalidInputJL.setVisible(false);
        getContentPane().add(invalidInputJL);
        getContentPane().add(loginJB);
        getContentPane().add(signupJB);
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
        revealPasswordJL.setBounds(70, 170, 30, 30);
        revealPasswordPressedJL.setBounds(70, 170, 30, 30);
        getContentPane().add(revealPasswordJL);
        getContentPane().add(revealPasswordPressedJL);
        revealPasswordPressedJL.setVisible(false);
        revealPasswordJL.addMouseListener(this);
        revealPasswordPressedJL.addMouseListener(this);
        revealPasswordJL.setVisible(false);
        loginJB.addKeyListener(this);
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
        if (thing == aboutJM) {
            showAbout();
        } else if (thing == revealPasswordJL) {
            revealPasswordJL.setVisible(false);
            revealPasswordPressedJL.setVisible(true);
            showPassword();
        }
    }

    ///显示输入框
    void showLogin() {
        revealPasswordJL.setVisible(true);
        usernameJTF.setVisible(true);
        passwordJPF.setVisible(true);
        usernameJL.setVisible(true);
        passwordJL.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == loginJB) {
            setSize(555, 469);
            loginJB.setLocation(110, 270);
            signupJB.setLocation(302, 270);
            showLogin();
            collectData();
            login();
        } else if (thing == signupJB) {
            setVisible(false);
            new Signup();
        } else if (thing == revealPasswordPressedJL) {
            revealPasswordJL.setVisible(true);
            revealPasswordPressedJL.setVisible(false);
            hidePassword();
        }
    }

    ///登录
    void login() {
        invalidInputJL.setVisible(false);
        if (checkSameUsername(library, username) && checkSamePassword(library, username, passwordSB.toString())) {
            setVisible(false);
            new Menu(username);
        } else if ((!username.equals("") || passwordSB.length() != 0))
            invalidInputJL.setVisible(true);
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
        if (thing == loginJB) loginJB.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        else if (thing == signupJB) signupJB.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        if (thing == loginJB || thing == signupJB || thing == revealPasswordJL || thing == revealPasswordPressedJL)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == loginJB) loginJB.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        else if (thing == signupJB) signupJB.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        if (thing == loginJB || thing == signupJB || thing == revealPasswordJL || thing == revealPasswordPressedJL)
            setCursor(Cursor.getDefaultCursor());
    }

}
