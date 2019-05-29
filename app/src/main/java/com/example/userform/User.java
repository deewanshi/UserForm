package com.example.userform;

/**
 * Created by DEEWANSHI GROVER on 27-10-2018.
 */

public class User {
    private String nem;
    private String tall;
    private String weight;
    private String bg;
    private String bp;
    private String diab;
    private String aller;
    private String allertype;
    private String disease;

    public User() {
    }

    public User(String nem, String tall, String weight, String bg, String bp, String diab, String aller, String allertype, String disease) {
        this.nem = nem;
        this.tall = tall;
        this.weight = weight;
        this.bg = bg;
        this.bp = bp;
        this.diab = diab;
        this.aller = aller;
        this.allertype = allertype;
        this.disease = disease;
    }

    public String getNem() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem = nem;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getDiab() {
        return diab;
    }

    public void setDiab(String diab) {
        this.diab = diab;
    }

    public String getAller() {
        return aller;
    }

    public void setAller(String aller) {
        this.aller = aller;
    }

    public String getAllertype() {
        return allertype;
    }

    public void setAllertype(String allertype) {
        this.allertype = allertype;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}