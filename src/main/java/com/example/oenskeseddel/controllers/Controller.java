package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.DATA.Arbiter;

import com.example.oenskeseddel.temp.Bruger;
import com.example.oenskeseddel.temp.WList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@org.springframework.stereotype.Controller
public class Controller {

     Arbiter arbiter = new Arbiter();


    @GetMapping("/")
    public String index(){return "index";}

    //USER @Martin Anberg

    @RequestMapping("/LogInd")
    public String LogIndSide(){
        return "LogInd";
    }

    @PostMapping("/LogInd")
    public String LogInd(@ModelAttribute(name="bruger") Bruger bruger, Model model, HttpSession session) throws SQLException {
        String username = bruger.getUsername();
        String password = bruger.getPassword();
        model.addAttribute(username, password);
        int UserID = arbiter.confirmLogIn(username, password);
        if(UserID != 0){
            session.setAttribute("id",UserID);
            return "DinØnskeListe";
        }else {
            return "LogInd";
        }
    }

    @GetMapping("/OpretBruger")
    public String OpretBrugerSide(){
        return "OpretBruger";
    }

    @PostMapping("/Opret Bruger")
    public String OpretBruger(@ModelAttribute(name = "bruger") Bruger bruger,HttpSession session) throws SQLException {

        if(arbiter.createUser(bruger.getEmail(), bruger.getUsername(), bruger.getPassword(), bruger.getPasswordRE())){
            int UserID = arbiter.confirmLogIn(bruger.getUsername(), bruger.getPassword());
            session.setAttribute("id",UserID);
            return "DinØnskeListe";
        }

        return "OpretBruger";
    }




    //WISHLIST @Martin Anberg
    @GetMapping("/DinØnskeListe")
    public String createFirstWishList(@ModelAttribute("wishlist") Model model,@RequestParam int UserID) throws SQLException {
        WList wishlist = new WList();
        wishlist.createWList(arbiter.postWishListToView(UserID));
        model.addAttribute("wish",wishlist);
        return "DinØnskeListe";
    }

    @PostMapping("/DinØnskeListe")
    public String addWishToWishlist(@ModelAttribute WList wlist,@RequestParam int UserID) throws SQLException {
        arbiter.addWishToWishlistFromView(wlist.getWish(),UserID);
        return "DinØnskeListe";
    }



    }


