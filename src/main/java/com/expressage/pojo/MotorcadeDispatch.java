package com.expressage.pojo;

import java.util.Date;

public class MotorcadeDispatch {
    private Integer motorcadeDispatchId;

    private Integer motorcadeId;

    private Integer carId;

    private Integer routeId;

    private Integer eid;

    private Date stime;

    private Date etime;

    private String status;

    public Integer getMotorcadeDispatchId() {
        return motorcadeDispatchId;
    }

    public void setMotorcadeDispatchId(Integer motorcadeDispatchId) {
        this.motorcadeDispatchId = motorcadeDispatchId;
    }

    public Integer getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(Integer motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}