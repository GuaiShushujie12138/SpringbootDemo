package com.jarvis.springboot.web.event;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DemoEvent extends ApplicationEvent {
    private final DemoEventObject demoEventObject;

    @Builder
    public DemoEvent(Object source, DemoEventObject demoEventObject) {
        super(source);
        this.demoEventObject = demoEventObject;
    }
}
