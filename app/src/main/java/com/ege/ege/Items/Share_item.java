package com.ege.ege.Items;

public class Share_item {

    public String konu_adı;
    public String yüzde;
    public String textyüzde;

    public Share_item() {
    }

    public Share_item(String textyüzde) {
        this.textyüzde = textyüzde;
    }

    public Share_item(String konu_adı, String yüzde, String textyüzde) {
        this.konu_adı = konu_adı;
        this.yüzde = yüzde;
        this.textyüzde = textyüzde;
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

    public String getYüzde() {
        return yüzde;
    }

    public void setYüzde(String yüzde) {
        this.yüzde = yüzde;
    }


}
