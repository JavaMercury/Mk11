import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

//拼图小游戏
public class PuzzleGame extends Initializer implements Border {

    //管理图片数据
    static int[][] data = new int[4][4];

    //空白方块在二维数组中的位置
    static int x = 0;
    static int y = 0;
    int mouseClickCount = 0;
    //记录用户操作的步数
    int step = 0;
    //图片路径
    ArrayList<String> animalLibrary = new ArrayList<>();
    ArrayList<String> girlLibrary = new ArrayList<>();
    ArrayList<String> sportLibrary = new ArrayList<>();
    String defaultPath = "image\\animal\\animal1\\";
    String path = defaultPath;
    String chooseImage;
    JMenu changeImageJM = new JMenu("更换图片");
    JMenu changeMovePatternJM = new JMenu("更改移动模式");
    JMenuItem moveBlankJMI = new JMenuItem("移动空白方块");
    JMenuItem movePuzzleJMI = new JMenuItem("移动拼图（默认）");
    JMenuItem animalJMI = new JMenuItem("动物");
    JMenuItem girlJMI = new JMenuItem("美女");
    JMenuItem sportJMI = new JMenuItem("运动");
    JMenuItem logoutJMI = new JMenuItem("退出登录");
    JMenuItem exitGameJMI = new JMenuItem("退出游戏");
    JMenuItem replayJMI = new JMenuItem("重玩游戏");
    JMenu helpJM = new JMenu("帮助(H)");
    JMenu propertiesJM = new JMenu("选项");
    JMenuBar puzzleGameJMB = new JMenuBar();
    JLabel backgroundJL = new JLabel(new ImageIcon("image\\background.png"));
    private int MOVE_UP_LEFT_OR_Y = 1;
    private int MOVE_DOWN_RIGHT_OR_Y = -1;
    private int BOUNDS_UP_LEFT = 3;
    private int BOUNDS_DOWN_RIGHT = 0;

    public PuzzleGame(String username) {
        animalLibrary.add("image\\animal\\animal1\\");
        animalLibrary.add("image\\animal\\animal2\\");
        animalLibrary.add("image\\animal\\animal3\\");
        animalLibrary.add("image\\animal\\animal4\\");
        animalLibrary.add("image\\animal\\animal5\\");
        animalLibrary.add("image\\animal\\animal6\\");
        animalLibrary.add("image\\animal\\animal7\\");
        animalLibrary.add("image\\animal\\animal8\\");
        girlLibrary.add("image\\girl\\girl1\\");
        girlLibrary.add("image\\girl\\girl2\\");
        girlLibrary.add("image\\girl\\girl3\\");
        girlLibrary.add("image\\girl\\girl4\\");
        girlLibrary.add("image\\girl\\girl5\\");
        girlLibrary.add("image\\girl\\girl6\\");
        girlLibrary.add("image\\girl\\girl7\\");
        girlLibrary.add("image\\girl\\girl8\\");
        girlLibrary.add("image\\girl\\girl9\\");
        girlLibrary.add("image\\girl\\girl10\\");
        girlLibrary.add("image\\girl\\girl11\\");
        girlLibrary.add("image\\girl\\girl12\\");
        girlLibrary.add("image\\girl\\girl13\\");
        sportLibrary.add("image\\sport\\sport1\\");
        sportLibrary.add("image\\sport\\sport2\\");
        sportLibrary.add("image\\sport\\sport3\\");
        sportLibrary.add("image\\sport\\sport4\\");
        sportLibrary.add("image\\sport\\sport5\\");
        sportLibrary.add("image\\sport\\sport6\\");
        sportLibrary.add("image\\sport\\sport7\\");
        sportLibrary.add("image\\sport\\sport8\\");
        sportLibrary.add("image\\sport\\sport9\\");
        sportLibrary.add("image\\sport\\sport10\\");
        this.username = username;
        initJFrame();
        initMenuBar();
        initData();
        initContent();
        setVisible(true);
    }

    ///显示帮助
    private static void showHelp() {
        JDialog help = new JDialog();
        JLabel helpText = new JLabel("使用方向键或WASD控制方向，按住ctrl查看原图，可在选项中更改移动方式");
        help.setAlwaysOnTop(true);
        help.setTitle("怎么玩？");
        help.setIconImage(new ImageIcon("image\\login\\MkLogInBackground.jpg").getImage());
        helpText.setBounds(0, 0, 420, 80);
        help.getContentPane().add(helpText);
        help.setSize(420, 80);
        help.setAlwaysOnTop(true);
        help.setLocationRelativeTo(null);
        help.setVisible(true);
    }

