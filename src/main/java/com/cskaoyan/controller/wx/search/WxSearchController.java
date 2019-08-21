package com.cskaoyan.controller.wx.search;

import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchGoods;
import com.cskaoyan.bean.wx.search.SearchIndex;
import com.cskaoyan.service.wx.search.WxSearchService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.*;

@RestController
@RequestMapping("/wx")
public class WxSearchController {

    //首先把HashMap设为静态的。用于防止“分类”按钮可能会对filterCategoryList造成影响
    static HashMap<String, Object> map = new HashMap<>();

    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("/search/index")
    public ResponseVo index(HttpServletRequest request){
        //前端写了一个token放在请求头中
        String tokenKey = request.getHeader("X-Litemall-Token");
        //通过请求头获得userId，进而可以获得一切关于user的信息
        Integer userId = UserTokenManager.getUserId(tokenKey);
        System.out.println(userId);
        //这是要查询和搜索相关的数据keyword，分别是：
        //默认值、历史记录、热值
        SearchIndex searchIndex = wxSearchService.querySearchIndex();
        return ResponseUtil.success(searchIndex);
    }

    @RequestMapping("/search/helper")
    public ResponseVo helper(String keyword){
        List<HistoryKeyword> keywords = wxSearchService.querySearchHelper(keyword);
        if (keywords.size()!=0){
            return ResponseUtil.success(keywords.get(0));
        }
       return ResponseUtil.success();
    }

    //这个方法仅仅用于搜索页面的商品展示，不用于其他商品的展示
    //所以我加了一个params="keyword"，只有请求包含keyword参数才会进入这个方法，否则不会进来
    @RequestMapping(value = "/goods/list",params = {"keyword"})
    public ResponseVo goodsList(String keyword,int page,int size,String sort,String order,int categoryId){
        /**
         * 这种做法有一个问题，那就是得到搜索结果后，如果选择某 “分类”还是会进入这个方法重新查找一次
         * 这样的话，filterCategoryList就会变成只有那个指定的分类。
         * 但是我们的目标是，在一次完整的搜索过程中，也就是只要keyword不变，filterCategoryList就保持不变
         * 因此我们需要把Map设为静态的，并且对参数categoryId进行判断，如果为0就重新设置Filter···List的值
         * 如果categoryId不为0，说明这个访问是某次搜索得到结果后，想查询某子类结果，不能让他重新设置filter··list，应该保持原有的值
         * 这时候就只要map是静态的，并且我们禁止它去执行给filterCategoryList赋值的过程，就可以让filter··List保持不变
         * */
        //接收到keyword之后，首先要把进行判断数据库中是否有相同的搜索记录
        String newKeyword = keyword.replace(" ", "");
        List<Keyword> keywordList = wxSearchService.queryKeyword(newKeyword);
        //如果没有，就把它插入到数据库中；如果已经存在，就更新一下update_time即可。
        if (keywordList.size()==0){
            Keyword keyword1 = new Keyword();
            keyword1.setKeyword(keyword);
            keyword1.setAddTime(new Date());
            keyword1.setUpdateTime(new Date());
            wxSearchService.insertKeyword(keyword1);
        }
        //如果已经存在这个keyword，那么只需要修改update_time即可
        else {
            Date date = new Date();
            keywordList.get(0).setUpdateTime(date);
            wxSearchService.updateKeyword(keywordList.get(0));
        }
        System.out.println("关键字是："+newKeyword+"哈哈");
        //然后就先要查询和keyword相似的商品
        //先开启分页
        PageHelper.startPage(page,size);
        List<SearchGoods> goodsList = wxSearchService.queryGoodsByKeyword(newKeyword,sort,order,categoryId);
        map = new HashMap<>();
        map.put("goodsList",goodsList);
        map.put("count",new PageInfo<>(goodsList).getTotal());

        //最后根据商品中的categoryId可以查询对应的category，把它放到set集合中：目的是让category不重复
        //如果categoryid为0才执行这个，也就是说，如果是“分类”按钮，直接拿出静态map中已经存在的list，不重新获取
        if (categoryId==0){
            HashSet<Category> filterCategoryList = new HashSet<>();
            for (SearchGoods goods : goodsList ) {
                Category category = wxSearchService.queryCategoryById(goods.getCategoryId());
                filterCategoryList.add(category);
            }
            map.put("filterCategoryList",filterCategoryList);
        }

        return ResponseUtil.success(map);
    }

    /**
     * 删除所有的搜索记录
     * */
    @RequestMapping("/search/clearhistory")
    public ResponseVo clearhistory(){
        wxSearchService.deleteAllRecords();
        return ResponseUtil.success();
    }
}
