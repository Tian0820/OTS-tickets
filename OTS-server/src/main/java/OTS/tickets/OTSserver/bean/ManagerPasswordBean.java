package OTS.tickets.OTSserver.bean;

public class ManagerPasswordBean {

    private String managerName;

    private String password;

    public ManagerPasswordBean() {
    }

    public ManagerPasswordBean(String managerName, String password) {
        this.managerName = managerName;
        this.password = password;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
