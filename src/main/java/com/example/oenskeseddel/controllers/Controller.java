package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.DATA.ListDatahandler;
import com.example.oenskeseddel.DATA.UserDatahandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
public class Controller {
    UserDatahandler userDatahandler = new UserDatahandler();
    ListDatahandler listDatahandler = new ListDatahandler();

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
    public String OpretBruger(WebRequest email, WebRequest username, WebRequest password,WebRequest passwordRE) throws SQLException {
        if (password.equals(passwordRE)|| userDatahandler.isEmailValid(email.getParameter(String.valueOf(email)))){
            userDatahandler.OpretBruger(String.valueOf(email),String.valueOf(username),String.valueOf(password));
            return "redirect:/DinØnskeliste";
        }else{
            return "redirect:/Opret Bruger";
        }



    }
    @PostMapping("/Logger Ind Nu")
    public String LogInd(WebRequest username, WebRequest password){
        if (true){
            return "redirect:/DinØnskeliste";
        }else {
            return "redirect:/DinØnskeliste";
        }

    }

}
