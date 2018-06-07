package edu.upf.model.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upf.model.model.Usuari;
import edu.upf.model.repository.UsuariRepository;
 
/**
 * Implementació de la interfície AlumneService.
 * Conté la lògica de negoci relacionada amb l'objecte Alumne.
 *
 * @see upf.edu.model.model.Alumne
 * @see upf.edu.model.repository.AlumneRepository
 *
 */
@Service
public class UsuariServiceImpl implements UsuariService {
     
    @Autowired
    UsuariRepository usuariRepository; 
     
    public Usuari cercarUsuariPerNis(Integer nis) {   
        Usuari usuari = usuariRepository.findOneByNis(nis);
        return usuari;
    }
    
}