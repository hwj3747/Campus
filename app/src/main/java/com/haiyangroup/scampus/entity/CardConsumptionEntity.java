package com.haiyangroup.scampus.entity;

/**
 * Created by hwj3747 on 2016/4/21.
 */
public class CardConsumptionEntity{
    String date;
    String consumption_num;
    String balance_num;
    String expend_name;

    public String getBalance_num() {
        return balance_num;
    }

    public void setBalance_num(String balance_num) {
        this.balance_num = balance_num;
    }

    public String getConsumption_num() {
        return consumption_num;
    }

    public void setConsumption_num(String consumption_num) {
        this.consumption_num = consumption_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpend_name() {
        return expend_name;
    }

    public void setExpend_name(String expend_name) {
        this.expend_name = expend_name;
    }
}
