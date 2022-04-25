package com; 
import model.Item; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Users") 
public class ItemService 
{ 
	Item userObj = new Item(); 
 @GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 { 
 return userObj.readUsers(); 
}
 
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("userName") String userName, 
  @FormParam("userNIC") String userNIC, 
  @FormParam("contactNumber") String contactNumber, 
  @FormParam("userType") String userType) 
 { 
  String output = userObj.insertUser(userName,userNIC,contactNumber,userType); 
 return output; 
 }

 
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String userData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
 //Read the values from the JSON object
  String userID = userObject.get("userID").getAsString(); 
  String UserName = userObject.get("UserName").getAsString(); 
  String userNIC = userObject.get("userNIC").getAsString(); 
  String contactNumber = userObject.get("contactNumber").getAsString(); 
  String  userType = userObject.get("userType").getAsString(); 
  String output = userObj.updateUser(userID, UserName, userNIC, contactNumber, userType); 
 return output; 
 }

 
 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String userData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String userID = doc.select("userID").text(); 
  String output = userObj.deleteUser(userID); 
 return output; 
 }
 
}