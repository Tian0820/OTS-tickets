package OTS.tickets.OTSserver.bean;

public class UserInfoBean {

    /**
     *
     */
    private int userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户等级
     */
    private int level;

    /**
     * 用户积分
     */
    private double point;

    /**
     * 累计积分
     */
    private double accumulativePoint;

    /**
     * 用户余额
     */
    private double balance;

    /**
     * 总消费
     */
    private double consume;

    public UserInfoBean() {
    }

    public UserInfoBean(int userId, String username, String email, String password, String phone, int level, double point, double accumulativePoint, double balance, double consume) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.level = level;
        this.point = point;
        this.accumulativePoint = accumulativePoint;
        this.balance = balance;
        this.consume = consume;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAccumulativePoint() {
        return accumulativePoint;
    }

    public void setAccumulativePoint(double accumulativePoint) {
        this.accumulativePoint = accumulativePoint;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }
}
