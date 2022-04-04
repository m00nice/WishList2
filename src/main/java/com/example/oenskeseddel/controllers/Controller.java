package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.DATA.Arbiter;

import com.example.oenskeseddel.temp.Bruger;
import com.example.oenskeseddel.temp.WList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;


@org.springframework.stereotype.Controller
public class Controller {
     Arbiter arbiter = new Arbiter();



    @GetMapping("/")
    public String index(){return "index";}

    @GetMapping("/LogInd")
    public String LogIndSide(Model model){
        model.addAttribute("bruger", new Bruger());
        return "LogInd";
    }



    @PostMapping("/LogInd")
    public String LogInd(@ModelAttribute Bruger bruger) throws SQLException {

        if(arbiter.confirmLogIn(bruger.getUsername(), bruger.getPassword())){
            return "/DinØnskeListe";
        }
        else {return "redirect:/LogInd";}
    }


    @GetMapping("/OpretBruger")
    public String OpretBrugerSide(Model model){
        model.addAttribute("bruger", new Bruger());
        return "OpretBruger";
    }


//   v
    @PostMapping("/Opret Bruger")
    public String OpretBruger(@ModelAttribute Bruger bruger) throws SQLException {

        arbiter.createUser(bruger.getEmail(), bruger.getUsername(), bruger.getPassword(), bruger.getPasswordRE());

        return "redirect:/Opret Bruger";
    }
//   ^

    @GetMapping("/DinØnskeListe")
    public String getWishList(Model model){
        model.addAttribute("wlist", new WList());
        return "/DinØnskeListe";
    }
    @PostMapping("/DinØnskeListe")
    public String addWishToWishlist(@ModelAttribute WList wlist) throws SQLException {
        int UserID = 0;
        arbiter.addWishToWishlistFromView(wlist.getWish(),UserID);
        return "/DinØnskeListe";
    }
    @GetMapping("/DinØnskeListe/empty")
    public String createFirstWishList(Model model) throws SQLException {
        int UserID = 1;
        WList wishlist = new WList();
        wishlist.createWList(arbiter.postWishListToView(UserID));
        model.addAttribute("wish",wishlist);
         return "wish";
    }


    }


