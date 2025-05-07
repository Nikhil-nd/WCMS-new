package org.nwf.wcms.Aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log before adding an observation
    @Before("execution(* org.nwf.wcms.Service.ObservationService.createObservation(..))")
    public void logBeforeCreateObservation() {
        logger.info("Attempting to add a new observation...");
    }

    // Log after adding an observation
    @After("execution(* org.nwf.wcms.Service.ObservationService.createObservation(..))")
    public void logAfterCreateObservation() {
        logger.info("Successfully added a new observation.");
    }

    // Log before updating an observation
    @Before("execution(* org.nwf.wcms.Service.ObservationService.updateObservation(..))")
    public void logBeforeUpdateObservation() {
        logger.info("Attempting to update an observation...");
    }

    // Log after updating an observation
    @After("execution(* org.nwf.wcms.Service.ObservationService.updateObservation(..))")
    public void logAfterUpdateObservation() {
        logger.info("Successfully updated an observation.");
    }
}