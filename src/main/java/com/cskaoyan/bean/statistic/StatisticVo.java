package com.cskaoyan.bean.statistic;

import java.util.List;

public class StatisticVo<T> {
    String[] columns;
    List<T> rows;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
