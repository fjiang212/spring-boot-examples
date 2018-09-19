package com.fjiang.springboot.exmaples.es.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fjiang.springboot.exmaples.es.service.AccountService;

@RestController
public class AccountCountroller {

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/search")
    public ResponseEntity<Object> sayHello() {
        return new ResponseEntity<Object>(accountService.searchAccount(), HttpStatus.OK);
    }
}
