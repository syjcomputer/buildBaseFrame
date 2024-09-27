package com.example.buildbaseframe.api.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <b>分页结果返回模板</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 一共有多少条符合要求
     */
    private Long total;

    /**
     * 上一页的页码（如果当前是第一页，则prev返回-1，表示没有上一页）
     */
    private Long prev = -1L;

    /**
     * 下一页的页码（如果当前是最后一页，则next返回-1，表示没有下一页）
     */
    private Long next = -1L;

    /**
     * 数据列表
     */
    private List<T> list;


    public static <E> PageResult<E> of(Page<E> page) {
        PageResult<E> result = new PageResult<>();
        result.setTotal(page.getTotal());
        long pageNum = page.getCurrent();
        if (pageNum > 1) {
            result.setPrev(pageNum - 1);
        }
        if (pageNum < page.getPages()) {
            result.setNext(pageNum + 1);
        }
        result.list = page.getRecords();
        return result;
    }

}