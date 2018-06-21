package edu.upf.model.config;
 
import javax.sql.DataSource;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
 
import lombok.extern.slf4j.Slf4j;
 
@Configuration
@Profile({"TEST"})
@Slf4j
public class EmbeddedDatabaseDataConfig implements DataConfig {
     
    @Bean
    public DataSource dataSource() {
        log.info("Creant connexió a bdd en memòria");
         
        // No need to shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder
            .setType(EmbeddedDatabaseType.H2)
            //Creació de les taules
            .addScript("db/schema.sql")
            //Dades genèriques necessàries per tots els tests:
            .addScript("db/test-data.sql")
            .build();
 
        return dataSource;
    }
 
}