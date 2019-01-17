package com.expressage.pojo;

public class Price {
    private Integer pid;

    private String address1;

    private String address2;

    private Integer fweight;

    private Integer cweight;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    public Integer getFweight() {
        return fweight;
    }

    public void setFweight(Integer fweight) {
        this.fweight = fweight;
    }

    public Integer getCweight() {
        return cweight;
    }

    public void setCweight(Integer cweight) {
        this.cweight = cweight;
    }
}