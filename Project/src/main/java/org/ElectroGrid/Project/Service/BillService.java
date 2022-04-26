package org.ElectroGrid.Project.Service;

import java.util.List;

import org.ElectroGrid.Project.Model.Bills;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.ElectroGrid.Project.DataBase.BillDAO;


@Path("/Bills")
public class BillService {
	
	private BillDAO billDAO = new BillDAO();

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Bills> getBills()
	{
		List<Bills> listOfBills=billDAO.getAllBills();
		return listOfBills;
	}
 
    @GET
    @Path("/{billno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bills getBillNo(@PathParam("billno") int billno)
    {
    	return billDAO.getBills(billno);
    }
 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Bills addBills(Bills bills)
    {
    	return billDAO.addBills(bills);
    }
 
    @PUT
    @Path("/{billno}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Bills updateBills(Bills bills)
    {
    	return billDAO.updateBills(bills);
 
    }
 
    @DELETE
    @Path("/{billno}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteBills(@PathParam("billno") int billno)
    {
    	if (billDAO.deleteBills(billno) != null) {
    	        return ("BillNo "+(billno)+" Deleted Successfully");
        } 
    	else {
            return "Deletion Failed";
        }
    }
 
}
