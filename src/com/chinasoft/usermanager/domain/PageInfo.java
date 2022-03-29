package com.chinasoft.usermanager.domain;

import java.util.List;

public class PageInfo<T> {
    private Integer size;   //每页显示记录数
    private Integer current;//当前页
    private Integer total;//总记录数
    private Integer pageNum;//总页数
    private List<T> list;//每页查询记录

    public PageInfo() {
    }

    public PageInfo(Integer size, Integer current, Integer total, Integer pageNum, List<T> list) {
        this.size = size;
        this.current = current;
        this.total = total;
        this.pageNum = pageNum;
        this.list = list;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "size=" + size +
                ", current=" + current +
                ", total=" + total +
                ", pageNum=" + pageNum +
                ", list=" + list +
                '}';
    }
}
