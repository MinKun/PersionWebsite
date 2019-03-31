package com.tydd.persion.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @ClassName PagingUtil
 * @Description 分页工具类
 * @Author kun
 * @Date 2019/3/31
 * @Version 1.0
 */
public class PagingUtil {

    /**
     * 根据分页、排序对象创建jpa分页对象
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static PageRequest buildPageRequest(Integer pageNumber, Integer pageSize, String order) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        pageSize = pageSize == null ? 10 : pageSize;
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, buildSort(order));
        return pageRequest;
    }

    /**
     * 获得排序对象
     * @param order
     * @return
     */
    public static Sort buildSort(String order) {
        Sort sort = null;
        if (!StringUtil.isEmpty(order)) {
            // 判断排序方式
            String style = order.substring(0, 1);
            Sort.Direction direction = null;
            if ("+".equals(style)) {
                // 正序
                direction = Sort.Direction.ASC;
            } else if("-".equals(style)) {
                // 倒序
                direction = Sort.Direction.DESC;
            } else {
                // 默认正序
                direction = Sort.Direction.ASC;
            }
            // 创建排序对象
            sort = new Sort(direction, order.substring(1).split(","));
        }
        return sort;
    }
}
