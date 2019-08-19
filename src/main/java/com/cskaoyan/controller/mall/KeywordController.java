package com.cskaoyan.controller.mall;

import com.cskaoyan.bean.mall.keyword.Keyword;
import com.cskaoyan.bean.mall.keyword.KeywordPage;
import com.cskaoyan.service.mall.KeywordService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/keyword")
public class KeywordController {

    @Autowired
    KeywordService keywordService;

    @RequestMapping("list")
    public ResponseVo getKeywordList(int page, int limit, String order, String sort, String keyword, String url) {
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        StringBuilder keywords = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (keyword != null) {
            keywords.append("%").append(keyword).append("%");
        }
        if (url != null) {
            urls.append("%").append(url).append("%");
        }
        List<Keyword> items = keywordService.getKeywordList(keywords.toString(), urls.toString());
        KeywordPage keywordPage = new KeywordPage();
        PageInfo<Keyword> pageInfo = new PageInfo<>(items);
        keywordPage.setItems(items);
        keywordPage.setTotal((int)pageInfo.getTotal());
        return ResponseUtil.success(keywordPage);
    }
}
