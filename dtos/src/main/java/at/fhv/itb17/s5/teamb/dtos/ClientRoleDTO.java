package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class ClientRoleDTO implements Serializable {

    private String roleName;
    private Boolean mayReadMsg;
    private Boolean mayWriteMsg;
    private int priority;

    public ClientRoleDTO() {
    }

    public ClientRoleDTO(String roleName, Boolean mayReadMsg, Boolean mayWriteMsg, int priority) {
        this.roleName = roleName;
        this.mayReadMsg = mayReadMsg;
        this.mayWriteMsg = mayWriteMsg;
        this.priority = priority;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getMayReadMsg() {
        return mayReadMsg;
    }

    public void setMayReadMsg(Boolean mayReadMsg) {
        this.mayReadMsg = mayReadMsg;
    }

    public Boolean getMayWriteMsg() {
        return mayWriteMsg;
    }

    public void setMayWriteMsg(Boolean mayWriteMsg) {
        this.mayWriteMsg = mayWriteMsg;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
