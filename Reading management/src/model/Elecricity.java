package model;

import java.sql.*;

public class Elecricity {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practicep", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	public String insertCon(String Accountno, String Consumername, String Address,Integer Consumedunit, String District, String Month)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into reeeader(`AccountCode`,`Accountno`,`Consumername`,`Address`,`Consumedunit`,`District`,`Month`)"
	 + " values ( ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, Accountno);
	 preparedStmt.setString(3, Consumername);
	 preparedStmt.setString(4, Address);
	 preparedStmt.setInt(5, Consumedunit);
	 preparedStmt.setString(6, District);
	 preparedStmt.setString(7, Month);
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	
	
	
	public String readCons()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Accountno</th><th>Consumername</th>" +
	 "<th>Address</th>" +
	 " <th>Consumedunit</th>"+" <th>District</th>"+" <th>Month</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from reeeader";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String Accountno = rs.getString("Accountno");
	 String Consumername = rs.getString("Consumername");
	 String Address = rs.getString("Address");
	 String Consumedunit = Integer.toString(rs.getInt("Consumedunit"));
	 String District = rs.getString("District");
	 String Month = rs.getString("Month");
	 
	 // Add into the html table
	 output += "<tr><td>" + Accountno + "</td>";
	 output += "<td>" + Consumername + "</td>";
	 output += "<td>" + Address+ "</td>";
	 output += "<td>" + Consumedunit + "</td>";
	 output += "<td>" + District + "</td>";
	 output += "<td>" + Month + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='AccountNo' type='hidden' value='" + Accountno + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

	public String updateCon(String AccountCode,String Accountno, String Consumername, String Address,String Consumedunit, String District, String Month)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE reeeader SET Accountno=?,Consumername=?,Address=?,Consumedunit=?,District=?,Month=? WHERE AccountCode=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, Accountno);
		 preparedStmt.setString(2, Consumername);
		 preparedStmt.setString(3, Address);
		 preparedStmt.setInt(4, Integer.parseInt(Consumedunit));
		 preparedStmt.setString(5, District);
		 preparedStmt.setString(6, Month);
		 preparedStmt.setInt(7, Integer.parseInt(AccountCode));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	
	public String deleteCons(String AccountCode)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from reeeader where AccountCode=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(AccountCode));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	

	
}
