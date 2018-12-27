package com.expressage.pojo;

public class GoodsReceipt {
    private Integer cid;

    private String receiptcode;

    private String orderno;

    private String error;

    private Integer pieceamount;

    private String specifications;

    private Integer goodsvalue;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getReceiptcode() {
        return receiptcode;
    }

    public void setReceiptcode(String receiptcode) {
        this.receiptcode = receiptcode == null ? null : receiptcode.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public Integer getPieceamount() {
        return pieceamount;
    }

    public void setPieceamount(Integer pieceamount) {
        this.pieceamount = pieceamount;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Integer getGoodsvalue() {
        return goodsvalue;
    }

    public void setGoodsvalue(Integer goodsvalue) {
        this.goodsvalue = goodsvalue;
    }
}