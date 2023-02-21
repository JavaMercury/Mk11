import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

//斗地主游戏
//大集合装三个小集合，每个小集合装每个玩家的牌；地主牌不放入大集合中
//再定义三个集合装玩家打出的牌，从小集合中抽出
//最开始还有一个牌盒集合，装所有的牌
//每个玩家有倒计时，三个倒计时用数组管理
//按钮：抢地主/不抢->数组     出牌/不要->数组
//地主有一个图标

public class beatLordGame extends Initializer implements FocusListener {

    static HashMap<Integer, String> cardList = new HashMap<>();
    static ArrayList<Integer> serialList = new ArrayList<>();
    JLabel player1NameJL = new JLabel();
    JLabel player2NameJL = new JLabel("Mercury");
    JLabel player3NameJL = new JLabel("HG");
    JMenu propertiesJM = new JMenu("选项");
    JMenuItem exitJMI = new JMenuItem("退出游戏");
    JMenuItem replayJMI = new JMenuItem("重玩游戏");
    JLabel gameTitleJL = new JLabel("水银斗地主");
    JButton playJB = new JButton("开始游戏");
    JTextField player1CountDownJTF = new JTextField();
    JTextField player2CountDownJTF = new JTextField();
    JTextField player3CountDownJTF = new JTextField();
    //把三个玩家的倒计时放入一个数组中管理
    JButton[] countDowns = new JButton[3];
    //把玩家的牌的集合放入一个集合中
    ArrayList<TreeSet<Integer>> playerCardsList = new ArrayList<>();
    //以下三个集合存玩家打出的牌
    TreeSet<Integer> player1OutCard = new TreeSet<>();
    TreeSet<Integer> player2OutCard = new TreeSet<>();
    TreeSet<Integer> player3OutCard = new TreeSet<>();
    JButton ToBeLordJB = new JButton("抢地主");
    JButton notToBeLordJB = new JButton("不抢");
    //把抢地主和不抢的按钮放入一个数组中管理
    JButton[] beLordOrNot = new JButton[2];
    JButton outCardJB = new JButton("出牌");
    JButton passJB = new JButton("不要");
    //把出牌和不要的按钮放入一个数组中管理
    JButton[] cardOrNot = new JButton[2];

    ///准备牌
    static {
        String[] cardSuit = {"♦", "♣", "♠", "♥"};
        String[] cardNumber = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        int serial = 0;
        for (String n : cardNumber) {
            for (String s : cardSuit) {
                cardList.put(serial, s + n);
                serialList.add(serial);
                serial++;
            }
        }
        serialList.add(serial);
        cardList.put(serial++, "小王");
        serialList.add(serial);
        cardList.put(serial, "大王");
    }

    public beatLordGame(String username) {
        this.username = username;
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///准备工作
    private void prepare() {
        //洗牌
        Collections.shuffle(serialList);
        TreeSet<Integer> lordCard = new TreeSet<>();
        TreeSet<Integer> player1Card = new TreeSet<>();
        TreeSet<Integer> player2Card = new TreeSet<>();
        TreeSet<Integer> player3Card = new TreeSet<>();
        //发牌
        for (int i = 0; i < serialList.size(); i++) {
            if (i <= 2) {
                lordCard.add(serialList.get(i));
                continue;
            }
            player1Card.add(serialList.get(i++));
            player2Card.add(serialList.get(i++));
            player3Card.add(serialList.get(i));
        }
        //看牌
        seeCard(lordCard, "地主牌");
        seeCard(player1Card, "Apermesa");
        seeCard(player2Card, "Mercury");
        seeCard(player3Card, "HG");
        //加载用户图形
        player1NameJL.setVisible(true);
        player2NameJL.setVisible(true);
        player3NameJL.setVisible(true);
        player1CountDownJTF.setVisible(true);
        player2CountDownJTF.setVisible(true);
        player3CountDownJTF.setVisible(true);
    }

    ///看牌
    public void seeCard(TreeSet<Integer> card, String username) {
        System.out.print(username + ": ");
        for (Integer serial : card) {
            System.out.print(cardList.get(serial) + " ");
        }
        System.out.println();
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    @Override
    void collectData() {

    }

    @Override
    void initJFrame() {
        setLayout(null);
        setSize(1500, 1300);
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
        propertiesJM.add(exitJMI);
        propertiesJM.add(replayJMI);
        exitJMI.addMouseListener(this);
        replayJMI.addMouseListener(this);
        jmb.add(aboutJM);
        aboutJM.addMouseListener(this);
        propertiesJM.addMouseListener(this);
        setJMenuBar(jmb);
    }

    @Override
    void initContent() {
        con.setBackground(Color.WHITE);
        player1NameJL.setText(username);
        gameTitleJL.setBounds(550, 500, 1000, 100);
        Font gameTitleFont = new Font(null, Font.BOLD, 72);
        gameTitleJL.setFont(gameTitleFont);
        playJB.setBounds(700, 700, 100, 40);
        playJB.addMouseListener(this);
        con.add(gameTitleJL);
        con.add(playJB);
        player1NameJL.setBounds(50, 50, 200, 30);
        player2NameJL.setBounds(730, 1000, 200, 30);
        player3NameJL.setBounds(1250, 50, 200, 30);
        con.add(player1NameJL);
        con.add(player2NameJL);
        con.add(player3NameJL);
        player1NameJL.setVisible(false);
        player2NameJL.setVisible(false);
        player3NameJL.setVisible(false);
        player1CountDownJTF.setBounds(50, 90, 100, 30);
        player2CountDownJTF.setBounds(730, 1040, 100, 30);
        player3CountDownJTF.setBounds(1240, 90, 100, 30);
        player1CountDownJTF.setVisible(false);
        player2CountDownJTF.setVisible(false);
        player3CountDownJTF.setVisible(false);
        con.add(player1CountDownJTF);
        con.add(player2CountDownJTF);
        con.add(player3CountDownJTF);
        player1CountDownJTF.setEditable(false);
        player2CountDownJTF.setEditable(false);
        player3CountDownJTF.setEditable(false);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == exitJMI) {
            setVisible(false);
            new GamesMenu(username);
        } else if (thing == replayJMI) {
            setVisible(false);
            new beatLordGame(username);
        } else if (thing == playJB) {
            gameTitleJL.setVisible(false);
            playJB.setVisible(false);
            prepare();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
