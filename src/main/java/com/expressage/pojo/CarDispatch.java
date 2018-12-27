package com.expressage.pojo;

import java.util.Date;

public class CarDispatch {
    private Integer carDispatchId;

    private Integer did;

    private Integer cid;

    private Integer eid;

    private Date dtime;

    private Date etime;

    public Integer getCarDispatchId() {
        return carDispatchId;
    }

    public void setCarDispatchId(Integer carDispatchId) {
        this.carDispatchId = carDispatchId;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }
}