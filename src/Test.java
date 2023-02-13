import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：签到功能
/*
 * 原理：每次签到获取当前的时间以及当前的day of month，若上次签到的day of month与这次的一致，则直接判定签到取消；接着与上次签到的时间进行对比，如果两次签到时间相差大于24小时，则这次签到成功。
 * 如果两次签到时间小于48小时，则判定为连续签到。若两次签到时间大于48小时，则连续签到天数重置为0
 * */
public class Test {
    static LocalDateTime lastSignIn = LocalDateTime.of(2020, 3, 24, 0, 0);
    ChronoUnit cu;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str;
        System.out.println("输入1签到");
        str = input.next();
        if (str.equals("1")) {
            if (signIn()) System.out.println("签到成功");
            else System.out.println("今天已经签到过了");
        }
    }

    private static boolean signIn() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd");
        if (ldt.format(formatDate).equals(lastSignIn.format(formatDate))) return false;
        return true;
    }
}
