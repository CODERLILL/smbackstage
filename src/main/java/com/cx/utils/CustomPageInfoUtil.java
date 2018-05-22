package com.cx.utils;

import com.cx.model.custom.CustomPageInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version V1.0
 * @Title: CustomPageInfoUtil.java <br>
 * @Package com.cx.utils <br>
 * @Description: (自定义pageInfo相关内容) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 09:28 <br>
 */
public class CustomPageInfoUtil<T> {

    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/3/11 23:18<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: getPageInfo <br>
     * @Description: (获取pageInfo返回的内容) <br>
     * @parma: [list] <br>
     * @return: customPageInfo<br>
     */
    public CustomPageInfo<T> getPageInfo(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        CustomPageInfo<T> customPageInfo = new CustomPageInfo<>();
        customPageInfo.setTotal(pageInfo.getTotal());
        customPageInfo.setPageNum(pageInfo.getPageNum());
        customPageInfo.setPageSize(pageInfo.getPageSize());
        customPageInfo.setPages(pageInfo.getPages());
        customPageInfo.setSize(pageInfo.getSize());
        customPageInfo.setHasNextPage(pageInfo.isHasNextPage());
        customPageInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
        customPageInfo.setFirstPage(pageInfo.isIsFirstPage());
        customPageInfo.setLastPage(pageInfo.isIsLastPage());
        customPageInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
        customPageInfo.setShowList(pageInfo.getList());
        return customPageInfo;
    }
}
