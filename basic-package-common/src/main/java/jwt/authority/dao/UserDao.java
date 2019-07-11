package jwt.authority.dao;

import jwt.authority.entity.SysRole;
import jwt.authority.entity.SysUser;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public SysUser findByUsername(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername(userName);
        sysUser.setPassword("123");
        List<SysRole> sysRoleList = new ArrayList<>();
        SysRole sysRole = new SysRole();
        sysRole.setId(1L);
        sysRole.setName("ADMIN");
        sysRoleList.add(sysRole);

        SysRole sysRole1 = new SysRole();
        sysRole1.setId(2L);
        sysRole1.setName("ACTUATOR");
        sysRoleList.add(sysRole1);

        sysUser.setRoles(sysRoleList);
        return sysUser;
    }
}
