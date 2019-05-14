package com.dcm.webserver;

import com.dcm.webserver.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Map;

@Component
public class WebServer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    // 使用JmsListener配置消费者监听的队列


    @SendTo("addusersh")
    public void sendAddSUser(Destination destination,Map<String, String> request){
        System.out.println("发送请求");
        jmsMessagingTemplate.convertAndSend(destination,request);
    }


    @SendTo("adduserbj")
    public void sendAddBUser(Destination destination,Map<String,String> request){
        System.out.println("发送请求");
        jmsMessagingTemplate.convertAndSend(destination,request);
    }

    @SendTo("addusergz")
    public void SendAddGUser(Destination destination,Map<String,String> request){
        System.out.println("发送请求");
        jmsMessagingTemplate.convertAndSend(destination,request);
    }

    @SendTo("checkuser")
    public void checkUser(Destination destination,Map<String,String> request){
        System.out.println("发送用户验证请求");
        jmsMessagingTemplate.convertAndSend(destination,request);
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JmsListener(destination= "res_usersh",containerFactory = "queueListenerFactory")
    public void resultgetSUser(ObjectMessage u) throws JMSException {
        System.out.println(u);
        User user=new User();
        BeanUtils.copyProperties(user,u.getObject());
        System.out.println(user.getUsername());
        this.setUser(user);
    }

    @JmsListener(destination= "res_userbj")
    public User resultgetBUser(User user){
        return user;
    }

    @JmsListener(destination= "res_usergz")
    public User resultgetGUser(User user){
        return user;
    }

    @JmsListener(destination = "res_adduser")
    public void resultAddSUser(String text){
        System.out.println(text.equals("0")?"added user sucessful":"added user failed");
    }



}
