package com.sb.latest.hibernate.validate.api;

import javax.validation.Valid;

import com.sb.latest.hibernate.validate.model.UserModel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping(path="/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel postRoute(@Valid @RequestBody UserModel userModel) {
        return userModel;
    }
}