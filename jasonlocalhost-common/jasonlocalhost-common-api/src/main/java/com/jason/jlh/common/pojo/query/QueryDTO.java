package com.jason.jlh.common.pojo.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jason.jlh.common.pojo.BaseValueObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title: QueryDTO
 * @package: com.jason.jlh.common.pojo.query
 * @description: 查询参数基类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDTO extends BaseValueObject {

    private static final long serialVersionUID = 1L;

    /**
     * 分页信息
     */
    @ApiModelProperty(value = "分页信息")
    protected Pages page;

    /**
     * 获取分页信息, 如果为null, 则默认构造一个分页大小为20条的分页对象
     *
     * @param: []
     * @return: com.jason.jlh.common.pojo.query.Pages
     * @author:
     * @date: 2020/5/3
     */
    public Pages getPage() {
        if (page == null) {
            page = new Pages();
        }
        return page;
    }

    /**
     * 设置页码
     *
     * @param: [index]
     * @return: com.jason.jlh.common.pojo.query.QueryDTO
     * @author:
     * @date: 2020/5/3
     */
    @JsonIgnore
    public QueryDTO setPageIndex(long index) {
        getPage().setPage(index);
        return this;
    }

    /**
     * 设置分页大小
     *
     * @param: [size]
     * @return: com.jason.jlh.common.pojo.query.QueryDTO
     * @author:
     * @date: 2020/5/3
     */
    @JsonIgnore
    public QueryDTO setPageSize(int size) {
        getPage().setPageSize(size);
        return this;
    }

}
