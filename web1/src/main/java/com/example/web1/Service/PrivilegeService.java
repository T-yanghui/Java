package com.example.web1.Service;

import com.example.web1.DAO.PrivilegeDAO;
import com.example.web1.moudle.Privilege;

import java.util.List;

public class PrivilegeService {
    PrivilegeDAO privilegeDAO = new PrivilegeDAO();

    //添加权限
    public void addPrivilege(Privilege privilege){
        privilegeDAO.addPrivilege(privilege);
    }

    //删除权限
    public void deletePrivilege(Privilege privilege){
        privilegeDAO.deletePrivilege(privilege);
    }

    //由Pri_ID查找权限
    public Privilege findPrivilege(Privilege privilege){
        return privilegeDAO.findPrivilege(privilege);
    }

    //所有权限
    public List<Privilege> getAll(){
        return privilegeDAO.getAll();
    }
}
