package edu.upf.model.config;
 
import javax.sql.DataSource;
 
import lombok.extern.slf4j.Slf4j;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
 
import edu.upf.model.properties.OracleSQLProperties;
 
/**
 * Classe de configuració de l'aplicació.
 * <p>
 * Una de les tres implementacions de la interfície DataConfig.
 * <p>
 * Aquesta classe configura una connexió directa a la base de dades que fariem servir en Tomcat.
 * <p>
 * Abans això ho configuravem a l'applicationContext-dataSource.xml, veure exemple a GAP
 *
 */
@Configuration
@Profile({"DES"})
@Slf4j
public class StandaloneDataConfig implements DataConfig {
     
    @Autowired
    private OracleSQLProperties oracleSQLProperties;
 
    @Bean
    public DataSource dataSource() {
        log.info("Creant connexió directa a bdd");
         
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(oracleSQLProperties.getDatabaseUrl());
        dataSource.setUsername(oracleSQLProperties.getUsername());
        dataSource.setPassword(oracleSQLProperties.getPassword());
        dataSource.setDriverClassName(oracleSQLProperties.getDriverClassName());
 
        return dataSource;
    }
    
}
 