package com.jarvis.springboot.web.event.publisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * application event publisher
 */
@Component
public class ApplicationEventPublisher {
    private final ApplicationContext applicationContext;

    public ApplicationEventPublisher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void publish(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}
