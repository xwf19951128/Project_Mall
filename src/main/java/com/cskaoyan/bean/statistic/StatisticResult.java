package com.cskaoyan.bean.statistic;

public class StatisticResult<T> {
    String[] columns;
    T[] rows;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public T[] getRows() {
        return rows;
    }

    public void setRows(T[] rows) {
        this.rows = rows;
    }
}
