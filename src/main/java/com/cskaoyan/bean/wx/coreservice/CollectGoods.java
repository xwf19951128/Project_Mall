package com.cskaoyan.bean.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;

public class CollectGoods extends Goods {
    private long valueId;
    private short type;
    private long goodsId;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getValueId() {
        return valueId;
    }

    public void setValueId(long valueId) {
        this.valueId = valueId;
    }
}
