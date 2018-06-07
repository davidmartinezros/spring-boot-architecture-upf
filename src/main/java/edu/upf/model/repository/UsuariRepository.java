package edu.upf.model.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.upf.model.model.Usuari;
 
 
/**
 * Queries per l'entity edu.upf.model.model.Usuari
 * Mirar com es creen les queries a la documentació de Spring Data:
 *    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @see edu.upf.model.model.Usuari
 *
 */
@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Integer> {
     
    /**
     * Query generada per Spring Data
     * @param nis
     * @return
     */
    @Transactional(readOnly = true)
    public Usuari findOneByNis(@Param("nis") Integer nis);
    
}