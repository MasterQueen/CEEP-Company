package com.example.administrator.company;

/**
 * Created by Administrator on 6/9/2018.
 */

public class Rocard_Datas {

    private String address;
    private  String time;
    private  String postion;

    public Rocard_Datas(String title, String time, String postion){
        this.address = title;
        this.time = time;
        this.postion  = postion;
    }

    public String getAddress(){

        return address;
    }

    public String getTime() {
        return time;
    }

    public String getPostion() {
        return postion;
    }
}
