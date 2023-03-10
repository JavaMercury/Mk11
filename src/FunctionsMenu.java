import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class FunctionsMenu extends Initializer implements FocusListener {
    public FunctionsMenu(String username) {
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

    JButton changeNumberSystemJB = new JButton("进制转换工具");
    JButton checkPrimeNumberJB = new JButton("质数判断器");
    JButton getRandomNumberJB = new JButton("随机数生成器");

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

    @Override
    void initMenuBar() {
        JMenuBar jmb = new JMenuBar();
        jmb.add(aboutJM);
        setJMenuBar(jmb);
    }

    @Override
    void initContent() {
        changeNumberSystemJB.setBounds(95, 20, 150, 50);
        checkPrimeNumberJB.setBounds(95, 100, 150, 50);
        getRandomNumberJB.setBounds(95, 180, 150, 50);
        changeNumberSystemJB.addMouseListener(this);
        checkPrimeNumberJB.addMouseListener(this);
        getRandomNumberJB.addMouseListener(this);
        con.add(changeNumberSystemJB);
        con.add(checkPrimeNumberJB);
        con.add(getRandomNumberJB);
        aboutJM.addMouseListener(this);
        changeNumberSystemJB.addKeyListener(this);
        checkPrimeNumberJB.addKeyListener(this);
        getRandomNumberJB.addKeyListener(this);
        changeNumberSystemJB.addFocusListener(this);
        checkPrimeNumberJB.addFocusListener(this);
        getRandomNumberJB.addFocusListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    int focusSelect;

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == 71) showAbout();
        else if (code == 27) {
            setVisible(false);
            new MainMenu(username);
        } else if (code == 10) {
            switch (focusSelect) {
                case 1: {
                    setVisible(false);
                    new ChangeNumberSystem(username);
                    break;
                }
                case 2: {
                    setVisible(false);
                    new CheckPrimeNumber();
                    break;
                }
                case 3: {
                    setVisible(false);
                    new GetRandomNumber();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == changeNumberSystemJB) {
            setVisible(false);
            new ChangeNumberSystem(username);
        } else if (thing == checkPrimeNumberJB) {
            setVisible(false);
            new CheckPrimeNumber();
        } else if (thing == getRandomNumberJB) {
            setVisible(false);
            new GetRandomNumber();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == changeNumberSystemJB || thing == checkPrimeNumberJB || thing == getRandomNumberJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == changeNumberSystemJB || thing == checkPrimeNumberJB || thing == getRandomNumberJB)
            setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        Object thing = focusEvent.getSource();
        if (thing == changeNumberSystemJB) {
            focusSelect = 1;
        } else if (thing == checkPrimeNumberJB) {
            focusSelect = 2;
        } else if (thing == getRandomNumberJB) {
            focusSelect = 3;
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }
}
