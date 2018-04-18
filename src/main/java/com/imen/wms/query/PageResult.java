package com.imen.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
public class PageResult {
    @Getter
    private int totalCount=0;//结果总数
    @Getter
    private List result=null;//结果集

    @Getter@Setter
    private int currentPage;//当前页：用户传入
    @Getter@Setter
    private int pageSize;//每页条数:用户传入

    @Getter
    private int prevPage;
    @Getter
    private int nextPage;
    @Getter
    private int totalPage;

    public PageResult(int totalCount, List result, int currentPage, int pageSize) {
        this.totalCount = totalCount;
        this.result = result;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        totalPage=totalPage==0?1:totalPage;//如果总页数为0，则改为1
        prevPage=currentPage-1>0?currentPage-1:1;
        nextPage=currentPage+1>totalPage?totalPage:currentPage+1;
    }
}
