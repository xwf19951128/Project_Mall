package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.admin.mall.keyword.KeywordPage;
import com.cskaoyan.service.admin.mall.KeywordService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/keyword")
@Api(tags = "keyword")
public class KeywordController {

    @Autowired
    KeywordService keywordService;

    @RequestMapping("list")
    @ApiOperation(value = "条件查询和分页")
    public ResponseVo getKeywordList(int page, int limit, String order, String sort, String keyword, String url) {
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        List<Keyword> items = keywordService.getKeywordList(keyword, url);
        KeywordPage keywordPage = new KeywordPage();
        PageInfo<Keyword> pageInfo = new PageInfo<>(items);
        keywordPage.setItems(items);
        keywordPage.setTotal((int)pageInfo.getTotal());
        return ResponseUtil.success(keywordPage);
    }

    @RequestMapping("delete")
    public ResponseVo deleteKeyword(@RequestBody Keyword keyword) {
        keywordService.deleteKeyword(keyword);
        return ResponseUtil.success(null);
    }

    @RequestMapping("create")
    public ResponseVo createKeyword(@RequestBody Keyword keyword) {
        keyword.setSortOrder((int)(Math.ceil(Math.random() * 100)));
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        keyword = keywordService.createKeyword(keyword);
        return ResponseUtil.success(keyword);
    }

    @RequestMapping("update")
    public ResponseVo updateKeyword(@RequestBody Keyword keyword) {
        keywordService.updateKeyword(keyword);
        return ResponseUtil.success(null);
    }
}
