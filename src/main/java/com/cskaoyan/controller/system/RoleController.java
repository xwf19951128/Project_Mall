package com.cskaoyan.controller.system;

import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.bean.vo.Options;
import com.cskaoyan.service.system.RoleService;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:38 2019/8/16
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

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

    @RequestMapping("admin/role/options")
    public ResponseVo ListOptions(){
        List<Options> optionData = roleService.selectOptions();
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(optionData);
        return responseVo;
    }
}
