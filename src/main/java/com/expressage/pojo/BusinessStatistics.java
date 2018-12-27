package com.expressage.pojo;

public class BusinessStatistics {
    private String pickupstation;

    private String receivingaddress;

    private Integer amountreceivable;

    private Integer amountreceived;

    private Integer receivable;

    private Integer received;

    private Integer remaining;

    public String getPickupstation() {
        return pickupstation;
    }

    public void setPickupstation(String pickupstation) {
        this.pickupstation = pickupstation == null ? null : pickupstation.trim();
    }

    public String getReceivingaddress() {
        return receivingaddress;
    }

    public void setReceivingaddress(String receivingaddress) {
        this.receivingaddress = receivingaddress == null ? null : receivingaddress.trim();
    }

    public Integer getAmountreceivable() {
        return amountreceivable;
    }

    public void setAmountreceivable(Integer amountreceivable) {
        this.amountreceivable = amountreceivable;
    }

    public Integer getAmountreceived() {
        return amountreceived;
    }

    public void setAmountreceived(Integer amountreceived) {
        this.amountreceived = amountreceived;
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

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}