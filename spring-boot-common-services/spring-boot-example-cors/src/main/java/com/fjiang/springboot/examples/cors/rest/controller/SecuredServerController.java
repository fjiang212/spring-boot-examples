package com.fjiang.springboot.examples.cors.rest.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
class SecuredServerController{
     
    @RequestMapping("/secured")
    public String secured(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Inside secured()");
        return "Hello user !!! : " + new Date();
    }
}