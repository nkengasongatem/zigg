package com.zigg;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class ZiggApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(ZiggApplicationLifeCycle.class);

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("__________.__");                  
        LOGGER.info("\\____    /|__|   ____     ____ ");
        LOGGER.info("  /     / |  |  / ___\\  / ___\\"); 
        LOGGER.info(" /     /_ |  | / /_/  > / /_/  >");
        LOGGER.info("/_______ \\|__| \\___  /  \\___  / ");
        LOGGER.info("        \\/    /_____/  /_____/  ");
        LOGGER.info("              Powered by Quarkus");
        LOGGER.info("The application Zigg is starting with profile " + ProfileManager.getActiveProfile());
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application Zigg is stopping...");
    }

}
