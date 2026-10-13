package com.example.mymoodapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * トップURLから記録一覧へ移動するControllerクラス。
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/records";
    }
}
