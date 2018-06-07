package edu.upf.model.properties;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
 
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cas")
public class CasProperties {
 
    @NonNull
    private String loginUrl;
 
    @NonNull
    private String validateUrl;
 
    @NonNull
    private String returnUrl;
 
    @NonNull
    private String logoutUrl;
 
}