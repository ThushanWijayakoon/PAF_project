package org.ElectroGrid.Project.Model;

public class Bills {

    private int BillNo;
    private String Name;
    private String AccountNo;
    private float BillPayment;
    private String Date ; 

    public Bills() {
    	super();
    }

    public Bills(int i, String Name, String AccountNo, float BillPayment, String Date) {
    	super();
        this.BillNo = i;
        this.Name = Name;
        this.AccountNo = AccountNo;
        this.BillPayment = BillPayment;
        this.Date = Date;
    }

    public int getBillNo() {
        return BillNo;
    }

    public void setBillNo(int billno) {
        this.BillNo = billno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountno) {
        this.AccountNo = accountno;
    }
    
    public float getBillPayment() {
        return BillPayment;
    }

    public void setBillPayment(float billpayment) {
        this.BillPayment = billpayment;
    }
    
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}