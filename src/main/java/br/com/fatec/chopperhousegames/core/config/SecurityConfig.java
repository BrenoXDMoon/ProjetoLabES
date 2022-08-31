package br.com.fatec.chopperhousegames.core.config;

import br.com.fatec.chopperhousegames.core.domain.service.UsuarioAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan(basePackageClasses = UsuarioAcessoService.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioAcessoService userDetailsService;


    @Autowired
    public SecurityConfig(UsuarioAcessoService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/**")
                .permitAll()//.hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**")
                .permitAll()//.hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/admin/**")
                .permitAll()//.hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/cliente/novo")
//                    .permitAll()
//
//                .antMatchers(HttpMethod.POST,"/cliente/novo")
//                    .permitAll()
                .antMatchers(HttpMethod.GET, "/cliente/**")
                .permitAll()//.hasAnyRole("ADMIN","CLIENTE")

                .antMatchers(HttpMethod.POST, "/cliente/**")
                .permitAll()//.hasAnyRole("ADMIN","CLIENTE")

                .antMatchers(HttpMethod.DELETE, "/cliente/**")
                .permitAll()//.hasAnyRole("ADMIN","CLIENTE")

                .antMatchers(HttpMethod.GET, "/")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/login")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
                .and().cors().and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
