import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：签到功能
/*
 * 原理：每次签到获取当前的日期及时间，若上次签到的年月日与这次的一致，则直接判定签到取失败；若不一致，则接着与上次签到的年月日进行对比，如果两次签到日期相差大于或等于1天，则这次签到成功。
 * 如果两次签到日期相差刚好为1天，则判定为连续签到，获得更多积分奖励。若两次签到日期相差大于1天，则连续签到天数重置为0。
 * */
public class Test {
    static LocalDateTime lastDateTime = LocalDateTime.of(2020, 3, 24, 0, 0, 0);
    static int succession = 0;
    static int point = 1;
    static int sumPoint = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = "3";
        while (!str.equals("0")) {
            System.out.println("输入1签到，输入0停止");
            str = input.next();
            if (str.equals("1")) {
                if (signIn())
                    System.out.printf("签到成功，已连续签到%d天，获得%d积分，目前积分%d\n", succession, point, sumPoint);
                else System.out.println("今天已经签到过了");
            }
        }
    }

    private static boolean signIn() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatToDate = DateTimeFormatter.ofPattern("dd");
        int current = Integer.parseInt(currentDateTime.format(formatToDate));
        int last = Integer.parseInt(lastDateTime.format(formatToDate));
        if (current == last) {
            return false;
        } else if (current - last == 1) {
            point += succession;
            succession++;
        } else {
            point = 1;
            succession = 1;
        }
        sumPoint += point;
        lastDateTime = lastDateTime.with(currentDateTime);
        return true;
    }
}
