package com.expressage.pojo;

import java.util.Date;

public class WarehouseRecord {
    private Integer warehouseRecordId;

    private Integer putoroutput;

    private Integer orderid;

    private Integer wareid;

    private Date outputtime;

    private Date puttime;

    private Integer puteid;

    private Integer outputeid;

    public Integer getWarehouseRecordId() {
        return warehouseRecordId;
    }

    public void setWarehouseRecordId(Integer warehouseRecordId) {
        this.warehouseRecordId = warehouseRecordId;
    }

    public Integer getPutoroutput() {
        return putoroutput;
    }

    public void setPutoroutput(Integer putoroutput) {
        this.putoroutput = putoroutput;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getWareid() {
        return wareid;
    }

    public void setWareid(Integer wareid) {
        this.wareid = wareid;
    }

    public Date getOutputtime() {
        return outputtime;
    }

    public void setOutputtime(Date outputtime) {
        this.outputtime = outputtime;
    }

    public Date getPuttime() {
        return puttime;
    }

    public void setPuttime(Date puttime) {
        this.puttime = puttime;
    }

    public Integer getPuteid() {
        return puteid;
    }

    public void setPuteid(Integer puteid) {
        this.puteid = puteid;
    }

    public Integer getOutputeid() {
        return outputeid;
    }

    public void setOutputeid(Integer outputeid) {
        this.outputeid = outputeid;
    }
}