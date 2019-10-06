package com.oc.greenbean.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String QUERY_USER_SQL = "select username, password, enabled from t_user where username = ?";
    public static final String QUERY_AUTHORITY_SQL = "select username, authority from t_authority inner join t_user on t_authority.user_id = t_user.id where username = ?";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/static/common/**", "/static/template/**", "/signUp").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/signIn").permitAll()
            .successForwardUrl("/home")
            .failureUrl("/signInError")
            .and().logout().logoutSuccessUrl("/signIn")
            .and().rememberMe().key("greenbean").tokenValiditySeconds(60);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(QUERY_USER_SQL)
            .authoritiesByUsernameQuery(QUERY_AUTHORITY_SQL);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
