package com.expressage.pojo;

import java.util.Date;

public class CurrentBusiness {
    private String sendcustomer;

    private String orderno;

    private String sendgoodsaddr;

    private String receivegoodsaddr;

    private Integer carriage;

    private Integer billmoney;

    private Integer receivable;

    private Integer received;

    private Date sendgoodsdate;

    public String getSendcustomer() {
        return sendcustomer;
    }

    public void setSendcustomer(String sendcustomer) {
        this.sendcustomer = sendcustomer == null ? null : sendcustomer.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getSendgoodsaddr() {
        return sendgoodsaddr;
    }

    public void setSendgoodsaddr(String sendgoodsaddr) {
        this.sendgoodsaddr = sendgoodsaddr == null ? null : sendgoodsaddr.trim();
    }

    public String getReceivegoodsaddr() {
        return receivegoodsaddr;
    }

    public void setReceivegoodsaddr(String receivegoodsaddr) {
        this.receivegoodsaddr = receivegoodsaddr == null ? null : receivegoodsaddr.trim();
    }

    public Integer getCarriage() {
        return carriage;
    }

    public void setCarriage(Integer carriage) {
        this.carriage = carriage;
    }

    public Integer getBillmoney() {
        return billmoney;
    }

    public void setBillmoney(Integer billmoney) {
        this.billmoney = billmoney;
    }

    public Integer getReceivable() {
        return receivable;
    }

    public void setReceivable(Integer receivable) {
        this.receivable = receivable;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Date getSendgoodsdate() {
        return sendgoodsdate;
    }

    public void setSendgoodsdate(Date sendgoodsdate) {
        this.sendgoodsdate = sendgoodsdate;
    }
}