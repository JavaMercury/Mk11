import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GetRandomNumber extends Initializer implements MouseListener, FocusListener, KeyListener {

    JMenu about = new JMenu("关于");
    JMenu properties = new JMenu("选项");
    JMenuItem exit = new JMenuItem("退出随机数生成器");
    JMenuItem logout = new JMenuItem("退出登录");
    JTextField inputMinimumJTF = new JTextField();
    JTextField inputMaximumJTF = new JTextField();
    JButton submit = new JButton("确定");
    String inputMin;
    String inputMax;
    String warning;
    JLabel warningJL = new JLabel(warning);
    String warningDefault = "输入有误，请重新输入";
    String warningInputGreater = "最大值必须大于最小值，请重新输入";
    String warningInputPositive = "请输入正数";
    JLabel resultJL = new JLabel();
    JLabel hintJL = new JLabel("在指定范围内生成随机整数");

    GetRandomNumber() {
        setResizable(false);
        initJFrame();
        initMenuBar();
        initContent();
        setVisible(true);
    }

    ///获取随机数
    void getRandomNumber() {
        Random r = new Random();
        if (notInteger(inputMax) || notInteger(inputMin) || inputMin.equals("") || inputMax.equals("")) {
            warning = warningDefault;
            warningJL.setText(warning);
            warningJL.setVisible(true);
            return;
        }
        if (Integer.parseInt(inputMin) < 0) {
            warning = warningInputPositive;
            warningJL.setText(warning);
            warningJL.setVisible(true);
            return;
        }
        if (Integer.parseInt(inputMin) >= Integer.parseInt(inputMax)) {
            warning = warningInputGreater;
            warningJL.setText(warning);
            warningJL.setVisible(true);
            return;
        }
        int randomNumber;
        warningJL.setVisible(false);
        randomNumber = r.nextInt(Integer.parseInt(inputMax) - Integer.parseInt(inputMin) + 1) + Integer.parseInt(inputMin);
        resultJL.setText("本次结果为 " + randomNumber);
        resultJL.setVisible(true);
    }

    @Override
    void collectData() {

    }

    ///窗口初始化
    @Override
    void initJFrame() {
        setLayout(null);
        setSize(640, 648);
        setIcon();
        setTitle("水银第十代 随机数生成器");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
    }

    ///菜单初始化
    @Override
    void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        properties.add(exit);
        properties.add(logout);
        menuBar.add(properties);
        menuBar.add(about);
        about.addMouseListener(this);
        exit.addMouseListener(this);
        logout.addMouseListener(this);
        setJMenuBar(menuBar);
    }

    ///内容初始化
    @Override
    void initContent() {
        getContentPane().setBackground(Color.WHITE);
        inputMinimumJTF.setBounds(100, 100, 100, 30);
        inputMaximumJTF.setBounds(250, 100, 100, 30);
        submit.setBounds(100, 200, 70, 30);
        warningJL.setBounds(100, 130, 200, 50);
        resultJL.setBounds(100, 300, 200, 30);
        submit.addMouseListener(this);
        warningJL.setVisible(false);
        resultJL.setVisible(false);
        inputMinimumJTF.setText("范围最小值");
        inputMaximumJTF.setText("范围最大值");
        getContentPane().add(inputMinimumJTF);
        getContentPane().add(inputMaximumJTF);
        getContentPane().add(submit);
        getContentPane().add(warningJL);
        getContentPane().add(resultJL);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        inputMinimumJTF.addFocusListener(this);
        inputMaximumJTF.addFocusListener(this);
        inputMinimumJTF.addKeyListener(this);
        inputMaximumJTF.addKeyListener(this);
        hintJL.setBounds(100, 70, 200, 30);
        getContentPane().add(hintJL);
        submit.addKeyListener(this);
        backJB.addKeyListener(this);
        warningJL.setForeground(Color.RED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == submit) {
            inputMin = inputMinimumJTF.getText();
            inputMax = inputMaximumJTF.getText();
            getRandomNumber();
        } else if (thing == about) showAbout();
        else if (thing == exit || thing == backJB) {
            setVisible(false);
            new Menu(username);
        } else if (thing == logout) {
            setVisible(false);
            new Login();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submit) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == backJB || thing == submit) setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (inputMinimumJTF.getText().equals("范围最小值") || inputMaximumJTF.getText().equals("范围最大值")) {
            inputMinimumJTF.setText("");
            inputMaximumJTF.setText("");
            inputMinimumJTF.setForeground(Color.BLACK);
            inputMaximumJTF.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (inputMinimumJTF.getText().equals("") && inputMaximumJTF.getText().equals("")) {
            inputMinimumJTF.setForeground(Color.GRAY);
            inputMaximumJTF.setForeground(Color.GRAY);
            inputMinimumJTF.setText("范围最小值");
            inputMaximumJTF.setText("范围最大值");
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
            inputMin = inputMinimumJTF.getText();
            inputMax = inputMaximumJTF.getText();
            getRandomNumber();
        } else if (code == 27) {
            setVisible(false);
            new Menu(username);
        }
    }
}
