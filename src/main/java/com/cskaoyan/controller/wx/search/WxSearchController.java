package com.cskaoyan.controller.wx.search;

import com.cskaoyan.bean.wx.search.SearchIndex;
import com.cskaoyan.service.wx.search.WxSearchService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx")
public class WxSearchController {
    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("/search/index")
    public ResponseVo index(HttpServletRequest request){
        //前端写了一个token放在请求头中
        String tokenKey = request.getHeader("X-Litemall-Token");
        //通过请求头获得userId，进而可以获得一切关于user的信息
        Integer userId = UserTokenManager.getUserId(tokenKey);

        //这是要查询和搜索相关的数据keyword，分别是：
        //默认值、历史记录、热值
        SearchIndex searchIndex = wxSearchService.querySearchIndex();
        return ResponseUtil.success(searchIndex);
    }

    @RequestMapping(value = "/goods/list",params = {"keyword"})
    public ResponseVo goodsList(){
        return null;
    }
}
