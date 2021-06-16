package cn.edu.usts.cs2019.entity;

import java.util.Date;

public class Customer {

    public int id;
    private String name;
    private String password;
    private String telphone;
    private String address;
    private String company;


    //省略setter/getter方法


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }




    public Customer(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Customer(int id, String name, String password, String telphone,
                String address,String company) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telphone = telphone;
        this.address = address;
        this.company = company;

    }


}

