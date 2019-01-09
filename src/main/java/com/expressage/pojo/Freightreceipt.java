package com.expressage.pojo;

import java.util.Date;

public class Freightreceipt {
    private Integer hid;

    private String receiptcode;

    private String loadstation;

    private Date startcarrydate;

    private String dealstation;

    private Date arrivedate;

    private Integer did;

    private String billstate;
    
    private Distribute distribute;
    
    private Employee employee;
    
    public Distribute getDistribute() {
		return distribute;
	}

	public void setDistribute(Distribute distribute) {
		this.distribute = distribute;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getReceiptcode() {
        return receiptcode;
    }

    public void setReceiptcode(String receiptcode) {
        this.receiptcode = receiptcode == null ? null : receiptcode.trim();
    }

    public String getLoadstation() {
        return loadstation;
    }

    public void setLoadstation(String loadstation) {
        this.loadstation = loadstation == null ? null : loadstation.trim();
    }

    public Date getStartcarrydate() {
        return startcarrydate;
    }

    public void setStartcarrydate(Date startcarrydate) {
        this.startcarrydate = startcarrydate;
    }

    public String getDealstation() {
        return dealstation;
    }

    public void setDealstation(String dealstation) {
        this.dealstation = dealstation == null ? null : dealstation.trim();
    }

    public Date getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(Date arrivedate) {
        this.arrivedate = arrivedate;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getBillstate() {
        return billstate;
    }

    public void setBillstate(String billstate) {
        this.billstate = billstate == null ? null : billstate.trim();
    }
}