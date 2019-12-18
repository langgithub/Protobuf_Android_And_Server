package com.lang.protobufserver.controller;

import com.lang.protobufdemo.entity.Login;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class HelloController {

    @RequestMapping("login")
    public Login.LoginResponse login(HttpServletRequest request) {
        System.out.println("---------------start login---------------------");
        Login.LoginResponse.Builder builder = Login.LoginResponse.newBuilder();

        try {
            request.setCharacterEncoding("utf-8");
            Login.LoginRequest loginData = Login.LoginRequest.parseFrom(request.getInputStream());

            if("name".equals(loginData.getUsername()) && "pwd".equals(loginData.getPassword())) {
                builder.setCode(200).setMsg("login success");
            }else{
                builder.setCode(-200).setMsg("login failed");
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Login.LoginResponse data =builder.build();
        System.out.println("data:" + data);

        return data;
    }
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        System.out.println("---------------start login---------------------");
        return "hello word";
    }
}