import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：
public class MkTest {
    public static void main(String[] args) throws IOException {
        Initializer i = new Initializer() {
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
            void initContent() throws IOException {

            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        };
        i.decrypt(new File("User\\aperture"), "aperture");
    }
}
