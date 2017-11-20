package com.nagarro.amcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nagarro.amcart.models.enums.RoleType;
import com.nagarro.amcart.services.UserService;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "amcart.security")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    private String[] permittedUrls;
    private String[] adminPermittedUrls;
    private String loginUrl;
    private String logoutUrl;
    private String logoutSuccessUrl;

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(permittedUrls).permitAll()
                .antMatchers(adminPermittedUrls).hasAnyAuthority(RoleType.ROLE_ADMIN.getName(), RoleType.ROLE_CUSTOMER.getName())
                .anyRequest().authenticated().and().formLogin().loginPage(loginUrl).permitAll().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutUrl)).logoutSuccessUrl(logoutSuccessUrl);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    public String[] getPermittedUrls() {
        return permittedUrls;
    }

    public void setPermittedUrls(String[] permittedUrls) {
        this.permittedUrls = permittedUrls;
    }

    public String[] getAdminPermittedUrls() {
        return adminPermittedUrls;
    }

    public void setAdminPermittedUrls(String[] adminPermittedUrls) {
        this.adminPermittedUrls = adminPermittedUrls;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getLogoutSuccessUrl() {
        return logoutSuccessUrl;
    }

    public void setLogoutSuccessUrl(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }
    
}
