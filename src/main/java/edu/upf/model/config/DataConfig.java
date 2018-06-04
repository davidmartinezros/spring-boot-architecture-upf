package edu.upf.model.config;
 
import javax.sql.DataSource;
 
import org.springframework.context.annotation.Bean;
 
/**
 * Interfície de configuració de l'aplicació.
 * Tindrem tres implementacions del DataConfig:
 * <ul>
 * <li>JndiDataConfig: pels entorns PRE i PRO en WebLogic
 * <li>StandaloneDataConfig: per l'entorn DES en Tomcat
 * <li>EmbeddedDatabaseDataConfig.java: per TEST
 * </ul>
 * Abans això ho configuravem a l'applicationContext-dataSource.xml, veure exemple a GAP
 *
 */
 
public interface DataConfig {
 
    @Bean
    public DataSource dataSource();
}