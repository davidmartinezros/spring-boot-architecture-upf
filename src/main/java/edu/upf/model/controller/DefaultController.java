package edu.upf.model.controller;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.upf.model.model.Alumne;
import edu.upf.model.model.Rol;
import edu.upf.model.model.Usuari;
import edu.upf.model.service.AlumneService;
import edu.upf.model.service.RolService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class DefaultController {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private AlumneService alumneService;
    
	@Autowired
    private RolService rolService;
	
    @RequestMapping()
    public ModelAndView inici() {
    	
    	/*
         * Per provar que funciona la connexió a la base de dades, fem unes queries i imprimim resultat per consola
         *
         */
    	Alumne oAlumne = alumneService.cercarAlumnePerId(new Long("61"));
        log.info("*********************** PROVA *************************");
        if(oAlumne != null) {
            log.info("Prova connexió a base de dades i query: alumne = " + oAlumne.getNomComplet());
        };
        log.info("*******************************************************");
        
        List<Alumne> listAlumne = alumneService.cercarAlumnesPerNomiCognom("Çelik", "Ahlström");
        log.info("*********************** PROVA 2 *************************");
        if(listAlumne != null) {
        	for(Alumne alumne: listAlumne) {
        		log.info("Prova connexió a base de dades i query: alumne = " + alumne.getNomComplet());        		
        	}
        };
        log.info("*******************************************************");
        
        Alumne oAlumneDni = alumneService.cercarAlumnePerDni("87654321");
        log.info("*********************** PROVA 3 *************************");
        if(oAlumneDni != null) {
            log.info("Prova connexió a base de dades i query: alumne = " + oAlumneDni.getNomComplet());
        };
        log.info("*******************************************************");
        
        Rol rol = rolService.cercarRolPerId("ROL_TEST");
        log.info("*********************** PROVA 4 *************************");
        if(rol != null) {
        	if(rol.getUsuaris() != null && rol.getUsuaris().size() > 0) {
        		for(Usuari usuari: rol.getUsuaris()) {
        			log.info("Prova connexió a base de dades i query: usuari = " + usuari.getNomComplet());
        		}
        	}
        }
        log.info("*******************************************************");	
        // error provocat
        //int a = 5/0;
        // -->
        
        return new ModelAndView("inici");
 
    }
}