package models;


public class Role {
   int roleID;
   String roleName;
   boolean isSelected;
   
   public Role(int roleID) {
       this.roleID = roleID;
       this.roleName = null;
       this.isSelected = false;
   }

    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.isSelected = false;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
