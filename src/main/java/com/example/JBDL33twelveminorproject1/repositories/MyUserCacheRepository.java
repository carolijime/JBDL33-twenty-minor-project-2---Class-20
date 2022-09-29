package com.example.JBDL33twelveminorproject1.repositories;

import com.example.JBDL33twelveminorproject1.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class MyUserCacheRepository {

    private final String USER_KEY_PREFIX = "usr::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());

        //below is saving in cache
        //automatic logout happens after 60 minutes, so cache timeout should be greater than automatic logout timing
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    public MyUser get(String username){
        String key =  getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }

    private String getKey(String username){
        return USER_KEY_PREFIX + username;
    }
}
