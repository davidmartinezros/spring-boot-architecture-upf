package edu.upf.model.properties;
 
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
/**
 * Classe que s'utilitza per accedir a les properties d'accès a Oracle del fitxer application-?entorn?.yml que correspongui segons l'entorn
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oracle")
public class OracleSQLProperties {
     
    @NonNull
    private String databaseUrl;
     
    @NonNull
    private String username;
     
    @NonNull
    private String password;
     
    @NonNull
    private String driverClassName;
     
    @NonNull
    private String dataSourceName;
        
}