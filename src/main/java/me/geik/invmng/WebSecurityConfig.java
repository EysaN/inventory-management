package me.geik.invmng;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Classification of admins APIs
                .antMatchers("/level1", "/item/delete*", "/items/delete*").hasRole("ADMINS")
                // Classification of admins and subamdins APIs
                .antMatchers("/level2", "/item/add*", "/item/update*").hasAnyRole("SUBADMINS", "ADMINS")
                // Classification of admins, subamdins and customers APIs
                .antMatchers("/level3", "/carts", "/cart/{id}").hasAnyRole("CUSTOMERS" ,"SUBADMINS" ,"ADMINS")
                // we only use the following command in case we need to authenticate all requests
                //.anyRequest().fullyAuthenticated()
                // other APIs are allowed for all users
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups") // set the search base to look for users
                .groupSearchSubtree(true)
                .contextSource()
                .url("ldap://localhost:8399/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }

}
