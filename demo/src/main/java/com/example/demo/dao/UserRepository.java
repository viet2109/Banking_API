package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email or u.phone = :phone")
    public Optional<User> findByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByPhone(String phone);
}
