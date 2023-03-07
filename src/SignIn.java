import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

//签到
public class SignIn extends Initializer {

    JMenu propertiesJM = new JMenu("选项");
    JMenuItem backJMI = new JMenuItem("返回主菜单");
    JButton signInJB = new JButton("签到");
    static int succession;
    static int point;
    static int sumPoint;
    JLabel successJL = new JLabel(String.format("签到成功，已连续签到%d天，获得%d积分，目前积分%d，等级%d", succession, point, sumPoint, level(sumPoint)));

    public SignIn(String username) {
        this.username = username;
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///计算用户等级
    private static int level(int sumPoint) {
        return 1 + sumPoint / 100;
    }

    ///签到
    private boolean signIn() {
        LocalDateTime currentLDT = LocalDateTime.now();
        long span = ChronoUnit.DAYS.between(lastLDT, currentLDT);
        if (span == 0) {
            return false;
        } else if (span == 1) {
            point += succession;
            succession++;
        } else {
            point = 1;
            succession = 1;
        }
        sumPoint += point;
        lastLDT = lastLDT.with(currentLDT);
        collectData();
        return true;
    }

    @Override
    void collectData() {
        User user = getUser(username);
        user.setLevel(level(sumPoint));
        user.setLastLDT(lastLDT);
        user.setPoint(sumPoint);
        user.setSuccession(succession);
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
        con.setBackground(Color.WHITE);
        signInJB.setBounds(95, 180, 150, 50);
        signInJB.addMouseListener(this);
        con.add(signInJB);

        successJL.setBounds(0, 130, 356, 30);
        con.add(successJL);
        successJL.setVisible(false);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        backJB.addKeyListener(this);
        con.add(backJB);
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
        if (code == 27) {
            setVisible(false);
            new MainMenu(username);
        } else if (code == 71) showAbout();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == backJMI || thing == backJB) {
            setVisible(false);
            new MainMenu(username);
        } else if (thing == signInJB) {
            if (signIn()) {
                successJL.setText(String.format("签到成功，已连续签到%d天，获得%d积分，目前积分%d，等级%d", succession, point, sumPoint, level(sumPoint)));
                successJL.setVisible(true);
            }
            signInJB.setText("今日已签到");
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == signInJB || thing == backJB) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == signInJB || thing == backJB) setCursor(Cursor.getDefaultCursor());
    }
}
