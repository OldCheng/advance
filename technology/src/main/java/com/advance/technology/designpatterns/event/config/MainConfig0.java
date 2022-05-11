package com.advance.technology.designpatterns.event.config;

import com.advance.technology.designpatterns.event.EventListener;
import com.advance.technology.designpatterns.event.EventMulticaster;
import com.advance.technology.designpatterns.event.SimpleEventMulticaster;
import com.advance.technology.designpatterns.event.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@ComponentScan
public class MainConfig0 {

    /**
     * 注册一个bean：事件发布者
     *
     * @param eventListeners
     * @return
     */
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) { //@1
        EventMulticaster eventPublisher = new SimpleEventMulticaster();
        if (eventListeners != null) {
            eventListeners.forEach(eventPublisher::addEventListener);
        }
        return eventPublisher;
    }

    /**
     * 注册一个bean：用户注册服务
     *
     * @param eventMulticaster
     * @return
     */
    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster) { //@2
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }

    /**
     * 上面有2个方法，负责向spring容器中注册2个bean。
     * @1：向spring容器中注册了一个bean：事件发布者，方法传入了EventListener类型的List，
     *      这个地方会将容器中所有的事件监听器注入进来，丢到EventMulticaster中。
     * @2：向spring容器中注册了一个bean：用户注册服务
     */
}
