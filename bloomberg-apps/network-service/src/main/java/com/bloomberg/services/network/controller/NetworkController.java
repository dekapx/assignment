package com.bloomberg.services.network.controller;

import com.bloomberg.services.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/network")
public class NetworkController {
    @Autowired
    private NetworkService networkService;

    @GetMapping(value = "multiply/{firstArg}/{secondArg}", produces = "application/json")
    public int multiply(@PathVariable int firstArg, @PathVariable int secondArg) {
        return networkService.multiply(firstArg, secondArg);
    }
}
