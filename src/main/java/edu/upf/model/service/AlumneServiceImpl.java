package edu.upf.model.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import edu.upf.model.model.Alumne;
import edu.upf.model.repository.AlumneRepository;
 
/**
 * Implementació de la interfície AlumneService.
 * Conté la lògica de negoci relacionada amb l'objecte Alumne.
 *
 * @see upf.edu.model.model.Alumne
 * @see upf.edu.model.repository.AlumneRepository
 *
 */
@Service
public class AlumneServiceImpl implements AlumneService {
     
    @Autowired
    AlumneRepository alumneRepository; 
     
    public Alumne cercarAlumnePerId(Long idAlumne) {   
        Alumne alumne = alumneRepository.findOneByIdAlumne(idAlumne);
        return alumne;
    }
    
    public List<Alumne> cercarAlumnesPerNomiCognom(String primerCognom, String segonCognom) {
    	List<Alumne> list = alumneRepository.findByPrimerCognomAndSegonCognomAllIgnoreCaseOrderByPrimerCognom(primerCognom, segonCognom);
    	return list;
    }
 
}