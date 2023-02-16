package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.User;
import com.byteridge.sahayak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
        userRepository.save(user);
        return user;

    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
