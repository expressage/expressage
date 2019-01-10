package com.expressage.pojo;

import java.util.Date;

public class GoodsInformation {
    private Integer xid;

    private String revertcode;

    private Integer eid;

    private String checkgoodsrecord;

    private String consignee;

    private Date consigneedate;
    
 private Employee employeeid;
    
    private Freightreceipt freigid;
    

	public Freightreceipt getFreigid() {
		return freigid;
	}

	public void setFreigid(Freightreceipt freigid) {
		this.freigid = freigid;
	}

	public Employee getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Employee employeeid) {
		this.employeeid = employeeid;
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