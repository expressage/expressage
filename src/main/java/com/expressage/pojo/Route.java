package com.expressage.pojo;

import java.util.Date;

public class Route {
    private Integer routeId;

    private String spoint;

    private String epoint;

    private Double mileage;

    private Date estimatedtime;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getSpoint() {
        return spoint;
    }

    public void setSpoint(String spoint) {
        this.spoint = spoint == null ? null : spoint.trim();
    }

    public String getEpoint() {
        return epoint;
    }

    public void setEpoint(String epoint) {
        this.epoint = epoint == null ? null : epoint.trim();
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Date getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(Date estimatedtime) {
        this.estimatedtime = estimatedtime;
    }
}