package com.ege.ege.Items;

import java.util.ArrayList;


public class Tekrarlar_item {


    private ArrayList<String> userEmailList;
    private ArrayList<String> userKonuAdıList;
    private ArrayList<Integer> userTeoriList;
    private ArrayList<Integer> userPratikList;
    private ArrayList<Integer> userYüzdeList;
    private ArrayList<Integer> userKaçıncıTekrarList;
    private ArrayList<Integer> userAralıkList;
    private ArrayList<Integer> userGeçenGünList;

    public Tekrarlar_item(ArrayList<String> userEmailList, ArrayList<String> userKonuAdıList, ArrayList<Integer> userTeoriList, ArrayList<Integer> userPratikList, ArrayList<Integer> userYüzdeList, ArrayList<Integer> userKaçıncıTekrarList, ArrayList<Integer> userAralıkList, ArrayList<Integer> userGeçenGünList) {
        this.userEmailList = userEmailList;
        this.userKonuAdıList = userKonuAdıList;
        this.userTeoriList = userTeoriList;
        this.userPratikList = userPratikList;
        this.userYüzdeList = userYüzdeList;
        this.userKaçıncıTekrarList = userKaçıncıTekrarList;
        this.userAralıkList = userAralıkList;
        this.userGeçenGünList = userGeçenGünList;
    }

    public Tekrarlar_item() {

    }

    public ArrayList<String> getUserEmailList() {
        return userEmailList;
    }

    public void setUserEmailList(ArrayList<String> userEmailList) {
        this.userEmailList = userEmailList;
    }

    public ArrayList<String> getUserKonuAdıList() {
        return userKonuAdıList;
    }

    public void setUserKonuAdıList(ArrayList<String> userKonuAdıList) {
        this.userKonuAdıList = userKonuAdıList;
    }

    public ArrayList<Integer> getUserTeoriList() {
        return userTeoriList;
    }

    public void setUserTeoriList(ArrayList<Integer> userTeoriList) {
        this.userTeoriList = userTeoriList;
    }

    public ArrayList<Integer> getUserPratikList() {
        return userPratikList;
    }

    public void setUserPratikList(ArrayList<Integer> userPratikList) {
        this.userPratikList = userPratikList;
    }

    public ArrayList<Integer> getUserYüzdeList() {
        return userYüzdeList;
    }

    public void setUserYüzdeList(ArrayList<Integer> userYüzdeList) {
        this.userYüzdeList = userYüzdeList;
    }

    public ArrayList<Integer> getUserKaçıncıTekrarList() {
        return userKaçıncıTekrarList;
    }

    public void setUserKaçıncıTekrarList(ArrayList<Integer> userKaçıncıTekrarList) {
        this.userKaçıncıTekrarList = userKaçıncıTekrarList;
    }

    public ArrayList<Integer> getUserAralıkList() {
        return userAralıkList;
    }

    public void setUserAralıkList(ArrayList<Integer> userAralıkList) {
        this.userAralıkList = userAralıkList;
    }

    public ArrayList<Integer> getUserGeçenGünList() {
        return userGeçenGünList;
    }


/*public String konu_adı;
    public int teori;
    public int pratik;
    public int kaçıncı_tekrar;
    public int yüzde= (100 - (100/7 * ((2*kaçıncı_tekrar)-1)));
    public int aralık= (((2*kaçıncı_tekrar)-1)*((teori+pratik)/2));*/

    public void setUserGeçenGünList(ArrayList<Integer> userGeçenGünList) {
        this.userGeçenGünList = userGeçenGünList;
    }


}
