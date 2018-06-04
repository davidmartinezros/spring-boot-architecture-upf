package edu.upf.model.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class DefaultController {
     
    @RequestMapping()
    public ModelAndView inici() {
    	
    	log.debug("Provant els logs");
         
        return new ModelAndView("inici");
 
    }
}