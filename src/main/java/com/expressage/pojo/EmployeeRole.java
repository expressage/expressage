package com.expressage.pojo;

import java.io.Serializable;

public class EmployeeRole  implements Serializable{
    private Integer eid;

    private Integer rid;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}