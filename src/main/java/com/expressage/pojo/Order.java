package com.expressage.pojo;

import java.util.Date;

public class Order {
    private Integer oid;

    private String orderno;

    private Integer uid;

    private Integer maddress;

    private Integer raddress;

    private Date orderTime;

    private Date appointmenttime;

    private String typeName;

    private Double weight;

    private Integer rprice;

    private Integer eprice;

    private Integer total;

    private Integer payType;

    private Integer rpay;

    private String status;

    private String remarks;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getAppointmenttime() {
        return appointmenttime;
    }

    public void setAppointmenttime(Date appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getRprice() {
        return rprice;
    }

    public void setRprice(Integer rprice) {
        this.rprice = rprice;
    }

    public Integer getEprice() {
        return eprice;
    }

    public void setEprice(Integer eprice) {
        this.eprice = eprice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getRpay() {
        return rpay;
    }

    public void setRpay(Integer rpay) {
        this.rpay = rpay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}