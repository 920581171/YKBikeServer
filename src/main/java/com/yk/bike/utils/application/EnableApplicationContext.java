package com.yk.bike.utils.application;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 92058
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(EnableApplicationContext.ApplicationContextRegister.class)
public @interface EnableApplicationContext {
    class ApplicationContextRegister extends AbstractComponentRegister {
        @Override
        public Class<?>[] classes() {
            return new Class[]{GlobalApplicationContext.class};
        }
    }
}
