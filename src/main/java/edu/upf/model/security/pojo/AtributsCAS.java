package edu.upf.model.security.pojo;
 
import org.springframework.util.StringUtils;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
/**
 * Objecte POJO que encapsula els atributs CAS que necessitem a Model
 *
 */
@Getter
@Setter
@ToString
public class AtributsCAS {
     
    private String userCAS;
     
    public Integer getNis() {
        if(StringUtils.isEmpty(this.userCAS)) {
            return null;
        }
        try {
            return Integer.valueOf(this.userCAS.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }
 
}