package edu.upf.model.config;
 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
 
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
import edu.upf.model.properties.CasProperties;
import edu.upf.model.security.CasUserDetailsService;
 
/**
 * Classe de configuració de l'aplicació.
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private CasProperties casProperties;
 
    @Autowired
    private CasUserDetailsService casUserDetailsService;
 
    @Bean
    public ServiceProperties serviceProperties() throws UnknownHostException {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(getUrlWithHostName(casProperties.getValidateUrl()));
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }
 
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() throws UnknownHostException {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(casUserDetailsService);
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }
 
 
    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator()  {
        return new Cas20ServiceTicketValidator(casProperties.getReturnUrl());
    }
 
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return casAuthenticationFilter;
    }
 
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() throws UnknownHostException  {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casProperties.getLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }
 
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
 
        auth.authenticationProvider(casAuthenticationProvider());
    }
 
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(casAuthenticationFilter());
  
        http.authorizeRequests()
                .antMatchers("/img/**", "/css/**", "/font/**", "/js/**", "/error/**", "/logout")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/**")
                .authenticated();
 
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(getUrlWithHostName(casProperties.getLogoutUrl()));;
  
        http.exceptionHandling()
                .authenticationEntryPoint(casAuthenticationEntryPoint());
 
    }
 
 
    private String getUrlWithHostName(String urlToFormat) throws UnknownHostException {
        String url = MessageFormat.format(urlToFormat, InetAddress.getLocalHost().getCanonicalHostName());
        return url;
    }
 
}