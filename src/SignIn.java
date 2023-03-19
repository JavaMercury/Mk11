import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

//签到
public class SignIn extends Initializer {

    static int succession;
    static int point;
    static int sumPoint;
    JMenu propertiesJM = new JMenu("选项");
    JMenuItem backJMI = new JMenuItem("返回主菜单");
    JButton signInJB = new JButton("签到");
    JLabel successJL = new JLabel(String.format("签到成功，已连续签到%d天，获得%d积分，目前积分%d，等级%d", succession, point, sumPoint, level(sumPoint)));

    public SignIn(String username) {
        this.username = username;
        initJFrame();
        initMenuBar();
        initContent();
        setResizable(false);
        setVisible(true);
    }

    ///计算用户等级
    private static int level(int sumPoint) {
        return 1 + sumPoint / 100;
    }

    ///签到
    private boolean signIn() throws IOException {
        LocalDateTime currentLDT = LocalDateTime.now();
        long span = ChronoUnit.DAYS.between(lastLDT, currentLDT);
        if (span == 0) {
            return false;
        } else if (span == 1) {
            point += succession;
            succession++;
        } else {
            point = 1;
            succession = 1;
        }
        sumPoint += point;
        lastLDT = lastLDT.with(currentLDT);
        saveData();
        return true;
    }

    void saveData() throws IOException {
        File file = new File("User\\" + username);
        decrypt(file, username);
        ArrayList<Integer> lineBytes = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile("Temp\\" + username, "rw");
        int totalBytes = 0;
        int b;
        while ((b = raf.read()) != -1) {
            totalBytes++;
            if (b == 13) {
                lineBytes.add(totalBytes++);
                raf.read();
            }
        }
        raf.seek(lineBytes.get(3) + 1);
        File tempTemp = new File("Temp\\" + username + "Temp");
        BufferedReader br = new BufferedReader(new FileReader(raf.getFD()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempTemp));
        String line;
        bw.write(point + "\r\n");
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        raf.seek(lineBytes.get(2));
        raf.write((point + "").getBytes());
        BufferedReader brTemp = new BufferedReader(new FileReader(tempTemp));
        long l = tempTemp.length();
        raf.setLength(raf.length() - l);
        raf.write("\r\n".getBytes());
        while ((line = brTemp.readLine()) != null) {
            raf.write(line.getBytes());
            raf.write("\r\n".getBytes());
        }
        brTemp.close();
        bw.close();
        br.close();
        raf.close();
        encrypt(new File("Temp\\" + username));
        /*if (!tempTemp.delete()) {
            System.out.println(username + "临时数据删除失败，程序紧急中止！");
            System.exit(-1);
        }
        if (!temp.delete()) {
            System.out.println(username + "数据删除失败，程序紧急中止！");
            System.exit(-1);
        }*/
    }

    @Override
    void collectData() {

    }

    @Override
    void initJFrame() {
        setLayout(null);
        setSize(356, 390);
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
        propertiesJM.add(backJMI);
        jmb.add(aboutJM);
        aboutJM.addMouseListener(this);
        propertiesJM.addMouseListener(this);
        backJMI.addMouseListener(this);
        setJMenuBar(jmb);
    }

    @Override
    void initContent() {
        con.setBackground(Color.WHITE);
        signInJB.setBounds(95, 180, 150, 50);
        signInJB.addMouseListener(this);
        con.add(signInJB);

        successJL.setBounds(0, 130, 356, 30);
        con.add(successJL);
        successJL.setVisible(false);

        backJB.setBounds(0, 0, 60, 30);
        backJB.addMouseListener(this);
        backJB.addKeyListener(this);
        con.add(backJB);
        aboutJM.addKeyListener(this);
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
        if (code == 27) {
            setVisible(false);
            new MainMenu(username);
        } else if (code == 71) showAbout();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == aboutJM) showAbout();
        else if (thing == backJMI || thing == backJB) {
            setVisible(false);
            new MainMenu(username);
        } else if (thing == signInJB) {
            try {
                if (signIn()) {
                    successJL.setText(String.format("签到成功，已连续签到%d天，获得%d积分，目前积分%d，等级%d", succession, point, sumPoint, level(sumPoint)));
                    successJL.setVisible(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            signInJB.setText("今日已签到");
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == signInJB || thing == backJB) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Object thing = mouseEvent.getSource();
        if (thing == signInJB || thing == backJB) setCursor(Cursor.getDefaultCursor());
    }
}
