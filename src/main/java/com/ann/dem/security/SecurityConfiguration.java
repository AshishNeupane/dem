package com.ann.dem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ann.dem.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;

    private UserService userService;
    
    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;
     
    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserService userService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),this.userService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userService))
                .authorizeRequests()
                // configure access rules
                //.antMatchers(HttpMethod.POST, "/*").permitAll();
                //for getting general setting if security treat appear remove below 2 line code
                .antMatchers("/admin/generalSetting/getInformation").permitAll()
                .antMatchers("/admin/{filename:.+}").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/master/*").hasAnyRole("ADMIN","MANAGER","USER")
                .antMatchers("/tourPrograms").hasAnyRole("USER","MANAGER")
                .antMatchers("/supervisor/tourprogram/*").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/geo/*").hasAnyRole("ADMIN")
                .antMatchers("/master/qualifications/*").hasAnyRole("ADMIN")
                .antMatchers("/master/designations/*").hasAnyRole("ADMIN")
                .antMatchers("/master/categories/*").hasAnyRole("ADMIN")
                .antMatchers("/master/divisions/*").hasAnyRole("ADMIN")
                .antMatchers("/master/categoryDivisions/*").hasAnyRole("ADMIN")
                .antMatchers("/master/products/*").hasAnyRole("ADMIN")
                .antMatchers("/master/productSegments/*").hasAnyRole("ADMIN")
                .antMatchers("/master/ProductGroups/*").hasAnyRole("ADMIN")
                .antMatchers("/dcr/*").hasAnyRole("USER","MANAGER")
                .antMatchers("/supervisor/sale/*").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/admin/*").hasAnyRole("ADMIN","MANAGER","USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .failureHandler(loginFailureHandler)
                .successHandler(loginSuccessHandler)               
                .permitAll();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
