package edu.upf.model.form;
 
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class AlumneCercaForm {
     
    private String cognom1;
    @Size(max=5, message="{validation.alumne.cognom2}")
    private String cognom2;
    private String dni;
 
}