package com.yq.android_recruit.component;

import com.yq.android_recruit.pojo.DetailedResume;
import com.yq.android_recruit.pojo.Enterprise;
import com.yq.android_recruit.pojo.Resume;

import java.io.Serializable;
import java.util.List;

//通过page对象传递数据，先显示简略信息
public class Page<T> implements Serializable {
    public final static Integer PAGE_NUM = 1;
    public final static Integer START = 1;
    public final static Integer END = 8;

    private Integer pageNum = PAGE_NUM;   //当前页
    private Integer pageCount;            //总页数
    //start，end是给后台查询用的
    private Integer start = START ;        //记录开始位置
    private Integer end = END;              //记录结束位置
    private Integer pageRecord;          //每页记录数
    private Integer recordTotal;          //该条件下所有记录数
    private List<String> jobs;
    private List<String> qualifications;  //学历
    private T bean;
    private List<T> divBeans;
    private String userId;
    private List<DetailedResume> detailedResumes;
    public Page() {
    }

    public Page(Integer pageRecord, T bean) {
        this.pageRecord = pageRecord;
        this.bean = bean;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public List<T> getDivBeans() {
        return divBeans;
    }

    public void setDivBeans(List<T> divBeans) {
        this.divBeans = divBeans;
    }

    //获取当前页
    public  Integer getPageNum() {
        return (int)Math.ceil((getStart()*1.0)/getPageRecord());
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageCount() {
        return (int) Math.ceil((getRecordTotal()*1.0)/getPageRecord());
    }

    public void  setStart(Integer start){
        this.start = start;
    }
    public Integer getStart() {
        return    (this.pageNum-1)*this.pageRecord+1;
    }
    public void setEnd(Integer end){
        this.end = end;
    }
    public Integer getEnd() {
       return getPageNum()*getPageRecord();
    }

    public Integer getPageRecord() {
        return pageRecord;
    }

    public void setPageRecord(Integer pageRecord) {
        this.pageRecord = pageRecord;
    }

    public Integer getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Integer recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public List<DetailedResume> getDetailedResumes() {
        return detailedResumes;
    }

    public void setDetailedResumes(List<DetailedResume> detailedResumes) {
        this.detailedResumes = detailedResumes;
    }
}
