package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class ClientRole {

    @Id
    private String roleName;
    private Boolean mayReadMsg;
    private Boolean mayWriteMsg;
    private int priority;

    public ClientRole(String roleName, Boolean mayReadMsg, Boolean mayWriteMsg, int priority) {
        this.roleName = roleName;
        this.mayReadMsg = mayReadMsg;
        this.mayWriteMsg = mayWriteMsg;
        this.priority = priority;
    }

    public ClientRole() {
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

    @Override
    public String toString() {
        return "ClientRoles{" +
                "roleName='" + roleName + '\'' +
                ", mayReadMsg=" + mayReadMsg +
                ", mayWriteMsg=" + mayWriteMsg +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRole that = (ClientRole) o;
        return priority == that.priority &&
                roleName.equals(that.roleName) &&
                Objects.equals(mayReadMsg, that.mayReadMsg) &&
                Objects.equals(mayWriteMsg, that.mayWriteMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, mayReadMsg, mayWriteMsg, priority);
    }

    public static ClientRole calcEffectiveRole(List<ClientRole> roles) {
        roles.sort(Comparator.comparingInt(o -> -1 * o.priority));
        int prio = 0;
        Boolean read = null;
        Boolean write = null;
        StringBuilder sb = new StringBuilder();
        for (ClientRole role : roles) {
            sb.append(role.roleName).append(",");
            if (read == null) {
                read = role.mayReadMsg;
            }
            if (write == null) {
                write = role.mayWriteMsg;
            }
        }
        Optional<ClientRole> clientRoles = Optional.ofNullable(roles.get(0));
        if (clientRoles.isPresent()) {
            prio = Optional.of(clientRoles.get().getPriority()).orElse(0);
        }
        return new ClientRole("Effective of [" + sb.toString().substring(0, sb.length() - 1) + "]", read, write, prio);
    }

    public ClientRole cloneObj() {
        return new ClientRole(roleName, mayReadMsg, mayWriteMsg, priority);
    }
}
