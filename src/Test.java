import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：签到功能
/*
 * 原理：每次签到获取当前的年月日，若上次签到的年月日与这次的一致，则直接判定签到失败；若不一致，则接着与上次签到的年月日进行对比，如果两次签到日期相差大于或等于1天，则这次签到成功。
 * 如果两次签到日期相差刚好为1天，则判定为连续签到，获得更多积分奖励。若两次签到日期相差大于1天，则连续签到天数重置为0。
 *
 * 增加签到功能后，User类可能会增加以下数据：积分数，上次签到时间，等级，连续签到天数
 * */
public class Test {
    //默认的上次签到时间，用于用户第一次签到
    static LocalDateTime lastLDT = LocalDateTime.of(2020, 3, 24, 0, 0, 0);
    //连续签到天数
    static int succession = 0;
    //单次签到获得的积分数
    static int point = 1;
    //总积分数
    static int sumPoint = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = "3";
        while (!str.equals("0")) {
            System.out.println("输入1签到，输入0停止");
            str = input.next();
            if (str.equals("1")) {
                if (signIn())
                    System.out.printf("签到成功，已连续签到%d天，获得%d积分，目前积分%d，目前等级%d\n", succession, point, sumPoint, level(sumPoint));
                else System.out.println("今天已经签到过了，明天再来吧！");
            }
        }
    }

    ///签到
    private static boolean signIn() {
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
        return true;
    }

    ///等级
    private static int level(int sumPoint) {
        return 1 + sumPoint / 100;
    }
}
