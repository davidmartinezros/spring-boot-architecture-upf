package edu.upf.model.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import edu.upf.model.enums.AlumneEnum;
import edu.upf.model.form.AlumneCercaForm;
import edu.upf.model.form.AlumneLlistaForm;
import edu.upf.model.model.Alumne;
import edu.upf.model.service.AlumneService;
 
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Controller
@RequestMapping(value = "/alumne")
public class AlumneController {
 
    private static final String ALUMNE_CERCA_FORM = "alumneCercaForm";
    private static final String ALUMNE_LLISTA_FORM = "alumneLlistaForm";
     
    @Autowired
    private AlumneService alumneService;
 
    @RequestMapping("/iniciCerca")
    public final ModelAndView cerca() throws Exception {
 
        log.debug("Inici cerca");
 
        AlumneCercaForm  alumneCercaForm = new AlumneCercaForm();
 
        return new ModelAndView(AlumneEnum.CERCA.getView(), ALUMNE_CERCA_FORM, alumneCercaForm);
    }
 
 
    @RequestMapping("/cerca")
    public final ModelAndView resultat(AlumneCercaForm  alumneCercaForm) throws Exception {
 
        log.debug("VALORS DE LA CERCA");
        log.debug("Cognoms: " + alumneCercaForm.getCognom1() + " " + alumneCercaForm.getCognom2());
        log.debug("DNI: " + alumneCercaForm.getDni());
 
        List<Alumne> alumnes = alumneService.cercarAlumnesPerCognoms(alumneCercaForm.getCognom1(), alumneCercaForm.getCognom2());
        Alumne alumne = alumneService.cercarAlumnePerDni(alumneCercaForm.getDni());
        if (!(alumne==null) && !(alumnes.contains(alumne))) {
            alumnes.add(alumne);
        }
         
        AlumneLlistaForm alumneLlistaForm = new AlumneLlistaForm();
        if (!alumnes.isEmpty()) {
            alumneLlistaForm.setAlumnes(alumnes);
        }
             
        return new ModelAndView(AlumneEnum.LLISTA.getView(), ALUMNE_LLISTA_FORM, alumneLlistaForm);
    }
 
}