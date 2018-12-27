package com.expressage.pojo;

public class Motorcade {
    private Integer motorcadeId;

    private Integer routeId;

    private String mname;

    private String eid;

    public Integer getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(Integer motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }
}