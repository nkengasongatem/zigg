package com.zigg.eneo.blackouts.boundary;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zigg.eneo.blackouts.control.BlackoutService;
import com.zigg.eneo.blackouts.entity.BlackoutResponse;
import com.zigg.eneo.blackouts.entity.BlackoutRequest;

@Path("/api/blackouts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackoutResource {

    @Inject
    BlackoutService blackoutService;

    @GET
    public Response get() {
        return Response.ok(new BlackoutResponse()).build();
    }

    @POST
    public Response getBlackouts(@Valid BlackoutRequest request) {
        final BlackoutResponse response = blackoutService.getBlackoutData(request);
        return Response.ok(response).build();
    }

}
