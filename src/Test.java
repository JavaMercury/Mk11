import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test extends JFrame implements MouseListener {
    JLabel shit = new JLabel(new ImageIcon("image\\login\\显示密码按下.png"));
    JPasswordField jpf = new JPasswordField();
    public Test(){
        setLayout(null);
        setSize(500,500);
        shit.setBounds(0,0,18,29);
        getContentPane().add(shit);
        setVisible(true);
        shit.addMouseListener(this);
        jpf.setBounds(0,100,100,30);
        getContentPane().add(jpf);
        System.out.println(jpf.getEchoChar());
    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object thing=e.getSource();
        if(thing==shit) jpf.setEchoChar((char)0);
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
}
