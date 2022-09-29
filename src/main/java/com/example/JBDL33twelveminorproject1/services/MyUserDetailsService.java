package com.example.JBDL33twelveminorproject1.services;

import com.example.JBDL33twelveminorproject1.models.MyUser;
import com.example.JBDL33twelveminorproject1.repositories.MyUserCacheRepository;
import com.example.JBDL33twelveminorproject1.repositories.MyUserRepository;
import com.example.JBDL33twelveminorproject1.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    //"general" rule for service using cache
    // 1. GET data from cache
    // 2. If not found, fetch from db
    // 3. If found from db, insert in cache again
    // 4. Return the data to the client

    //checks first in cache
    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    //if not in cache, checks DB
    @Autowired
    MyUserRepository myUserRepository;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //get user from cache
        MyUser myUser = myUserCacheRepository.get(username);

        // check first if value is in cache, if not, find in the DB
        //below code could be made "generic"
        if(myUser == null){
            myUser = myUserRepository.findByUsername(username);
            //confirm that user is in DB (if not, error will be thrown
            if(myUser != null){
                myUserCacheRepository.set(myUser); //this call can be made parallel using threads
            }
        }

        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){

        MyUser .MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(adminAuthority);
        }

        return myUserRepository.save(myUserBuilder.build());
    }
}
