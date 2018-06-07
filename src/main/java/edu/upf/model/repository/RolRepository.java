package edu.upf.model.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.upf.model.model.Rol;
 
 
/**
 * Queries per l'entity edu.upf.model.model.Rol
 * Mirar com es creen les queries a la documentació de Spring Data:
 *    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @see edu.upf.model.model.Rol
 *
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
     
    /**
     * Query generada per Spring Data
     * @param idString
     * @return
     */
    @Transactional(readOnly = true)
    public Rol findOneByIdRol(@Param("idRol") String idRol);
    
}