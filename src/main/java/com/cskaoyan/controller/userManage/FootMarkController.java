package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.FootMark;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.bean.userManage.VipCollect;
import com.cskaoyan.service.userManage.FootMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FootMarkController {

    @Autowired
    FootMarkService footMarkService;

    // 查询所有用户的地址信息
    @RequestMapping("/admin/footprint/list")
    @ResponseBody
    DataAndErr queryFootMark(String userId, String goodsId, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<FootMark> itemAndTotal = new ItemAndTotal<>();
        List<FootMark> footMarks;

        if ((userId == null || "".equals(userId)) && (goodsId == null || "".equals(goodsId))) {
            // 查询全部足迹
            footMarks = footMarkService.queryFootMark();
            // 封装item和total
            itemAndTotal.setItems(footMarks);
            itemAndTotal.setTotal(footMarks.size());
        } else if (userId == null || "".equals(userId)) {
            // 通过商品id进行查询
            footMarks = footMarkService.queryFootMarkByGoodsId(goodsId);
            // 封装item和total
            itemAndTotal.setItems(footMarks);
            itemAndTotal.setTotal(footMarks.size());
        } else if (goodsId == null || "".equals(goodsId)) {
            // 通过用户id进行查询
            footMarks = footMarkService.queryFootMarkByUserId(userId);
            // 封装item和total
            itemAndTotal.setItems(footMarks);
            itemAndTotal.setTotal(footMarks.size());
        } else {
            // 通过用户id和商品id进行查询
            footMarks = footMarkService.queryFootMarkByUserIdAndGoodsId(userId, goodsId);
            // 封装item和total
            itemAndTotal.setItems(footMarks);
            itemAndTotal.setTotal(footMarks.size());
        }

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }
}
