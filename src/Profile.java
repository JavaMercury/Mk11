import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

//展示用户资料
public class Profile extends Initializer {
    JMenu propertiesJM = new JMenu("选项");
    JMenuItem backJMI = new JMenuItem("返回主菜单");
    JLabel profileJL = new JLabel();
    JLabel changePhoneNumberJL = new JLabel("修改");

    public Profile(String username) {
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

    @Override
    void initJFrame() {
        setLayout(null);
        setSize(356, 390);
        setTitle(version);
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    void initMenuBar() {
        JMenuBar jmb = new JMenuBar();
        jmb.add(propertiesJM);
        propertiesJM.add(backJMI);
        jmb.add(aboutJM);
        aboutJM.addMouseListener(this);
        propertiesJM.addMouseListener(this);
        backJMI.addMouseListener(this);
        setJMenuBar(jmb);
    }

    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        backJB.addKeyListener(this);
        getContentPane().add(backJB);
        profileJL.setBounds(50, 0, 356, 360);
        profileJL.setText("<html>用户名：" + username + "<br />" +
                "手机号码：" + getUser(username).getPhoneNumber() + "<br />" +
                "等级：" + getUser(username).getLevel() + "<br />" +
                "积分：" + getUser(username).getPoint() + "<br />" +
                "注册日期：" + getUser(username).getSignupDateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日hh点mm分ss秒")));
        changePhoneNumberJL.setBounds(190, 155, 26, 16);
        changePhoneNumberJL.addMouseListener(this);
        changePhoneNumberJL.setForeground(Color.BLUE);
        getContentPane().add(changePhoneNumberJL);
        getContentPane().add(profileJL);
        aboutJM.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == 71) showAbout();
        else if (code == 27) {
            setVisible(false);
            new Menu(username);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == backJB || thing == backJMI) {
            setVisible(false);
            new Menu(username);
        } else if (thing == aboutJM) showAbout();
        else if (thing == changePhoneNumberJL) {
            setVisible(false);
            new ResetPhoneNumber(username);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == backJB) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else if (thing == changePhoneNumberJL) {
            changePhoneNumberJL.setText("<HTML><U>修改");
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == backJB) setCursor(Cursor.getDefaultCursor());
        else if (thing == changePhoneNumberJL) {
            changePhoneNumberJL.setText("修改");
            setCursor(Cursor.getDefaultCursor());
        }
    }
}
