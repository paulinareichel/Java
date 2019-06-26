package com.laby.laby10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {
    @GetMapping("/image")
    public String index(){
        return "Upload";
    }
}
