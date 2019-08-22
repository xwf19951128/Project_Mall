package com.cskaoyan.controller.admin.system;

import com.cskaoyan.bean.admin.system.PermissionL1;
import com.cskaoyan.bean.admin.system.Role;

import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;
import com.cskaoyan.bean.admin.vo.DataBean;
import com.cskaoyan.bean.admin.vo.Options;
import com.cskaoyan.service.admin.system.PermissionsService;

import com.cskaoyan.service.admin.system.RoleService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:38 2019/8/16
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionsService permissionsService;

    /**
     * 查询角色管理，以及列表展示
     * @param page
     * @param limit
     * @param name
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping("admin/role/list")
    public ResponseVo listRoles(int page, int limit, String name, String sort, String order){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Role> roleData = roleService.selectRoles(page,limit,name,sort,order);
        responseVo.setData(roleData);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return  responseVo;
    }

    /**
     * 查询角色options
     * @return
     */

    @RequestMapping("admin/role/options")
    public ResponseVo ListOptions(){
        List<Options> optionData = roleService.selectOptions();
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(optionData);
        return responseVo;
    }

    @RequestMapping("admin/role/create")
    public ResponseVo addRole(@RequestBody Role role){
        ResponseVo responseVo = new ResponseVo();
        role.setUpdateTime(new Date());
        role.setAddTime(new Date());
        role.setEnabled(true);
        role.setDeleted(false);
        int insert= roleService.insertRole(role);
        if(insert!=0){
            responseVo.setData(role);
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
        }
        return responseVo;
    }

    @RequestMapping("admin/role/update")
    public ResponseVo updateRole(@RequestBody Role role){
        ResponseVo responseVo = new ResponseVo();
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        int update =roleService.updateRole(role);
        if(update!=0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(update);
        }
        return responseVo;
    }
    @RequestMapping("admin/role/delete")
    public ResponseVo deleteRole(@RequestBody Role role){
        ResponseVo responseVo = new ResponseVo();
        int i= roleService.deleteRole(role);
        if(i!=0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(i);
        }
        return responseVo;
    }

    @RequestMapping("admin/role/permissions")
    public ResponseVo permissions(String roleId){
        //查询所有的权限
        List<PermissionL1> permissionList = permissionsService.queryAllPermissions();
        //查询当前用户已经有的权限
        List<String> assignedPermissions = new ArrayList<>();
        assignedPermissions  = permissionsService.queryAssignPermissionByRoleId(roleId);
        //如果当前用户的权限是 * ，那么会返回一个 * ，我们实际需要的是所有权限的名字而不是* ，需要再次调用方法查询所有权限
        if ("*".equals(assignedPermissions.get(0))){
            assignedPermissions = permissionsService.queryAllPermissionName();
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("assignedPermissions",assignedPermissions);
        map.put("systemPermissions",permissionList);
        return ResponseUtil.success(map);
    }
}
