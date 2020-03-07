package com.bloomberg.services.calc.controller;

import com.bloomberg.services.calc.model.MultiplyRequest;
import com.bloomberg.services.calc.model.MultiplyResponse;
import com.bloomberg.services.calc.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calc/")
public class CalcController {
    @Autowired
    private CalcService calcService;

    @PostMapping(value = "/multiply", produces = "application/json")
    public ResponseEntity<MultiplyResponse> multiply(@RequestBody MultiplyRequest request) {
        final MultiplyResponse response = calcService.multiply(request);
        return ResponseEntity.ok(response);
    }
}
