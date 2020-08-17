package com.yk.bike.utils.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class GlobalApplicationContext {

    public static GlobalApplicationContext instance;

    private final ApplicationContext applicationContext;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GlobalApplicationContext(ApplicationContext applicationContext) {
        log.info("Common: Initializing GlobalApplicationContext");
        instance = this;
        this.applicationContext = applicationContext;
    }

    public ApplicationContext context() {
        return applicationContext;
    }
}
