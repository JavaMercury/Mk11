import java.io.Serializable;
import java.time.LocalDateTime;

//记录水银客户端的用户账户
public class User implements Serializable {

    //固定版本号用于序列化
    private static final long serialVersionUID = 114514L;
    private String username;
    private String password;
    private String phoneNumber;
    //积分
    private int point;
    //等级
    private int level;
    //连续签到天数
    private int succession;
    //上次签到时间
    private LocalDateTime lastLDT;
    //签到时间
    private LocalDateTime signupDateTime;
    //拼图小游戏通关最佳步数，初始为0
    private int puzzleSteps;

    User() {

    }

    public User(String username, String password, String phoneNumber, int point, int level, int succession, LocalDateTime lastLDT, LocalDateTime signupDateTime, int puzzleSteps) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.level = level;
        this.succession = succession;
        this.lastLDT = lastLDT;
        this.signupDateTime = signupDateTime;
        this.puzzleSteps = puzzleSteps;
    }

    /**
     * 获取
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取
     *
     * @return point
     */
    public int getPoint() {
        return point;
    }

    /**
     * 设置
     *
     * @param point
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * 获取
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取
     *
     * @return succession
     */
    public int getSuccession() {
        return succession;
    }

    /**
     * 设置
     *
     * @param succession
     */
    public void setSuccession(int succession) {
        this.succession = succession;
    }

    /**
     * 获取
     *
     * @return lastLDT
     */
    public LocalDateTime getLastLDT() {
        return lastLDT;
    }

    /**
     * 设置
     *
     * @param lastLDT
     */
    public void setLastLDT(LocalDateTime lastLDT) {
        this.lastLDT = lastLDT;
    }

    /**
     * 获取
     *
     * @return signupDateTime
     */
    public LocalDateTime getSignupDateTime() {
        return signupDateTime;
    }

    public void setSignupDateTime(LocalDateTime signupDateTime) {
        this.signupDateTime = signupDateTime;
    }

    /**
     * 获取
     *
     * @return puzzleSteps
     */
    public int getPuzzleSteps() {
        return puzzleSteps;
    }

    public void setPuzzleSteps(int puzzleSteps) {
        this.puzzleSteps = puzzleSteps;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", phoneNumber = " + phoneNumber + ", point = " + point + ", level = " + level + ", succession = " + succession + ", lastLDT = " + lastLDT + ", signupDateTime = " + signupDateTime + ", puzzleSteps = " + puzzleSteps + "}";
    }
}
