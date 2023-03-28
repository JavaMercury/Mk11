import javax.swing.*;
import java.awt.*;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：
public class MkTest extends JFrame {
    public MkTest() {
        super("Scrollable JLabel");
        JLabel label = new JLabel();
        label.setText("This is a very long text that will not fit in the label.");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        add(scrollPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MkTest();
    }
}
