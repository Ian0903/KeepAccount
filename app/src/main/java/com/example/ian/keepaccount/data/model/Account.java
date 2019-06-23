package com.example.ian.keepaccount.data.model;

import java.io.Serializable;
import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Account implements Serializable {
    @Id
    public long id;

    double money;    //金额
    Date recordTime;      //记录时间
    String accountBook;     //帐本
    String costFirstType;        //一级类型（支出/收入）
    String costSecondType;      //二级类型
    String remark;              //备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(String accountBook) {
        this.accountBook = accountBook;
    }

    public String getCostFirstType() {
        return costFirstType;
    }

    public void setCostFirstType(String costFirstType) {
        this.costFirstType = costFirstType;
    }

    public String getCostSecondType() {
        return costSecondType;
    }

    public void setCostSecondType(String costSecondType) {
        this.costSecondType = costSecondType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
