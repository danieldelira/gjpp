/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cheve360
 */
@Controller
public class SpringController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("hola", "Spring 4 MVC");
        return "index";
    }

    @RequestMapping(value = "/hello_world",  method = RequestMethod.GET)
    public ModelAndView getListUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("hola", "Spring 5 MVC");
        return modelAndView;
    }
    
    @RequestMapping(value = "/inicio",  method = RequestMethod.GET)
    public ModelAndView Cargando(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("hola", "Spring 5 MVC");
        return modelAndView;
    }

}
