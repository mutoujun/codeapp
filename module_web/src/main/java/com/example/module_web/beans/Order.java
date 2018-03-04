package com.example.module_web.beans;

/**
 * Created by Administrator on 2018/2/25.
 */

public class Order {

    /**
     * number : 1
     * key : 1061
     * date : 2018-03-02
     * account : t_1510410447415_0886
     * product : 补水五件套+补水面膜
     * payablePrice : 318.0
     * paidPrice : 318.0
     * giftPrice : 2.0
     * contract : 钱明娣，13861367629
     * address : 江苏省 镇江市 扬中市 新坝镇 扬中市新坝申扬换热设备有限公司南自路2号 ，212200
     * source :
     * remark : 
     * label :
     */

    private int number;
    private int key;
    private String date;
    private String account;
    private String product;
    private double payablePrice;
    private double paidPrice;
    private double giftPrice;
    private String contract;
    private String address;
    private String source;
    private String remark;
    private String label;

    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }

    public int getkey() {
        return key;
    }

    public void setkey(int key) {
        this.key = key;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getaccount() {
        return account;
    }

    public void setaccount(String account) {
        this.account = account;
    }

    public String getproduct() {
        return product;
    }

    public void setproduct(String product) {
        this.product = product;
    }

    public double getpayablePrice() {
        return payablePrice;
    }

    public void setpayablePrice(double payablePrice) {
        this.payablePrice = payablePrice;
    }

    public double getpaidPrice() {
        return paidPrice;
    }

    public void setpaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public double getgiftPrice() {
        return giftPrice;
    }

    public void setgiftPrice(double giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getcontract() {
        return contract;
    }

    public void setcontract(String contract) {
        this.contract = contract;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getsource() {
        return source;
    }

    public void setsource(String source) {
        this.source = source;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }

    public String getlabel() {
        return label;
    }

    public void setlabel(String label) {
        this.label = label;
    }
}
