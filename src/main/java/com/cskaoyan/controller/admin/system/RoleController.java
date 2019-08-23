package com.cskaoyan.controller.admin.system;

import com.cskaoyan.bean.admin.system.NewPermission;
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

    //这是查询权限的方法
    @GetMapping("admin/role/permissions")
    public ResponseVo permissions(int roleId){
        //查询所有的权限
        List<PermissionL1> permissionList = permissionsService.queryAllPermissions();
        //查询当前用户已经有的权限
        List<String> assignedPermissions = new ArrayList<>();
        assignedPermissions  = permissionsService.queryAssignPermissionByRoleId(roleId);
        //如果当前用户的权限是 * ，那么会返回一个 * ，我们实际需要的是所有权限的名字而不是* ，需要再次调用方法查询所有权限
        if (assignedPermissions.size()!=0 && "*".equals(assignedPermissions.get(0))){
            assignedPermissions = permissionsService.queryAllPermissionName();
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("assignedPermissions",assignedPermissions);
        map.put("systemPermissions",permissionList);
        return ResponseUtil.success(map);
    }

    //这是修改角色权限的方法
    @PostMapping("admin/role/permissions")
    public ResponseVo updatePermissions(@RequestBody HashMap<String,Object> paramMap){
        //首先获取一个角色id
        int roleId = (int) paramMap.get("roleId");
        //然后获取前端传递过来的，需要更新的权限名称name集合
        List<String> permissions = (List<String>) paramMap.get("permissions");
        //然后先删除这个角色的所有权限字段
        permissionsService.deletePermissionsById(roleId);
        //首先可以判断这个要更新的权限集合，是否包含所有的权限
        //从permissionList表中查询所有的权限名称name
        List<String> allPermissionName = permissionsService.queryAllPermissionName();
        //如果包含所有权限的话，直接把要更新的权限名称集合的内容换成一个 * 就可以了
        if (permissions.containsAll(allPermissionName)) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("*");
            permissions = arrayList;
        }
        //如果不包含所有权限，就更新对应的权限名称name，把集合中的权限name都和这个roleID绑定
        //如果需要更新的权限集合为空，就不需要执行这个方法了。因为刚刚已经把所有权限删除了
        if (permissions.size()!=0){
            /**
             * 但是我后来又发现这样授权无法被前端识别，经过查验前端代码，我发现它是通过权限的api识别的
             * 所以我修改了permission表中permission字段的格式，改成了api的格式
             * 然后新增了一个字段name，存储之前的permission字段
             * */
             //需要根据前端传递过来的permissions，去找list表中这些权限名称对应的api
            //然后把这两个字段封装到同一个JavaBean中
            List<NewPermission> newPermissions = new ArrayList<>();
            if (permissions.get(0).equals("*")){
                NewPermission permission = new NewPermission();
                permission.setApi("*");
                permission.setName("*");
                newPermissions.add(permission);
            }else {
                for (String name : permissions){
                    NewPermission newPermission = permissionsService.queryApiByPermissionName(name);
                    newPermissions.add(newPermission);
                }
            }

            //，然后把apis集合插入到permissions表中的对应permission字段中
            permissionsService.insertPermissions(roleId,newPermissions);
        }
        return ResponseUtil.success();
    }
}
