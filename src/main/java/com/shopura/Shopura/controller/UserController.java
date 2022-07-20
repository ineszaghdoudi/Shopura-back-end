package com.shopura.Shopura.controller;



import com.shopura.Shopura.entity.AuthRequest;
import com.shopura.Shopura.entity.User;
import com.shopura.Shopura.repository.UserRepository;
import com.shopura.Shopura.service.DbSequenceService;
import com.shopura.Shopura.service.UserService;
import com.shopura.Shopura.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@RestController
@ComponentScan("com.shopura.Shopura")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
            }
        };
    }


    @Autowired
    private UserService userService;
    @Autowired
    private DbSequenceService dbSequenceService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome here!";
    }

    @RequestMapping( "/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){

        user.setId(dbSequenceService.generateSequence(User.SEQUENCE_NAME));
        return userRepository.save(user);

    }


    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {

        String tempEmail= user.getEmail();
        String tempPass= user.getPassword();
        User userObj = null;
        if(tempEmail !=null && tempPass !=null){
            userObj = userService.getByEmailandPassword(tempEmail, tempPass);
        }
        return userObj;
    }

    @RequestMapping("/getuser/{email}")
    public User getUser(@PathVariable String email){
        return userService.getByEmail(email);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {return userService.getAll(); }

    @PutMapping("/updateuserpass")
    public User updateUserPass(@RequestParam String email, @RequestParam String password, @RequestParam String cpassword) {
        return userService.updateUserPass(email, password, cpassword);
    }

    @PutMapping("/updateuser")
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/deleteuser/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.delete(id);

    }




}

