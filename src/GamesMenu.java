import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GamesMenu extends Initializer implements FocusListener {
    JButton puzzleGameJB = new JButton("拼图小游戏");
    JButton beatLordGameJB = new JButton("斗地主");

    public GamesMenu(String username) {
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
        puzzleGameJB.setBounds(95, 20, 150, 50);
        beatLordGameJB.setBounds(95, 100, 150, 50);
        puzzleGameJB.addMouseListener(this);
        beatLordGameJB.addMouseListener(this);
        con.add(puzzleGameJB);
        con.add(beatLordGameJB);
        aboutJM.addMouseListener(this);
        puzzleGameJB.addKeyListener(this);
        beatLordGameJB.addKeyListener(this);
        puzzleGameJB.addFocusListener(this);
        beatLordGameJB.addFocusListener(this);
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
            new MainMenu(username);
        } else if (code == 10) {
            switch (focusSelect) {
                case 1: {
                    setVisible(false);
                    new PuzzleGame(username);
                    break;
                }
                case 2: {
                    setVisible(false);
                    new BeatLordGame(username);
                    break;
                }
            }
        }
    }
    int focusSelect;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == puzzleGameJB) {
            setVisible(false);
            new PuzzleGame(username);
        } else if (thing == beatLordGameJB) {
            setVisible(false);
            new BeatLordGame(username);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == puzzleGameJB || thing == beatLordGameJB)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == puzzleGameJB || thing == beatLordGameJB)
            setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        Object thing = focusEvent.getSource();
        if (thing == puzzleGameJB) {
            focusSelect = 1;
        } else if (thing == beatLordGameJB) {
            focusSelect = 2;
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }
}
