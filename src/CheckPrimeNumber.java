import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CheckPrimeNumber extends Initializer implements MouseListener, FocusListener, KeyListener {

    JMenu about = new JMenu("关于");
    JMenu properties = new JMenu("选项");
    JMenuItem exit = new JMenuItem("退出质数判断器");
    JMenuItem logout = new JMenuItem("退出登录");
    JTextField inputJTF = new JTextField();
    JButton submit = new JButton("确定");
    String input;
    JLabel wrongInputWarning = new JLabel("输入有误，请重新输入");
    JLabel resultJL = new JLabel();
    String stateOne = "1既不是质数也不是合数";
    JLabel hintJL = new JLabel("判断质数，并给出合数的所有因子");

    CheckPrimeNumber() {
        setResizable(false);
        initJFrame();
        initMenuBar();
        initContent();
        setVisible(true);
    }

    ///判断质数
    void checkPrimeNumber() {
        int number;
        if (input.equals("") || notInteger(input)) {
            wrongInputWarning.setVisible(true);
            return;
        }
        number = Integer.parseInt(input);
        if (number <= 0) {
            wrongInputWarning.setVisible(true);
            return;
        }
        if (number == 1) {
            resultJL.setText(stateOne);
            wrongInputWarning.setVisible(false);
            resultJL.setVisible(true);
        }
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        ArrayList<Integer> arr = new ArrayList<>();
        int count = 0;
        boolean boolNumber = true;
        for (int i = number - 1; i > 1; i--) {
            if (number % i == 0) {
                boolNumber = false;
                int subNumber = number / i;
                arr.add(subNumber);
                count++;
            }
        }
        if (boolNumber) {
            result.append(String.format("%d是质数", number));
        } else {
            result.append(input).append("不是质数，而且它有").append(count).append("个因子（除去1与").append(input).append("），分别如下：<br />");
            for (int index = 0; index < arr.size(); index++) {
                if (arr.get(index) <= arr.get(count - index - 1))
                    result.append(String.format("%d    *    %d<br />", arr.get(index), arr.get(count - index - 1)));
                else break;
            }
        }
        resultJL.setText(result.toString());
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
        setTitle("水银第十代 质数判断器");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        inputJTF.setBounds(100, 100, 200, 30);
        submit.setBounds(100, 200, 70, 30);
        wrongInputWarning.setBounds(100, 130, 200, 50);
        resultJL.setBounds(100, 300, 400, 200);
        submit.addMouseListener(this);
        inputJTF.addFocusListener(this);
        wrongInputWarning.setVisible(false);
        resultJL.setVisible(false);
        inputJTF.setText("请输入一个正整数");
        getContentPane().add(inputJTF);
        getContentPane().add(submit);
        getContentPane().add(wrongInputWarning);
        getContentPane().add(resultJL);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        getContentPane().add(backJB);
        inputJTF.addKeyListener(this);
        submit.addKeyListener(this);
        backJB.addKeyListener(this);
        wrongInputWarning.setForeground(Color.RED);
        hintJL.setBounds(100, 70, 200, 30);
        getContentPane().add(hintJL);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == submit) {
            input = inputJTF.getText();
            checkPrimeNumber();
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
        String temp = inputJTF.getText();
        if (temp.equals("请输入一个正整数")) {
            inputJTF.setText("");
            inputJTF.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        String temp = inputJTF.getText();
        if (temp.equals("")) {
            inputJTF.setForeground(Color.GRAY);
            inputJTF.setText("请输入一个正整数");
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
            input = inputJTF.getText();
            checkPrimeNumber();
        } else if (code == 27) {
            setVisible(false);
            new Menu(username);
        }
    }
}
