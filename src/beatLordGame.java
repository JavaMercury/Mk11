import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

//斗地主游戏
public class beatLordGame extends Initializer implements FocusListener{

    static ArrayList<String> cardList = new ArrayList<>();

    ///准备牌
    static {
        String[] cardSuit = {"♦", "♣", "♠", "♥"};
        String[] cardNumber = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (String s : cardSuit) {
            for (String n : cardNumber) {
                cardList.add(s + n);
            }
        }
        cardList.add("小王");
        cardList.add("大王");
    }

    public beatLordGame(String username) {
        this.username = username;
        //洗牌
        Collections.shuffle(cardList);
        ArrayList<String> lordCard = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();
        //发牌
        for (int i = 0; i < cardList.size(); i++) {
            if (i <= 2) {
                lordCard.add(cardList.get(i));
                continue;
            }
            player1.add(cardList.get(i++));
            player2.add(cardList.get(i++));
            player3.add(cardList.get(i));
        }
        //看牌
        System.out.println("地主牌：" + lordCard);
        System.out.println(username + ": " + player1);
        System.out.println("玩家2：" + player2);
        System.out.println("玩家3：" + player3);
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
