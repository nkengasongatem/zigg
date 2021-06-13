package com.zigg.eneo.blackouts.control;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

import com.zigg.eneo.blackouts.boundary.BlackoutRestClient;
import com.zigg.eneo.blackouts.entity.BlackoutRequest;
import com.zigg.eneo.blackouts.entity.BlackoutResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BlackoutService {

    @Inject
    @RestClient
    private BlackoutRestClient blackoutClient;

    private static final Logger LOGGER = Logger.getLogger(BlackoutService.class.getName());

    public BlackoutResponse getBlackoutData(final BlackoutRequest request) {
        LOGGER.info("Getting blackout data from Eneo");
        final String json = blackoutClient.getBlackoutData(request.region, request.localite);
        try (var jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(json, BlackoutResponse.class);
        } catch (Exception exception) {
            throw new JsonbException(exception.getMessage());
        }
    }

}
