package com.shopura.Shopura.service;

import com.shopura.Shopura.entity.User;
import com.shopura.Shopura.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public User getByEmailandPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> getAll() { return userRepository.findAll();}

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUserPass(String email, String password, String cpassword) {
        User u=userRepository.findByEmail(email);
        u.setPassword(password);
        u.setCpassword(cpassword);

        return userRepository.save(u);
    }

    public User updateUser( User user) {
        User existUSR=userRepository.findById(user.getId());

        if(existUSR == null) {
            return  userRepository.save(user);
        } else {
            userRepository.deleteById(existUSR.getId());
            userRepository.save(user);
        }
        return user;
    }

    public boolean delete(int id) {
        User existUSR= userRepository.findById(id);
        if(existUSR != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
