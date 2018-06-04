package edu.upf.model.properties;
 
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
/**
 * Classe que s'utilitza per accedir a les properties d'Hibernate del fitxer application-?entorn?.yml que correspongui segons l'entorn
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "hibernate")
public class HibernateProperties {
     
    @NonNull
    private Boolean showSql;
     
    @NonNull
    private String dialect;
     
}