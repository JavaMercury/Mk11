import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

//斗地主游戏
public class beatLordGame extends Initializer implements FocusListener {

    static HashMap<Integer, String> cardList = new HashMap<>();
    static ArrayList<Integer> serialList = new ArrayList<>();

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
        //洗牌
        Collections.shuffle(serialList);
        TreeSet<Integer> lordCard = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        //发牌
        for (int i = 0; i < serialList.size(); i++) {
            if (i <= 2) {
                lordCard.add(serialList.get(i));
                continue;
            }
            player1.add(serialList.get(i++));
            player2.add(serialList.get(i++));
            player3.add(serialList.get(i));
        }
        //看牌
        seeCard(lordCard, "地主牌");
        seeCard(player1, "Apermesa");
        seeCard(player2, "Mercury");
        seeCard(player3, "HG");
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

    }

    @Override
    void initMenuBar() {

    }

    @Override
    void initContent() {

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
