package edu.ntu.sau.javasb.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {



    @GetMapping("/")
    String getWelcome(Model model) {

        return "welc";
    }

}
