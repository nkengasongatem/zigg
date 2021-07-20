package com.zigg.eneo.blackouts.boundary;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.NotFoundException;

import com.zigg.eneo.blackouts.control.BlackoutService;
import com.zigg.eneo.blackouts.entity.Blackout;
import com.zigg.eneo.blackouts.entity.BlackoutRequest;
import com.zigg.eneo.blackouts.entity.BlackoutResponse;
import com.zigg.eneo.blackouts.entity.Subscription;

import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;

@ServerEndpoint("/subscribe")
@ApplicationScoped
public class SubcriptionResource {

    @Inject
    BlackoutService blackoutService;

    private static final Logger LOG = Logger.getLogger(SubcriptionResource.class);

    /**
     * Save user subscription
     */
    @OnOpen
    public void onOpen(Subscription subscription) {
        Subscription.persist(subscription);
    }

    @OnClose
    public void onClose(Subscription subscription) {
        Subscription entity = Subscription.findByEndpoint(subscription.endpoint);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @OnError
    public void onError(Subscription subscription, Throwable throwable) {
        LOG.error("Error ", throwable);
        this.onClose(subscription);
    }

    /**
     * Send messages to subscribers
     */
    @OnMessage
    public void onMessage(Subscription subscription, Blackout notification) {
        blackoutService.sendNotification(subscription, notification);
    }

    /**
     * Send notification to all subscribers everyday at 7am
     */
    @Scheduled(cron="0 0 7 * * ?")
    void sendNotifications() {
        // TODO: get most relevant blackout - closest and longest
        BlackoutRequest request = new BlackoutRequest();
        request.localite = "6";

        BlackoutResponse response = blackoutService.getBlackoutData(request);
        Blackout blackout = response.data.get(0);
        if (blackout != null) {
            Subscription.listAll().forEach(s -> {
                onMessage((Subscription) s, blackout);
            });
        }
    }

    /**
     * Send notification for specific localities
     */
    @Scheduled(every = "1h")
    void sendNotification() {
    }
}
