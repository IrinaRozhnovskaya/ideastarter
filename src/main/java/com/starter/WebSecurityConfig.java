package com.starter;


import com.starter.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/registration", "/", "/publishedIdeas").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }


   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     auth
               .userDetailsService(userService)
               .passwordEncoder(bCryptPasswordEncoder());
    }
    //@Bean
   // public DaoAuthenticationProvider authenticationProvider() {
   //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //    authenticationProvider.setUserDetailsService(userService);
     //   authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
     //   return authenticationProvider;
    //}
   // @Override
   // @Bean
   // public AuthenticationManager authenticationManager() throws Exception {
   //     return super.authenticationManagerBean();
   // }
}