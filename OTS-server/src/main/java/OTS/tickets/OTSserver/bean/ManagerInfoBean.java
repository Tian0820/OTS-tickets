package OTS.tickets.OTSserver.bean;

public class ManagerInfoBean {

    private int managerId;

    private String managerName;

    private String password;

    public ManagerInfoBean() {
    }

    public ManagerInfoBean(int managerId, String managerName, String password) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.password = password;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
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
