package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.DATA.Arbiter;

import com.example.oenskeseddel.temp.Bruger;
import com.example.oenskeseddel.temp.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;


@org.springframework.stereotype.Controller
public class Controller {
     Arbiter arbiter = new Arbiter();



    @GetMapping("/")
    public String index(){return "index";}

    @GetMapping("/LogInd")
    public String LogIndSide(){return "LogInd";}

    @GetMapping("/OpretBruger")
    public String OpretBrugerSide(){return "OpretBruger";}

    @GetMapping("/DinØnskeListe")
    public String DinØnskeliste(){return "DinØnskeListe";}




    @PostMapping("/DinØnskeListe")
    public String OpretListe(@ModelAttribute List list){

        arbiter.addWishToWishlistFromView(list.getWish(),UserID);

        return "redirect:/DinØnskeListe";
    }



//   v
    @PostMapping("/Opret Bruger")
    public String OpretBruger(@ModelAttribute Bruger bruger) throws SQLException {

        arbiter.createUser(bruger.getEmail(), bruger.getUsername(), bruger.getPassword(), bruger.getPasswordRE());

        return "redirect:/Opret Bruger";
    }
//   ^

    @GetMapping("/MinØnskeListe")
    public String getWishList(){

        return "bruh";
    }
    @PostMapping("/MinØnskeListe")
    public String addWishToWishlist(){

        return "bruh";
    }


    }


