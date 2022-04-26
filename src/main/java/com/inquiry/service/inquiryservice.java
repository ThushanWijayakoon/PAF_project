package com.inquiry.service;

import com.inquiry.model.inquirymodel;

import java.sql.*;
import java.util.ArrayList;

public class inquiryservice {
    Connection con;


    public inquiryservice(){

        try {
            String url ="jdbc:mysql://localhost:3306/ceb";
            String uname ="root";
            String pwd = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);

        } catch(Exception e) {
            System.out.println(e +" data insert unsuccess.");
        }
    }


    public inquirymodel insertInquiry(inquirymodel inquiry) {
        String insert = "insert into inquiry(inquiryId,inquiryTitle,inquiryType,inquiryDate) values(?,?,?,?) ";

        try {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setLong(1, inquiry.getInquiryId());
            ps.setString(2, inquiry.getInquiryTitle());
            ps.setString(3, inquiry.getInquiryType());
            ps.setString(4, inquiry.getInquiryDate());

            ps.execute();
        }catch(Exception e) {
            System.out.println(e +"data insert unsuccess.");
        }

        return inquiry;

    }

    public ArrayList<inquirymodel> getInquiry() throws SQLException {

        ArrayList<inquirymodel> data = new ArrayList<inquirymodel>();

        String select = "select * from inquiry";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            inquirymodel model = new inquirymodel();

            model.setInquiryId(rs.getInt("inquiryId"));
            model.setInquiryTitle(rs.getString("inquiryTitle"));
            model.setInquiryType(rs.getString("inquiryType"));
            model.setInquiryDate(rs.getString("inquiryDate"));

            data.add(model);

        }

        return data;

    }


    public ArrayList<inquirymodel> getInquiryById(int inquiryId) throws SQLException{

        ArrayList<inquirymodel> data = new ArrayList<inquirymodel>();
        String select = "select * from inquiry where inquiryId =?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,inquiryId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            inquirymodel model = new inquirymodel();

            model.setInquiryId(rs.getInt("inquiryId"));
            model.setInquiryTitle(rs.getString("inquiryTitle"));
            model.setInquiryType(rs.getString("inquiryType"));
            model.setInquiryDate(rs.getString("inquiryDate"));

            data.add(model);
        }
        return data;
    }

    public inquirymodel updateInquiry(inquirymodel inquiry) {
        String insert = "update inquiry set inquiryTitle=?, inquiryType=?, inquiryDate=? where inquiryId=?";

        try {
            PreparedStatement ps = con.prepareStatement(insert);

            ps.setString(1, inquiry.getInquiryTitle());
            ps.setString(2, inquiry.getInquiryType());
            ps.setString(3, inquiry.getInquiryDate());
            ps.setLong(4, inquiry.getInquiryId());

            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println(e +"data insert unsuccess.");
        }

        return inquiry;

    }


    public int deleteInquiry(int inquiryId) {
        String insert = "delete from inquiry where inquiryId =?";

        try {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,inquiryId);

            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println(e +"data insert unsuccess.");
        }
        return inquiryId;
    }
}
