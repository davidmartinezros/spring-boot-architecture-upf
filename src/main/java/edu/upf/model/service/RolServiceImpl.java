package edu.upf.model.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upf.model.model.Rol;
import edu.upf.model.repository.RolRepository;
 
/**
 * Implementació de la interfície RolService.
 * Conté la lògica de negoci relacionada amb l'objecte Rol.
 *
 * @see upf.edu.model.model.Rol
 * @see upf.edu.model.repository.RolRepository
 *
 */
@Service
public class RolServiceImpl implements RolService {
     
    @Autowired
    RolRepository rolRepository; 
     
    public Rol cercarRolPerId(String idRol) {   
        Rol rol = rolRepository.findOneByIdRol(idRol);
        return rol;
    }
    
}