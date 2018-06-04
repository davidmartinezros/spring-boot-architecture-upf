package edu.upf.model.model;
 
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.springframework.util.StringUtils;
 
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table(name="MODEL_ALUMNE")
@Getter
@Setter
@EqualsAndHashCode(of="idAlumne")
public class Alumne {
     
    @Id
    @Column(name = "idalumne")
    private Long idAlumne;
     
    @Column(name = "dni")
    private String dni;
     
    @Column(name = "nom")
    private String nom;
     
    @Column(name = "primercognom")
    private String primerCognom;
     
    @Column(name = "segoncognom")
    private String segonCognom;
     
    @Column(name = "datanaixement")
    private Date dataNaixement;
     
    public String getNomComplet() {
        if (StringUtils.isEmpty(segonCognom)) {
            return nom + " " + primerCognom;
        } else {
            return nom + " " + primerCognom + " " + segonCognom;
        }
    }
 
}