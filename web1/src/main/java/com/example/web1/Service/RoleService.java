package com.example.web1.Service;

import com.example.web1.DAO.RoleDAO;
import com.example.web1.moudle.Privilege;
import com.example.web1.moudle.Role;

import java.util.List;

public class RoleService {
    RoleDAO roleDAO = new RoleDAO();
    //添加角色
    public void addRole(Role role){
        roleDAO.addRole(role);
    }

    //删除角色
    public void deleteRole(Role role){
        roleDAO.deleteRole(role);
    }

    //查找角色
    public Role findRole(Role role){
        return roleDAO.findRole(role);
    }

    //获取所有角色
    public List<Role> getAll(){
        return roleDAO.getAll();
    }

    //更新角色权限
    public void updatePrivilege(Role role, List<Privilege> privileges){
        roleDAO.updatePrivilege(role,privileges);
    }
}
