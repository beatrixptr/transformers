package com.example.szoftverfejleszts;

public class Helyezes {
    public String vnev;
    public String knev;
    public int sec;
    public String pont;

    public Helyezes(String vnev, String knev, int sec, String pont){
        this.vnev = vnev;
        this.knev = knev;
        this.sec = sec;
        this.pont = " ";
    }

    public Helyezes(){}

    public String getVnev(){
        return vnev;
    }
    public String getKnev(){
        return knev;
    }
    public int getSec(){
        return sec;
    }
    public void setVnev(String vnev){
        this.vnev = vnev;
    }
    public void setKnev(String knev){
        this.knev = knev;
    }
    public void setSec(int sec){
        this.sec = sec;
    }
}
