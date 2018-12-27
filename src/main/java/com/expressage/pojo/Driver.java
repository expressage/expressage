package com.expressage.pojo;

public class Driver {
    private Integer driverId;

    private String driverName;

    private Integer eid;

    private Integer carType;

    private String dcard;

    private Integer dyears;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getDcard() {
        return dcard;
    }

    public void setDcard(String dcard) {
        this.dcard = dcard == null ? null : dcard.trim();
    }

    public Integer getDyears() {
        return dyears;
    }

    public void setDyears(Integer dyears) {
        this.dyears = dyears;
    }
}