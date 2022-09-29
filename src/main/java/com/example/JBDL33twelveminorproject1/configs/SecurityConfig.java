package com.example.JBDL33twelveminorproject1.configs;

import com.example.JBDL33twelveminorproject1.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // authentication + authorization + PE

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Value("${users.student.authority}")
    String studentAuthority;

    // Spring security configs
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //instructions (antMatchers) are sequential
        http
                .csrf().disable() //for insecure POST requests to work
                .authorizeHttpRequests()
                .antMatchers("/student_for_admin/**").hasAnyAuthority(adminAuthority)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll() //needs csrd disabled to work
                .antMatchers("/student/**", "/transaction/**").hasAnyAuthority(studentAuthority)
                .antMatchers("/book/search/**").hasAnyAuthority(studentAuthority, adminAuthority)
                .antMatchers("/book/**").hasAnyAuthority(adminAuthority)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
//                .antMatchers("/transaction/**").hasAnyAuthority(studentAuthority)

    }

}
