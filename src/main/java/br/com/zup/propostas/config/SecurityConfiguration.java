package br.com.zup.propostas.config;



import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();
        http.authorizeRequests(request->
                request.antMatchers("/**")
                        .hasAuthority("SCOPE_proposta")
        ).
        oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }



}
