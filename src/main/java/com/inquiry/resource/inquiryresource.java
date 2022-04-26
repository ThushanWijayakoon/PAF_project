package com.inquiry.resource;

import com.inquiry.model.inquirymodel;
import com.inquiry.service.inquiryservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/inquiry")
public class inquiryresource {
    inquiryservice service = new inquiryservice();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public inquirymodel addFund(inquirymodel inquiry) {
        return service.insertInquiry(inquiry);

    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<inquirymodel> getInquiry() throws SQLException {
        return service.getInquiry();

    }

    @Path("/retrieveById/{inquiryId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<inquirymodel>  getInquiry(@PathParam("inquiryId") int inquiryId) throws SQLException {
        return service.getInquiryById(inquiryId);

    }



    @Path("/updateInquiry")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public inquirymodel updateInquiry(inquirymodel inquiry) {
        return service.updateInquiry(inquiry);

    }

    @Path("/deleteInquiryById/{inquiryId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int deleteInquiry(@PathParam("inquiryId") int inquiryId) {
        return service.deleteInquiry(inquiryId);
    }
}
