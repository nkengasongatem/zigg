package com.zigg.eneo.blackouts.control;


import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.zigg.eneo.blackouts.boundary.BlackoutRestClient;
import com.zigg.eneo.blackouts.entity.BlackoutResponse;
import com.zigg.eneo.blackouts.entity.BlackoutResquest;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BlackoutService {

    @Inject
    @RestClient
    BlackoutRestClient blackoutClient;

    private static final Logger LOGGER = Logger.getLogger(BlackoutService.class.getName());

    public BlackoutResponse getBlackoutData(final BlackoutResquest request) {
        LOGGER.info("Getting blackout data from Eneo");
        return blackoutClient.getBlackoutData(request);
    }

}
