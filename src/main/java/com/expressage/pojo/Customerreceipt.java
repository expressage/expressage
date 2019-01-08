package com.expressage.pojo;

import java.util.Date;

public class Customerreceipt {
    private Integer kid;

    private String orderno;

    private Integer maddress;

    private Integer raddress;

    private String checkgoodsrecord;

    private String consignee;

    private Date consigneedate;
    
    private Order order;
    
    private Address address;
   
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getMaddress() {
        return maddress;
    }

    public void setMaddress(Integer maddress) {
        this.maddress = maddress;
    }

    public Integer getRaddress() {
        return raddress;
    }

    public void setRaddress(Integer raddress) {
        this.raddress = raddress;
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