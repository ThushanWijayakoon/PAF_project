package model; 
import java.sql.*; 
public class Item 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement", "root", "1998"); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertUser(String username, String usernic, String contactnumber, String usertype) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into userdetails (`userID`,`userName`,`userNIC`,`contactNumber`,`userType`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, username); 
 preparedStmt.setString(3,usernic ); 
 preparedStmt.setString(4, contactnumber); 
 preparedStmt.setString(5, usertype); 
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


public String readUsers() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>User Nic</th>" +
 "<th>User Nic</th>" + 
 "<th>contactNumber</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from userdetails"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String userID = Integer.toString(rs.getInt("userID")); 
 String userName = rs.getString("userName"); 
 String userNIC = rs.getString("userNIC"); 
 String contactNumber = rs.getString("contactNumber"); 
 String userType = rs.getString("userType"); 
 // Add into the html table
 output += "<tr><td>" + userName + "</td>"; 
 output += "<td>" + userNIC + "</td>"; 
 output += "<td>" + contactNumber + "</td>"; 
 output += "<td>" + userType + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='Users.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='userID' type='hidden' value='" + userID
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the users."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String updateUser(String userID, String username, String usernic, String contactnumber, String usertype) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE userdetails SET UserName=?,userNIC=?,contactNumber=?,userType=? WHERE userID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, username); 
 preparedStmt.setString(2, usernic); 
 preparedStmt.setString(3, contactnumber); 
 preparedStmt.setString(4, usertype); 
 preparedStmt.setInt(5, Integer.parseInt(userID)); 
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

public String deleteUser(String userID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from userdetails where userID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(userID)); 
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
