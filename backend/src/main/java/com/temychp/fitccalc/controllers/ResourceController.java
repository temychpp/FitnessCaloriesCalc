package com.temychp.fitccalc.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ResourceController {

    @GetMapping("")
    public String get() {
        return "Hello";
    }

    @GetMapping("/home")
    public String getHome() {
        return "Home Page!";
    }

    @GetMapping("/login")
    public String loginGetEndpoint() {
        return "Login!";
    }

    @GetMapping("/admin/hello")
    public String adminEndpoint() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User!";
    }

    @GetMapping("/all")
    public String allRolesEndpoint() {
        return "All Roles!";
    }

    @GetMapping("/error")
    public String errorEndpoint() {
        return "Такой страницы нет!";
    }

    @DeleteMapping("/delete")
    public String deleteEndpoint(@RequestBody String s) {
        return "I am deleting " + s;
    }
}
