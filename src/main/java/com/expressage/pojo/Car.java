package com.expressage.pojo;

public class Car {
    private Integer carId;

    private String carName;

    private String carNo;

    private Integer carType;

    private Double cvolume;

    private Double cweight;

    private Double cheight;

    private Double cwidth;

    private Double clength;

    private String insurance;

    private String operation;

    private String travel;

    private String status;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Double getCvolume() {
        return cvolume;
    }

    public void setCvolume(Double cvolume) {
        this.cvolume = cvolume;
    }

    public Double getCweight() {
        return cweight;
    }

    public void setCweight(Double cweight) {
        this.cweight = cweight;
    }

    public Double getCheight() {
        return cheight;
    }

    public void setCheight(Double cheight) {
        this.cheight = cheight;
    }

    public Double getCwidth() {
        return cwidth;
    }

    public void setCwidth(Double cwidth) {
        this.cwidth = cwidth;
    }

    public Double getClength() {
        return clength;
    }

    public void setClength(Double clength) {
        this.clength = clength;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance == null ? null : insurance.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel == null ? null : travel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}