    ///数据初始化
    private static void initData() {
        Random r = new Random();
        int[] arrayTemp = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        do {
            for (int i = 0; i < arrayTemp.length; i++) {
                int randomIndex = r.nextInt(16);
                int temp = arrayTemp[i];
                arrayTemp[i] = arrayTemp[randomIndex];
                arrayTemp[randomIndex] = temp;
            }
            for (int i = 0; i < arrayTemp.length; i++) {
                if (arrayTemp[i] == 0) {
                    x = i / 4;
                    y = i % 4;
                }
                data[i / 4][i % 4] = arrayTemp[i];
            }
        } while (!isPlayable());
    }

    ///计算拼图可解性
    private static boolean isPlayable() {
        int[] array = new int[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                array[index] = data[i][j];
            }
        }
        //记录逆序数
        int count = x + y + 2;
        int base = 1;
        for (int i : array) {
            for (int start2 = 1; start2 < array.length - base; start2++) {
                if (i > array[start2]) count++;
            }
            base++;
        }
        return count % 2 == 0;
    }

    @Override
    void collectData() {

    }

    ///窗口初始化
    @Override
    void initJFrame() {
        setSize(603, 680);
        setTitle("水银第十代 - 拼图小游戏");
        setIcon();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        addKeyListener(this);
    }

    ///菜单初始化
    @Override
    void initMenuBar() {
        getContentPane().setBackground(Color.WHITE);
        changeImageJM.add(animalJMI);
        changeImageJM.add(girlJMI);
        changeImageJM.add(sportJMI);
        propertiesJM.add(changeImageJM);
        propertiesJM.add(changeMovePatternJM);
        changeMovePatternJM.add(movePuzzleJMI);
        changeMovePatternJM.add(moveBlankJMI);
        propertiesJM.add(replayJMI);
        propertiesJM.add(exitGameJMI);
        propertiesJM.add(logoutJMI);
        puzzleGameJMB.add(propertiesJM);
        puzzleGameJMB.add(aboutJM);
        puzzleGameJMB.add(helpJM);
        aboutJM.addMouseListener(this);
        helpJM.addMouseListener(this);
        logoutJMI.addMouseListener(this);
        exitGameJMI.addMouseListener(this);
        replayJMI.addMouseListener(this);
        animalJMI.addMouseListener(this);
        girlJMI.addMouseListener(this);
        sportJMI.addMouseListener(this);
        setJMenuBar(puzzleGameJMB);
        backgroundJL.addMouseListener(this);
        changeMovePatternJM.addMouseListener(this);
        moveBlankJMI.addMouseListener(this);
        movePuzzleJMI.addMouseListener(this);
        backgroundJL.addKeyListener(this);
    }

    ///内容初始化
    @Override
    void initContent() {
        getContentPane().removeAll();
        if (victory()) {
            JLabel victory = new JLabel(new ImageIcon("image\\win.png"));
            victory.setBounds(203, 283, 197, 73);
            getContentPane().add(victory);
            if (step != 99999 && getUser(username).getPuzzleSteps() >= 0 && getUser(username).getPuzzleSteps() > step)
                getUser(username).setPuzzleSteps(step);
        }
        JLabel countStep = new JLabel("步数：" + step);
        countStep.setBounds(50, 30, 100, 20);
        getContentPane().add(countStep);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int number = data[i][j];
                ImageIcon icon = new ImageIcon(path + number + ".jpg");
                JLabel mainImage = new JLabel(icon);
                mainImage.setBounds(105 * i + 83, 105 * j + 134, 105, 105);
                mainImage.setBorder(new BevelBorder(BevelBorder.RAISED));
                getContentPane().add(mainImage);
            }
        }
        backgroundJL.setBounds(40, 40, 508, 560);
        getContentPane().add(backgroundJL);
        getContentPane().repaint();
        aboutJM.addKeyListener(this);
    }

    ///判断用户是否胜利
    boolean victory() {
        int index = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[j][i] != index) return false;
                index++;
                if (index == 16) return true;
            }
        }
        return true;
    }

    ///更换图片
    void changeImage() {
        switch (chooseImage) {
            case "animal":
                path = animalLibrary.get(new Random().nextInt(8));
                break;
            case "girl":
                path = girlLibrary.get(new Random().nextInt(10));
                break;
            case "sport":
                path = sportLibrary.get(new Random().nextInt(10));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    ///重玩
    void replay() {
        step = 0;
        initData();
        initContent();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing = e.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == replayJMI) replay();
        else if (thing == exitGameJMI) {
            setVisible(false);
            new Menu(username);
        } else if (thing == logoutJMI) {
            setVisible(false);
            new Login();
        } else if (thing == helpJM) {
            showHelp();
        } else if (thing == animalJMI) {
            chooseImage = "animal";
            changeImage();
            replay();
        } else if (thing == girlJMI) {
            chooseImage = "girl";
            changeImage();
            replay();
        } else if (thing == sportJMI) {
            chooseImage = "sport";
            changeImage();
            replay();
        } else if (thing == backgroundJL) mouseClickCount++;
        else if (thing == moveBlankJMI) {
            MOVE_UP_LEFT_OR_Y = -1;
            MOVE_DOWN_RIGHT_OR_Y = 1;
            BOUNDS_UP_LEFT = 0;
            BOUNDS_DOWN_RIGHT = 3;
        } else if (thing == movePuzzleJMI) {
            MOVE_UP_LEFT_OR_Y = 1;
            MOVE_DOWN_RIGHT_OR_Y = -1;
            BOUNDS_UP_LEFT = 3;
            BOUNDS_DOWN_RIGHT = 0;
        }
        if (mouseClickCount == 10) showHelp();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    ///用户按住不松
    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()) return;
        int code = e.getKeyCode();
        if (code == 17) {
            getContentPane().removeAll();
            JLabel fullImage = new JLabel(new ImageIcon(path + "all.jpg"));
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            fullImage.setBounds(83, 134, 420, 420);
            background.setBounds(40, 40, 508, 560);
            getContentPane().add(fullImage);
            getContentPane().add(background);
            getContentPane().repaint();
        }
    }

    ///用户键盘操作
    @Override
    public void keyReleased(KeyEvent e) {
        //左 37, 上 38, 右 39, 下 40, W 87, A 65, S 83, D 68, Esc 27, Enter 10, 小- 109, ctrl 17, G 71, H 72
        if (victory()) return;
        int code = e.getKeyCode();
        if (code == 37 || code == 65) {
            if (x != BOUNDS_UP_LEFT) {
                step++;
                data[x][y] = data[x + MOVE_UP_LEFT_OR_Y][y];
                data[x + MOVE_UP_LEFT_OR_Y][y] = 0;
                x += MOVE_UP_LEFT_OR_Y;
                initContent();
            }
        } else if (code == 38 || code == 87) {
            if (y != BOUNDS_UP_LEFT) {
                step++;
                data[x][y] = data[x][y + MOVE_UP_LEFT_OR_Y];
                data[x][y + MOVE_UP_LEFT_OR_Y] = 0;
                y += MOVE_UP_LEFT_OR_Y;
                initContent();
            }
        } else if (code == 39 || code == 68) {
            if (x != BOUNDS_DOWN_RIGHT) {
                step++;
                data[x][y] = data[x + MOVE_DOWN_RIGHT_OR_Y][y];
                data[x + MOVE_DOWN_RIGHT_OR_Y][y] = 0;
                x += MOVE_DOWN_RIGHT_OR_Y;
                initContent();
            }
        } else if (code == 40 || code == 83) {
            if (y != BOUNDS_DOWN_RIGHT) {
                step++;
                data[x][y] = data[x][y + MOVE_DOWN_RIGHT_OR_Y];
                data[x][y + MOVE_DOWN_RIGHT_OR_Y] = 0;
                y += MOVE_DOWN_RIGHT_OR_Y;
                initContent();
            }
        } else if (code == 17) initContent();
            //官方开挂键数字键盘“-”
        else if (code == 109) {
            step = 99999;
            data = new int[][]{
                    {1, 5, 9, 13},
                    {2, 6, 10, 14},
                    {3, 7, 11, 15},
                    {4, 8, 12, 0}
            };
            initContent();
        } else if (code == 27) {
            setVisible(false);
            new Menu(username);
        } else if (code == 71) showAbout();
        else if (code == 72) showHelp();
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

    }

    @Override
    public Insets getBorderInsets(Component c) {
        return null;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
