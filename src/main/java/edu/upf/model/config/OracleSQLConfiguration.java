package edu.upf.model.config;
 
import java.util.Properties;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
import edu.upf.model.properties.HibernateProperties;
import lombok.extern.slf4j.Slf4j;
 
/**
 * Classe de configuració de l'aplicació.
 * <p>
 * Aquesta classe configura el TransactionManager, l'Hibernate, a on es troben les classes Repository...
 * <p>
 * Abans això ho configuravem a l'applicationContext-dataSource.xml, veure exemple a GAP
 */
@Configuration
@EnableJpaRepositories(basePackages = "edu.upf.model.repository")
@EnableTransactionManagement
@Slf4j
public class OracleSQLConfiguration {
 
    @Autowired
    private HibernateProperties hibernateProperties;
     
    @Autowired
    private DataConfig dataConfig;
 
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
     
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         
        vendorAdapter.setGenerateDdl(false);
     
        entityManagerFactoryBean.setPackagesToScan("edu.upf.model.model");
        log.debug("entityManagerFactory() dataConfig.dataSource()? = " + dataConfig.dataSource());
        entityManagerFactoryBean.setDataSource(dataConfig.dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
 
        return entityManagerFactoryBean;
    }
 
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
     
    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();
 
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        jpaProperties.setProperty("hibernate.dialect", hibernateProperties.getDialect());
        jpaProperties.setProperty("hibernate.connection.charSet", "UTF-8");
        jpaProperties.put("hibernate.show_sql", hibernateProperties.getShowSql());
         
        return jpaProperties;
    }
     
}