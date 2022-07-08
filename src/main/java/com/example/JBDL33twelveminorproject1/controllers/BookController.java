package com.example.JBDL33twelveminorproject1.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    //wiring custom property
    @Value("${custom.my_prop}")
    String sampleText;

    @GetMapping("/sample")
    public String getSampleText(){
        return this.sampleText;
    }

}
