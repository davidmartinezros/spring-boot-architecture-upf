package edu.upf.model.service;
 
import java.util.List;
 
import edu.upf.model.model.Alumne;
 
public interface AlumneService {
     
    public Alumne cercarAlumnePerId(Long idAlumne);
    
    public List<Alumne> cercarAlumnesPerNomiCognom(String primerCognom, String segonCognom);
    
    public Alumne cercarAlumnePerDni(String dni);
 
}