package br.com.fatec.ChopperHouseGames.config;

import br.com.fatec.ChopperHouseGames.service.impl.UsuarioAcessoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioAcessoImpl userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/admin/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/admin/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/admin/**")
                        .hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET,"/cliente/novo")
                        .permitAll()

                    .antMatchers(HttpMethod.POST,"/cliente/novo")
                        .permitAll()
                    .antMatchers(HttpMethod.GET,"/cliente/{\\d+}/**")
                        .hasAnyRole("ADMIN","CLIENTE")

                    .antMatchers(HttpMethod.POST,"/cliente/{\\d+}/**")
                        .hasAnyRole("ADMIN","CLIENTE")

                    .antMatchers(HttpMethod.DELETE,"/cliente/{\\d+}/**")
                        .hasAnyRole("ADMIN","CLIENTE")

                    .antMatchers(HttpMethod.GET,"/")
                        .permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and().logout().permitAll()
                    .and().cors().and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
