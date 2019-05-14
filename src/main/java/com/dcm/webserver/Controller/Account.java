package com.dcm.webserver.Controller;

import com.dcm.webserver.Model.User;
import com.dcm.webserver.Util.JSONResult;
import com.dcm.webserver.Util.JSONUtils;
import com.dcm.webserver.WebServer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import java.util.Map;

@RestController
public class Account {

    @Autowired
    private WebServer webServer;

    /**
     *
     * @param data json格式数据，包含user信息
     * @return JSONResult：向数据库服务器发送数据的结果
     */
    @PostMapping("/user/add")
    public JSONResult addUser(@RequestBody Map<String,String> data)
    {
        System.out.println(data);
        String vehicleId=data.get("vehicleId").toString();
        System.out.println(vehicleId);
        char[] c=new char[1];
        vehicleId.getChars(0,1,c,0);
        System.out.println(c);
        String ch=new String(c);
        System.out.println(ch);
        String location1=new String("沪");
        String location2=new String("粤");
        String location3=new String("京");

        if(ch.equals(location1)){
            System.out.println("准备发送数据");
            Destination destination=new ActiveMQQueue("addusersh");
            webServer.sendAddSUser(destination,data);
        }
        else if(ch.equals(location2)){
            System.out.println("准备发送数据");
            Destination destination=new ActiveMQQueue("guangzhouServer");
            webServer.sendAddSUser(destination,data);
        }
        else if(ch.equals(location3)){
            System.out.println("准备发送数据");
            Destination destination=new ActiveMQQueue("beijingServer");
            webServer.sendAddSUser(destination,data);
        }
        else{
            return JSONResult.errorMsg("该车牌号码暂时设计没有服务器存储相关信息");
        }
        return JSONResult.ok();
    }

    @PostMapping("/checkuser")
    public JSONResult checkUser(@RequestBody Map<String,String>data){
        User user=null;
        try{
            Destination destination=new ActiveMQTopic("checkuser.topic");
            webServer.checkUser(destination,data);
            user=webServer.getUser();
            System.out.println(user.getUsername());
        }catch(Exception e){
            System.out.println("数据库服务器已宕机，请检查后重试");
            return JSONResult.errorMsg("数据库服务器已宕机，攻城狮正在紧急修复");
        }
        return JSONResult.ok();
    }

}
