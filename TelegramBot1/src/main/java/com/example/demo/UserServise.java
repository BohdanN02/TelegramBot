package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServise {
    private  final UserRepository userRepository;

    public UserServise(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public  User findByChatId(long id){
        return  userRepository.findByChatId(id);
    }

    @Transactional
    public void addUser(User user) {
        user.setAdmin(userRepository.count()== 0);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
