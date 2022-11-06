package com.example.HungerKitchen;

public class InventoryRecord {
    private String donorName;
    private String contactNo;
    private String nicNo;
    private String dAddress;
    private String invType;
    private String invQty;
    private String expDate;

    public InventoryRecord() {
    }

    public InventoryRecord(String donorName, String contactNo, String nicNo, String dAddress, String invType, String invQty, String expDate) {
        this.donorName = donorName;
        this.contactNo = contactNo;
        this.nicNo = nicNo;
        this.dAddress = dAddress;
        this.invType = invType;
        this.invQty = invQty;
        this.expDate = expDate;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvQty() {
        return invQty;
    }

    public void setInvQty(String invQty) {
        this.invQty = invQty;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}