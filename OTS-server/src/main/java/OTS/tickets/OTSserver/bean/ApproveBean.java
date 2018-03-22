package OTS.tickets.OTSserver.bean;

public class ApproveBean {

    private int approvalId;

    private String approvalResult;

    public ApproveBean() {
    }

    public ApproveBean(int approvalId, String approvalResult) {
        this.approvalId = approvalId;
        this.approvalResult = approvalResult;
    }

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }
}
