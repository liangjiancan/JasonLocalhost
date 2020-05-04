package com.jason.jlh.common.pojo.query;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import com.jason.jlh.common.pojo.BaseValueObject;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 * @title: Pages
 * @package: com.jason.jlh.common.pojo.query
 * @description: 封装分页数据类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@SuppressWarnings("unchecked")
public class Pages<T extends BaseValueObject> extends BaseValueObject {

    private static final long serialVersionUID = -6174013679824669157L;

    /**
     * 默认每页20条记录
     */
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 默认可见翻页视图为10页
     */
    private static final Integer DEFAULT_PAGE_VIEW_SIZE = 10;

    /**
     * 当前页码
     */
    private long page;

    /**
     * 分页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 可见页数
     */
    private int pageViewSize = DEFAULT_PAGE_VIEW_SIZE;

    /**
     * 总记录数
     */
    private long totals;

    /**
     * 分页记录
     */
    private List<T> records;

    /**
     * 分页记录的类型
     */
    private transient volatile Class<T> rowType;

    // ========== 构造器 START ==========

    public Pages() {
        setPage(1);
    }

    public Pages(int size) {
        this();
        setPageSize(size);
    }

    public Pages(long page, int pageSize, long totalSize, List<T> data) {
        super();
        setPage(page);
        setPageSize(pageSize);
        setTotals(totalSize);
        setRecords(data);
    }

    // ========== 构造器 END ==========

    // ========== GETTER/SETTER START ==========

    @XmlElement
    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        Preconditions.checkArgument(page > 0, "Page must be greater than 0");
        this.page = page;
    }

    @XmlElement
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        Preconditions.checkArgument(pageSize > 0, "PageSize must be greater than 0");
        this.pageSize = pageSize;
    }

    @XmlElement
    public int getPageViewSize() {
        return pageViewSize;
    }

    public void setPageViewSize(int pageViewSize) {
        Preconditions.checkArgument(pageViewSize > 0, "PageViewSize must be greater than 0");
        this.pageViewSize = pageViewSize;
    }

    @XmlElement
    public long getTotals() {
        return totals;
    }

    public void setTotals(long totals) {
        this.totals = totals;
    }

    @XmlElement
    public List<T> getRecords() {
        return records == null ? Collections.unmodifiableList(new ArrayList<T>(0)) : records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
        // 同时更新分页记录的类型
        this.rowType = null;
        getRowType();
    }

    /**
     * 从数组传入数据行.
     *
     * @param records 数据行数组
     */
    public void setRecords(T... records) {
        if (null == records) {
            this.records = null;
            this.rowType = null;
        } else {
            setRecords(Arrays.asList(records));
        }
    }

    /**
     * 动态识别数据行的实际类型, 由于泛型类型在运行时不可见, 只能用运行时方法获取
     *
     * @param: []
     * @return: java.lang.Class<T>
     * @author:
     * @date: 2020/5/3
     */
    @JsonIgnore
    public Class<T> getRowType() {
        if (null == rowType && null != getRecords() && getRecords().size() > 0) {
            // 数据数组为空(0元素)时，只能得到ComponentType类型(即AbstractValueObject)，也属于无法识别。
            // rowType = (Class<T>) getRecords().getClass().getComponentType();
            rowType = (Class<T>) getRecords().get(0).getClass();
        }
        return rowType;
    }

    public void setRowType(Class<T> rowType) {
        Preconditions.checkNotNull(rowType, "RowType must not be null");
        this.rowType = rowType;
    }

    // ========== GETTER/SETTER END ==========

    // ========== 分页相关方法 START ==========

    /**
     * 得到当前页的数据总量.
     *
     * @return 当前页的数据量。如果一页的数据不足，此数据值肯定小于每页大小数。
     */
    @JsonGetter
    @XmlElement
    public int getNumberOfElements() {
        return getRecords() == null ? 0 : getRecords().size();
    }

    @JsonGetter
    @XmlElement
    public boolean isHasPreviousPage() {
        return getPage() > 1;
    }

    @JsonGetter
    @XmlElement
    public boolean isFirstPage() {
        return getPage() == 1;
    }

    @JsonGetter
    @XmlElement
    public boolean isHasNextPage() {
        return getPage() < getTotalPages();
    }

    @JsonGetter
    @XmlElement
    public boolean isLastPage() {
        return getPage() == getTotalPages();
    }

    @JsonGetter
    @XmlElement
    public boolean isPageOutOfRange() {
        return getPage() < 1 || getPage() > getTotalPages();
    }

    @JsonGetter
    @XmlElement
    public boolean isHasContent() {
        return getRecords() != null && getRecords().size() > 0;
    }

    @JsonGetter
    @XmlElement
    public long getTotalPages() {
        return (totals % pageSize == 0) ? (totals / pageSize) : (totals / pageSize) + 1;
    }

    /**
     * 当前页第一条数据在所有结果中的位置
     * 
     * @param: []
     * @return: long
     * @author: 
     * @date: 2020/5/3
     */
    @JsonGetter
    @XmlElement
    public long getPagePos() {
        return (page - 1) * (long) pageSize;
    }

    /**
     * 获取可见页数的第一页
     * 
     * @param: []
     * @return: long
     * @author: 
     * @date: 2020/5/3
     */
    @JsonGetter
    @XmlElement
    public long getPageViewBegin() {
        long minus = Math.max(1, getPage() - (long) (getPageViewSize() / 2));
        long maxBound = Math.max(1, getTotalPages() - getPageViewSize() + 1);
        return Math.min(minus, maxBound);
    }

    /**
     * 获取可见页数的最后一页
     * 
     * @param: []
     * @return: long
     * @author: 
     * @date: 2020/5/3
     */
    @JsonGetter
    @XmlElement
    public long getPageViewEnd() {
        return Math.min(getPageViewBegin() + getPageViewSize() - 1, getTotalPages());
    }

    /**
     * 分页导航上可显示的分页页码列表
     * 
     * @param: []
     * @return: java.util.List<java.lang.Long>
     * @author: 
     * @date: 2020/5/3
     */
    @JsonGetter
    @XmlElement
    public List<Long> getPageViewScope() {
        List<Long> list = new ArrayList<Long>();
        for (long i = getPageViewBegin(); i <= getPageViewEnd(); i++) {
            list.add(i);
        }
        return list;
    }

    // ========== 分页相关方法 END ==========

    /**
     * 获取数据行的迭代器对象
     * 
     * @param: []
     * @return: java.util.Iterator<T>
     * @author:
     * @date: 2020/5/3
     */
    public Iterator<T> iterator() {
        return getRecords().iterator();
    }
}
