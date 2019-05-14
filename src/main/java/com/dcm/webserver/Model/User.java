package com.dcm.webserver.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{

    private String userId;


    private String phone;

    private String username;


    private String violation;


    private String vehicleId;


    private BigDecimal payment;


    private String password;

    public User() { }

    public User(String username, String password, String vehicleId, String phone){
        this.password=password;
        this.username=username;
        this.vehicleId=vehicleId;
        this.phone=phone;
    }
    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }


    public String getViolation() {
        return violation;
    }


    public void setViolation(String violation) {
        this.violation = violation == null ? null : violation.trim();
    }


    public String getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId == null ? null : vehicleId.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}