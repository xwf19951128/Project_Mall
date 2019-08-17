package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.bean.userManage.VipCollect;
import com.cskaoyan.service.userManage.VipCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VipCollectController {

    @Autowired
    VipCollectService vipCollectService;

    @RequestMapping("/admin/collect/list")
    @ResponseBody
    DataAndErr queryVipCollect(String userId, String valueId, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<VipCollect> collectItemAndTotal = new ItemAndTotal<>();
        List<VipCollect> collects;

        if ((userId == null || "".equals(userId)) && (valueId == null || "".equals(valueId))) {
            // 查询全部收藏
            collects = vipCollectService.queryVipCollect();
            // 封装item和total
            collectItemAndTotal.setItems(collects);
            collectItemAndTotal.setTotal(collects.size());
        } else if (userId == null || "".equals(userId)) {
            // 通过商品id进行查询
            collects = vipCollectService.queryVipCollectByValueId(valueId);
            // 封装item和total
            collectItemAndTotal.setItems(collects);
            collectItemAndTotal.setTotal(collects.size());
        } else if (valueId == null || "".equals(valueId)) {
            // 通过用户id进行查询
            collects = vipCollectService.queryVipCollectByUserId(userId);
            // 封装item和total
            collectItemAndTotal.setItems(collects);
            collectItemAndTotal.setTotal(collects.size());
        } else {
            // 通过用户id和商品id进行查询
            collects = vipCollectService.queryVipCollectByUserIdAndValueId(userId, valueId);
            // 封装item和total
            collectItemAndTotal.setItems(collects);
            collectItemAndTotal.setTotal(collects.size());
        }

        // 封装data、errMsg、errno
        dataAndErr.setData(collectItemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }
}
