package com.zigg.eneo.blackouts.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zigg.eneo.blackouts.entity.BlackoutResponse;
import com.zigg.eneo.blackouts.entity.Subscription;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/ajaxOutage.php")
@RegisterRestClient
public interface BlackoutRestClient {

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    String getBlackoutData(@FormParam("region") String region, @FormParam("localite") String localite);
}
