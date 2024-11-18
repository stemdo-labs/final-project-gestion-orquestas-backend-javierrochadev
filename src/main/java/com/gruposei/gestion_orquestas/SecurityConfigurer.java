package com.gruposei.gestion_orquestas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.gruposei.gestion_orquestas.filters.JwtRequestFilters;
import com.gruposei.gestion_orquestas.service.MyUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilters jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/**")
//                .authenticated().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // http.csrf().disable().cors().add().authorizeRequests().antMatchers("/api/login").permitAll()
        //         .antMatchers("/api/users/register").permitAll()
        //         .antMatchers("/api/mercadopago/notifications").permitAll()
        //         .antMatchers("/**").permitAll()
        //         .antMatchers(HttpMethod.GET,"/api/news").permitAll()
        //         .antMatchers(HttpMethod.GET,"/api/shows").permitAll();

        http.csrf().disable()
                .cors() // Activa la configuración CORS
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/mercadopago/notifications").permitAll()
                .antMatchers(HttpMethod.GET,"/api/news").permitAll()
                .antMatchers(HttpMethod.GET,"/api/shows").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{

        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Ajusta los orígenes permitidos según tu entorno
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
