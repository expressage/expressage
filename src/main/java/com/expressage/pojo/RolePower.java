package com.expressage.pojo;

import java.io.Serializable;

public class RolePower  implements Serializable{
    private Integer rid;

    private String pid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}