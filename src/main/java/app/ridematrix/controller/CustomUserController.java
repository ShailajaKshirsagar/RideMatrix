package app.ridematrix.controller;

import app.ridematrix.entity.CustomUser;
import app.ridematrix.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CustomUserController {

    @Autowired
    private CustomUserService customUserService;

    //save User
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody CustomUser user){
        String msg = customUserService.saveUser(user);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
