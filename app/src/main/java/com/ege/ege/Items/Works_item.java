package com.ege.ege.Items;

public class Works_item {


    public String konu_adı;
    public int teori;
    public int pratik;
    public int yüzde;
    public String textyüzde;

    public Works_item() {
    }

    public Works_item(String konu_adı, int teori, int pratik, int yüzde, String textyüzde) {
        this.konu_adı = konu_adı;
        this.teori = teori;
        this.pratik = pratik;
        this.yüzde = yüzde;
        this.textyüzde = textyüzde;
    }

    public int getYüzde() {
        return yüzde;
    }

    public void setYüzde(int yüzde) {
        this.yüzde = yüzde;
    }

    public int getPratik() {
        return pratik;
    }

    public void setPratik(int pratik) {
        this.pratik = pratik;
    }

    public String getTextyüzde() {
        return textyüzde;
    }

    public void setTextyüzde(String textyüzde) {
        this.textyüzde = textyüzde;
    }

    public String getKonu_adı() {
        return konu_adı;
    }

    public void setKonu_adı(String konu_adı) {
        this.konu_adı = konu_adı;
    }

    public int getTeori() {
        return teori;
    }

    public void setTeori(int teori) {
        this.teori = teori;
    }


}





