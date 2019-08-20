package com.cskaoyan.goods;


import com.cskaoyan.service.admin.goods.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {

    @Autowired
    GoodsService goodsService;

    @Test
    public void countTotalGoodsCount() {
        long count = goodsService.countTotalGoodsCount();
        System.out.println("count = " + count);
    }

}
