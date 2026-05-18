package de.db.webapp.presentation.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequestMapping("/demo")
@RequestScope
public class DemoController {


    @GetMapping("/gruss")
    public String gruss() {
        return "Hallo Rest";
    }
}
