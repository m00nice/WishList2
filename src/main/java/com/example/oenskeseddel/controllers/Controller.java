package com.example.oenskeseddel.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String index(){return "index";}

    @GetMapping("/Log Ind")
    public String LogIndSide(){return "LogInd";}

    @GetMapping("/Opret Bruger")
    public String OpretBrugerSide(){return "OpretBruger";}

    @GetMapping("/DinØnskeListe")
    public String DinØnskeliste(){return "DinØnskeListe";}

    @PostMapping("/OpretterListe")
    public String OpretListe(WebRequest ønske){

        return "redirect:/DinØnskeliste";
    }
    @PostMapping("/Opretter Bruger Nu")
    public String OpretBruger(WebRequest email, WebRequest username, WebRequest password){

        return "redirect:/DinØnskeliste";
    }
    @PostMapping("/Loger Ind Nu")
    public String LogInd(WebRequest username, WebRequest password){
        if (true){
            return "redirect:/DinØnskeliste";
        }else {
            return "redirect:/DinØnskeliste";
        }

    }

}
