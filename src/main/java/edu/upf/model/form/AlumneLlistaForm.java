package edu.upf.model.form;
 
import java.util.List;
 
import edu.upf.model.model.Alumne;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class AlumneLlistaForm {
     
    private List<Alumne> alumnes;
     
    private Alumne alumne;
 
}