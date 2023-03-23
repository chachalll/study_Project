package com.example.a86136.grlc;

/**
 * Created by 86136 on 2021/12/3.
 */

public class Come{
    private String id;
    private String time;
    private String leib;
    private String  money;
    private String  payer;
    private String other;
    private String  note;
    public String getId(){
        return id;
    }
    public String getTime(){
        return time;
    }
    public String getLeib(){
        return leib;
    }
    public String getMoney(){
        return money;
    }
    public String getPayer(){
        return payer;
    }
    public String getOther(){
        return other;
    }
    public String getNote(){
        return note;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setLeib(String leib){
        this.leib = leib;
    }
    public void setMoney(String money){
        this.money = money;
    }
    public void setPayer(String payer){
       this.payer = payer;
    }
    public void setOther(String other){
        this.other = other;
    }
    public void setNote(String note){
        this.note = note;
    }
}
