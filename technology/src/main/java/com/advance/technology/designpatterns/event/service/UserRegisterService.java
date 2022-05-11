package com.advance.technology.designpatterns.event.service;

import com.advance.technology.designpatterns.event.EventMulticaster;
import com.advance.technology.designpatterns.event.UserRegisterSuccessEvent;

/**
 * 用户注册服务
 *
 * 负责实现用户注册逻辑
 *
 * @0：事件发布者
 * @1：registerUser这个方法负责用户注册，内部主要做了2个事情
 * @2：模拟将用户信息落库
 * @3：使用事件发布者eventPublisher发布用户注册成功的消息:
 *
 *
 * 上面将注册的主要逻辑（用户信息落库）和次要的业务逻辑（发送邮件）通过事件的方式解耦了。
 * 次要的业务做成了可插拔的方式，比如不想发送邮件了，只需要将邮件监听器上面的@Component注释就可以了，非常方便扩展。
 */
public class UserRegisterService {
    //事件发布者
    private EventMulticaster eventMulticaster; //@0

    /**
     * 注册用户
     *
     * @param userName 用户名
     */
    public void registerUser(String userName) { //@1
        //用户注册(将用户信息入库等操作)
        System.out.println(String.format("用户【%s】注册成功", userName)); //@2
        //广播事件
        this.eventMulticaster.multicastEvent(new UserRegisterSuccessEvent(this, userName)); //@3
    }

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}
