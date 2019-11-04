package com.tangu.config;

/**
 * FileName: Page
 * Author: yeyang
 * Date: 2019/9/5 15:41
 */

public class Page implements Cloneable {
    /**
     * 每一页大小
     */
    private Integer pageSize = 100;
    /**
     * 目标页码
     */
    private Integer targetIndex;
    /**
     * 总数量
     */
    private Integer countNumber;
    /**
     * 总页码
     */
    private Integer pageRecord;
    /**
     * 是否开启 默认不开启分页
     */
    private Boolean flag = false;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(Integer targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Integer getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public Integer getPageRecord() {
        return pageRecord;
    }

    public void setPageRecord(Integer pageRecord) {
        this.pageRecord = pageRecord;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
