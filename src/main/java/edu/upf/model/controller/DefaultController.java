package edu.upf.model.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping(value = {"/"})
public class DefaultController {
     
    @RequestMapping()
    public ModelAndView inici() {
         
        return new ModelAndView("inici");
 
    }
}