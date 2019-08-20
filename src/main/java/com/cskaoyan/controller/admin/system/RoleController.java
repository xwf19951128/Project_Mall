package com.cskaoyan.controller.admin.system;

import com.cskaoyan.bean.admin.system.Role;
import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;
import com.cskaoyan.bean.admin.vo.DataBean;
import com.cskaoyan.bean.admin.vo.Options;
import com.cskaoyan.service.admin.system.PermissionsService;
import com.cskaoyan.service.admin.system.RoleService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("admin/role/permissions")
    public ResponseVo getPermissionsList(@RequestParam(value = "roleId") int roleId){
        ResponseVo responseVo = new ResponseVo();
        //根据给的id，查找出所有权限
        PermissionDateOne permissionDateOne= permissionsService.selectPermission(roleId);
        responseVo.setData(permissionDateOne);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

}
