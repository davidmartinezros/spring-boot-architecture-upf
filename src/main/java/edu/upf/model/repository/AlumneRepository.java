package edu.upf.model.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.upf.model.model.Alumne;
 
 
/**
 * Queries per l'entity edu.upf.model.model.Alumne
 * Mirar com es creen les queries a la documentació de Spring Data:
 *    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @see edu.upf.model.model.Alumne
 *
 */
@Repository
public interface AlumneRepository extends JpaRepository< Alumne, Long> {
     
    /**
     * Query generada per Spring Data
     * @param idAlumne
     * @return
     */
    @Transactional(readOnly = true)
    public Alumne findOneByIdAlumne(@Param("idAlumne") Long idAlumne);
    
    @Transactional(readOnly = true)
    List<Alumne> findByPrimerCognomAndSegonCognomAllIgnoreCaseOrderByPrimerCognom(String primerCognom, String segonCognom);
     
}