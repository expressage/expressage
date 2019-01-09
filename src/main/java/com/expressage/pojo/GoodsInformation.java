package com.expressage.pojo;

import java.util.Date;

public class GoodsInformation {
    private Integer xid;

    private String revertcode;

    private Integer eid;

    private String checkgoodsrecord;

    private String consignee;

    private Date consigneedate;
    
    private Employee employee;
    
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public String getRevertcode() {
        return revertcode;
    }

    public void setRevertcode(String revertcode) {
        this.revertcode = revertcode == null ? null : revertcode.trim();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getCheckgoodsrecord() {
        return checkgoodsrecord;
    }

    public void setCheckgoodsrecord(String checkgoodsrecord) {
        this.checkgoodsrecord = checkgoodsrecord == null ? null : checkgoodsrecord.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public Date getConsigneedate() {
        return consigneedate;
    }

    public void setConsigneedate(Date consigneedate) {
        this.consigneedate = consigneedate;
    }
}