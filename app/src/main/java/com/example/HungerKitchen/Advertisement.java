package com.example.HungerKitchen;

public class Advertisement {

    private String newspaper;
    private String pdate;
    private String category;
    private String body;
    private Integer wordcount;
    private String status;

    public Advertisement() {
    }

    public Advertisement(String newspaper, String pdate, String category, String body, Integer wordcount, String status) {
        this.newspaper = newspaper;
        this.pdate = pdate;
        this.category = category;
        this.body = body;
        this.wordcount = wordcount;
        this.status = status;
    }

    public String getNewspaper() {
        return newspaper;
    }

    public String getPdate() {
        return pdate;
    }

    public String getCategory() {
        return category;
    }

    public String getBody() {
        return body;
    }

    public Integer getWordcount() {
        return wordcount;
    }

    public String getStatus() {
        return status;
    }


    public void setNewspaper(String newspaper) {
        this.newspaper = newspaper;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
