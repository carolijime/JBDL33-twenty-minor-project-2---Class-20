package com.example.JBDL33twelveminorproject1.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig {

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Generalizing the cache functionality (check first if data is in cache, if it is, retrieve from cache, if not, save it in cache
     * T getData(Repository class ref, Cache class ref){
     *     //common logic
     * }
     */

}
