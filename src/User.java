import java.time.LocalDateTime;
import java.util.Objects;

//记录水银客户端的用户账户
public class User {
    private String username;
    private String password;
    private String phoneNumber;
    //积分
    private int point;
    //等级
    private int level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return point == user.point && level == user.level && succession == user.succession && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(lastLDT, user.lastLDT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, phoneNumber, point, level, succession, lastLDT);
    }

    //连续签到天数
    private int succession;
    //上次签到时间
    private LocalDateTime lastLDT;

    public User() {
    }

    public User(String username, String password, String phoneNumber, int point, int level, int succession, LocalDateTime lastLDT) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.level = level;
        this.succession = succession;
        this.lastLDT = lastLDT;
    }

    /**
     * 获取
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username
     */
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

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", phoneNumber = " + phoneNumber + ", point = " + point + ", level = " + level + ", succession = " + succession + ", lastLDT = " + lastLDT + "}";
    }
}
