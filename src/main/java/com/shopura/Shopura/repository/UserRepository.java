package com.shopura.Shopura.repository;

import com.shopura.Shopura.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
    public User findById(int id);

}
