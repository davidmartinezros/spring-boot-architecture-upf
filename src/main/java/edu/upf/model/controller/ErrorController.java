package edu.upf.model.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
 
@ControllerAdvice
@Controller
public class ErrorController {
         
    @ExceptionHandler(Exception.class)
    public ModelAndView  mostrarExcepcio(Exception exception) {
         
        return new ModelAndView("error/errorGeneral");
    }
 
}