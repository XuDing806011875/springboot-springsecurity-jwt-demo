package boss.portal.entity;

/**
 * create by
 *
 * @author dingxu
 * @date 2018/10/30 19:27
 */
public class RolePermit {
    private Integer id;
    private Integer roleId;
    private Integer permitId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermitId() {
        return permitId;
    }

    public void setPermitId(Integer permitId) {
        this.permitId = permitId;
    }
}
