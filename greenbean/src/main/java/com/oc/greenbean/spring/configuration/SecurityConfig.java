package com.oc.greenbean.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String QUERY_USER_SQL = "select username, password, enabled from t_user where username = ?";
    private static final String QUERY_AUTHORITY_SQL = "select username, authority from t_authority inner join t_user on t_authority.user_id = t_user.id where username = ?";

    private DataSource dataSource;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfig(DataSource dataSource, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.dataSource = dataSource;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //FIXME 刷新signIn页面，登录post请求返回403
        //XXX 重构signIn这个URL，改为sign
        http.authorizeRequests()
            .antMatchers("/static/common/**", "/static/template/**", "/signUp", "/signUp/*").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/signIn").permitAll()
            .successHandler(authenticationSuccessHandler)
            .failureUrl("/signInError")
            .and().logout().logoutUrl("/signOut").logoutSuccessUrl("/signIn")
            .and().rememberMe().key("greenbean").tokenValiditySeconds(259200).authenticationSuccessHandler(authenticationSuccessHandler);
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
