package com.example.oenskeseddel.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String index(){return "index";}

    @GetMapping("/Log Ind")
    public String LogInd(){return "LogInd";}

    @GetMapping("/Opret Bruger")
    public String OpretBruger(){return "OpretBruger";}

    @GetMapping("/MinØnskeListe")
    public String minØnskeliste(){return "MinØnskeListe";}

    @PostMapping("/bruh")
    public String bruh(WebRequest ønske){

        return "redirect:/minØnskeliste";
    }

}
