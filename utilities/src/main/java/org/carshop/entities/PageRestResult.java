package org.carshop.entities;


import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2019/10/21 9:29
 */

public class PageRestResult<T extends java.io.Serializable> implements java.io.Serializable {

    private static final long serialVersionUID = -802357682594188365L;

    private int result;
    private String msg;
    private long pageindex;
    private long pages;
    private long records;
    private List<T> rows;


    public PageRestResult() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public PageRestResult(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getPageindex() {
        return pageindex;
    }

    public void setPageindex(long pageindex) {
        this.pageindex = pageindex;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void filPage(IPage<T> p) {
        this.pageindex = p.getCurrent();
        this.pages = p.getPages();
        this.records = p.getTotal();
        this.rows = p.getRecords();
    }



}
