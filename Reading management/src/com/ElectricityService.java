package com;

import model.Elecricity; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/electric") 
public class ElectricityService {
	
	Elecricity eleconnec = new Elecricity();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCons()
	 {
		return eleconnec.readCons();
	 } 

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCon(@FormParam("Accountno") String Accountno,
	 @FormParam("Consumername") String Consumername,
	 @FormParam("Address") String Address,
	 @FormParam("Consumedunit") Integer Consumedunit,
	 @FormParam("District") String District,
	 @FormParam("Month") String Month)
	{
		
	 String output = eleconnec.insertCon(Accountno,Consumername, Address,Consumedunit,District,Month);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCon(String ReaderData)
	{
	//Convert the input string to a JSON object
	 JsonObject ConObject = new JsonParser().parse(ReaderData).getAsJsonObject();
	//Read the values from the JSON object
	 String AccountCode = ConObject.get("AccountCode").getAsString();
	 String Accountno = ConObject.get("Accountno").getAsString();
	 String Consumername = ConObject.get("Consumername").getAsString();
	 String Address = ConObject.get("Address").getAsString();
	 String Consumedunit = ConObject.get("Consumedunit").getAsString();
	 String District = ConObject.get("District").getAsString();
	 String Month = ConObject.get("Month").getAsString();
	 
	 
	 String output = eleconnec.updateCon(AccountCode, Accountno,Consumername,Address,Consumedunit,District,Month);
	 
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCons(String ReaderData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ReaderData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String AccountCode = doc.select("AccountCode").text();
	 String output = eleconnec.deleteCons(AccountCode);
	return output;
	}

	

}
