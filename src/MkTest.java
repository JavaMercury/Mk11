import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

//此类用于各种疑难理论测试，成功后可使用branch功能进行实际测试
//现在正在测试：
public class MkTest {
    //默认的上次签到时间，用于用户第一次签到
    static LocalDateTime lastLDT = LocalDateTime.of(2020, 3, 24, 0, 0, 0);
    //连续签到天数
    static int succession = 0;
    //单次签到获得的积分数
    static int point = 1;
    //总积分数
    static int sumPoint = 0;

    public static void main(String[] args) {
        new beatLordGame("Apermesa");
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
