package edu.upf.model.security;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.upf.model.model.Rol;
import edu.upf.model.model.Usuari;
import edu.upf.model.security.pojo.AtributsCAS;
import edu.upf.model.service.RolService;
import edu.upf.model.service.UsuariService;
import lombok.extern.slf4j.Slf4j;
 
/**
 * Classe que fa l'autenticació CAS per l'aplicació Model.
 *
 */
 
@Service
@Slf4j
public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken>, UserDetailsService {
 
    @Autowired
    private UsuariService usuariService;
 
    @Autowired
    private RolService rolService;
 
    public CasUserDetailsService() {
        super();
    }
 
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
 
        log.debug("1. getAtributsCAS(token)");
        AtributsCAS atributsCAS = getAtributsCAS(token);
 
        log.debug("2. getUsuariAutenticat(atributsCAS)");
        UserDetailsImpl userDetailsImpl = getUsuariAutenticat(atributsCAS);
 
        log.debug("3. userDetailsImpl == null?");
        if (userDetailsImpl == null) {
            log.error("loadUserDetails - ERROR: L'usuari no te permís per accedir a l'aplicació");
            throw new UsernameNotFoundException("ERROR: L'usuari no te permís per accedir a l'aplicació");
        }
        log.info("3.1 userDetailsImpl: " + userDetailsImpl.toString());
 
        log.debug("4. return userDetailsImpl");
        return userDetailsImpl;
    }
 
 
    /**
     * Obtenim els atributsCAS a partir del que ens retorna el CAS dins del CasAssertionAuthenticationToken quan intentem accedir a l-aplicació
     * Els atributs que rebem del CAS, pactats amb l'equip que el (UGIP) son:
     * <ul>
     * <li>De moment, Fase 1, cap. Només rebem el NIS com principal.
     * </ul>
     *
     * @param token que retorna CAS
     * @return AtributsCAS
     * @see edu.upf.model.security.pojo.AtributsCAS
     */
    private AtributsCAS getAtributsCAS(CasAssertionAuthenticationToken token) {       
        AtributsCAS atributsCAS = new AtributsCAS();
 
        atributsCAS.setUserCAS(token.getPrincipal().toString());
 
        /* Aquí recuperariem els atributs si en tinguessim */
 
        log.info("1.1 Rebem del CAS: userCAS: " + atributsCAS.getUserCAS());
 
        return(atributsCAS);
 
    }
 
    /**
     * 1. Comprovem que el nis pertany a un usuari
     * 2. Si és que si, li assignem els rols i els permisos que tingui donats
     *    d'alta a la base de dades i retornem l'objecte UserDetailsImpl amb totes les dades
     *
     * @param atributsCAS Atributs que ens ha retornat el CAS a l'intentar accedir a l'aplicació
     * @return UserDetailsImpl
     */
    private UserDetailsImpl getUsuariAutenticat(AtributsCAS atributsCAS) {
 
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String nomComplet = "";
        Rol oRol = null;
 
        Usuari usuariAutenticat = usuariService.cercarUsuariPerNis(atributsCAS.getNis());
 
        if(usuariAutenticat == null){
            log.error("2.1 getUsuariAutenticat - ERROR: L'usuari no te permís per accedir a l'aplicació");
            return(null);
        }
 
        nomComplet = usuariAutenticat.getNomComplet();
        oRol = rolService.cercarRolPerId(usuariAutenticat.getDefaultRol());
 
        log.debug("2.2 Assignem rol per defecte " + usuariAutenticat.getDefaultRol());
        grantedAuthorities.add(new SimpleGrantedAuthority(usuariAutenticat.getDefaultRol()));
 
        log.debug("2.3 Creem new UserDetailsImpl");
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(usuariAutenticat.getNis().toString(),
                grantedAuthorities, nomComplet, oRol.getIdRol(),
                oRol.getMemo(), usuariAutenticat.getDefaultIdioma());
 
        return userDetailsImpl;
 
    }
    
    /**
    * Per testing
    * @param username
    * @return
    * @throws UsernameNotFoundException
    */
    @Profile(value = "TEST")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             
       return this.ompleUserDetailsImplProves(username,  "Marta Morales Vivó", "ROL_TEST", "ca");
     
    }
     
    /**
    * Omple un objecte UserDetailsImpl amb tot el necessari per poder fer tests
    *
    * @param idUsuari
    * @param nomComplet
    * @param idRolUsuari
    * @param idIdioma
    */
    @Profile(value = "TEST")
    public UserDetailsImpl ompleUserDetailsImplProves(String nisUsuari,
            String nomComplet, String idRolUsuari, String idIdioma) {
         
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(idRolUsuari));
         
        Usuari usuari = usuariService.cercarUsuariPerNis(new Integer(nisUsuari));
     
        Rol oRol = rolService.cercarRolPerId(idRolUsuari);
                 
        UserDetailsImpl oUserDetailsImpl = new UserDetailsImpl(nisUsuari,
                grantedAuthorities, usuari.getNomComplet(), idRolUsuari, oRol.getMemo(), idIdioma);
     
        return oUserDetailsImpl;
    }
 
}