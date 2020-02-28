package controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {

    @GetMapping("/welcome")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }
    
}