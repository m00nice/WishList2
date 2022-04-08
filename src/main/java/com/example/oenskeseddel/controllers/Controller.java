package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.DATA.Arbiter;

import com.example.oenskeseddel.temp.Bruger;
import com.example.oenskeseddel.temp.WList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@org.springframework.stereotype.Controller
public class Controller {

     Arbiter arbiter = new Arbiter();

     int UserID = 0;

    @GetMapping("/")
    public String index(){return "index";}

    //USER @Martin Anberg

    @RequestMapping("/LogInd")
    public String LogIndSide(){
        return "LogInd";
    }

    @PostMapping("/LogInd")
    public String LogInd(@ModelAttribute(name="bruger") Bruger bruger, Model model) throws SQLException {
        String username = bruger.getUsername();
        String password = bruger.getPassword();
        model.addAttribute(username, password);
        UserID = arbiter.InGetUserID(username, password);
        if(UserID != 0){
            return "DinØnskeListe";
        }else {
            return "LogInd";
        }
    }

    @GetMapping("/OpretBruger")
    public String OpretBrugerSide(){
        return "OpretBruger";
    }

    @PostMapping("/OpretBruger")
    public String OpretBruger(@ModelAttribute(name = "bruger") Bruger bruger) throws SQLException {

        if(arbiter.createUser(bruger.getEmail(), bruger.getUsername(), bruger.getPassword(), bruger.getPasswordRE())){
            UserID = arbiter.InGetUserID(bruger.getUsername(), bruger.getPassword());
            return "DinØnskeListe";
        }

        return "OpretBruger";
    }




    //WISHLIST @Martin Anberg
    @GetMapping("/DinØnskeListe")
    public String createFirstWishList(@ModelAttribute("wishlist") Model model) throws SQLException {
        WList wishlist = new WList();
        wishlist.createWList(arbiter.postWishListToView(UserID));
        model.addAttribute("wish",wishlist);
        return "DinØnskeListe";
    }

    @PostMapping("/DinØnskeListe")
    public String addWishToWishlist(@ModelAttribute WList wlist) throws SQLException {
        arbiter.addWishToWishlistFromView(wlist.getWish(),UserID);
        return "DinØnskeListe";
    }



    }


