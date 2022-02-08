package com.stefanini.taskmanager.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface SendEmail {

    String to();
    String subject() default "";

    EventType event();

    enum EventType {
        USER_CREATED, TASK_CREATED
    }
}

