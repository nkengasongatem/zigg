package com.zigg.eneo.blackouts.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zigg.eneo.blackouts.entity.BlackoutResponse;
import com.zigg.eneo.blackouts.entity.BlackoutResquest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/ajaxOutage.php")
@RegisterRestClient
public interface BlackoutRestClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    BlackoutResponse getBlackoutData(final BlackoutResquest request);

}
