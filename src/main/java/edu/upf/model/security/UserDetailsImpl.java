package edu.upf.model.security;
 
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
/**
 * Implementació de la interfície User amb els camps que necessitem per l'aplicació Model
 *
 */
@Getter
@Setter
@ToString
public class UserDetailsImpl extends User {
     
    private static final long serialVersionUID = -3139301885145237461L;
    private String idUsuari;
    private String nomComplet;
    private String idRolActual;
    private String descRolActual;
    private String idIdiomaActual;
 
    public UserDetailsImpl(String username,
            Collection<? extends GrantedAuthority> authorities, 
            String nomComplet, String idRolActual, String descRolActual,
            String idIdioma) {
 
        super(username, "", authorities);
         
        this.idUsuari = username;
        this.nomComplet = nomComplet;
        this.idRolActual = idRolActual;
        this.descRolActual = descRolActual;
        this.idIdiomaActual = idIdioma;
    }
 
    public Integer getNis() {
        try {
            String sNis = this.getIdUsuari();
            if (sNis == null) return null;
            else return new Integer(sNis);
        } catch (NumberFormatException e) {
            return null;
        }
    }
 
}