package com.example.HungerKitchen;

public class FundRecord {
    private String Name;
    private String Number;
    private String Address;
    private String NIC;
    private String PaymentType;
    private String Amount;
    private String CardNumber;
    private String ExDate;
    private String CVV;

    public FundRecord() {
    }

    public FundRecord(String name, String number, String address, String NIC, String paymentType, String amount, String cardNumber, String exDate, String CVV) {
        Name = name;
        Number = number;
        Address = address;
        this.NIC = NIC;
        PaymentType = paymentType;
        Amount = amount;
        CardNumber = cardNumber;
        ExDate = exDate;
        this.CVV = CVV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getExDate() {
        return ExDate;
    }

    public void setExDate(String exDate) {
        ExDate = exDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
