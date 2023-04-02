import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

//水银主菜单
public class MainMenu extends Initializer implements FocusListener {

    JButton gamesJB = new JButton("小游戏");
    JButton functionsJB = new JButton("实用功能");
    JMenuItem resetPasswordJMI = new JMenuItem("修改密码");
    JMenuItem resetPhoneNumberJMI = new JMenuItem("修改手机号码");
    JMenuItem logoutJMI = new JMenuItem("退出登录");
    JMenuItem signInJMI = new JMenuItem("每日签到");
    JMenuItem profileJMI = new JMenuItem("账户信息");
    int focusSelect;

    public MainMenu(String username) {
        this.username = username;
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    @Override
    void collectData() {

    }

    ///窗口初始化
    @Override
    void initJFrame() {
        setLayout(null);
        setSize(356, 390);
        setIcon();
        setTitle(version);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    ///菜单初始化
    @Override
    void initMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu meJM = new JMenu("我的");
        meJM.add(profileJMI);
        meJM.add(signInJMI);
        meJM.add(logoutJMI);
        meJM.add(resetPasswordJMI);
        meJM.add(resetPhoneNumberJMI);
        jmb.add(meJM);
        jmb.add(aboutJM);
        setJMenuBar(jmb);
    }

    ///内容初始化
    @Override
    void initContent() {
        con.setBackground(Color.WHITE);
        functionsJB.setBounds(95, 80, 150, 50);
        gamesJB.setBounds(95, 180, 150, 50);
        con.add(gamesJB);
        con.add(functionsJB);
        functionsJB.addMouseListener(this);
        gamesJB.addMouseListener(this);
        aboutJM.addMouseListener(this);
        logoutJMI.addMouseListener(this);
        resetPasswordJMI.addMouseListener(this);
        resetPhoneNumberJMI.addMouseListener(this);
        functionsJB.addKeyListener(this);
        gamesJB.addKeyListener(this);
        functionsJB.addFocusListener(this);
        gamesJB.addFocusListener(this);
        signInJMI.addMouseListener(this);
        profileJMI.addMouseListener(this);
        aboutJM.addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == aboutJM) {
            dispose();
        } else if (thing == gamesJB) {
            dispose();
            new GamesMenu(username);
        } else if (thing == functionsJB) {
            dispose();
            new FunctionsMenu(username);
        } else if (thing == logoutJMI) {
            dispose();
            new Login();
        } else if (thing == resetPasswordJMI) {
            dispose();
            new ResetPassword(username);
        } else if (thing == resetPhoneNumberJMI) {
            dispose();
            new ResetPhoneNumber(username);
        } else if (thing == signInJMI) {
            dispose();
            new SignIn(username);
        } else if (thing == profileJMI) {
            dispose();
            try {
                new Profile(username);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == functionsJB || thing == gamesJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == functionsJB || thing == gamesJB)
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
            switch (focusSelect) {
                case 1: {
                    dispose();
                    new FunctionsMenu(username);
                    break;
                }
                case 2: {
                    dispose();
                    new GamesMenu(username);
                    break;
                }
            }
        } else if (code == 27) {
            dispose();
            new Login();
        } else if (code == 71) showAbout();
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object thing = e.getSource();
        if (thing == functionsJB) {
            focusSelect = 1;
        } else if (thing == gamesJB) {
            focusSelect = 2;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
