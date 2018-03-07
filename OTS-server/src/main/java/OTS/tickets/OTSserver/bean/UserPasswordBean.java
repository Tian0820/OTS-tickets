package OTS.tickets.OTSserver.bean;

public class UserPasswordBean {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    public UserPasswordBean() {

    }

    public UserPasswordBean(String email, String password) {
        this.email = email;
        this.password = password;
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
}
