package com.lgdx.example.repository;

import com.lgdx.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from tb_user where user_id = :userId and password = :password", nativeQuery = true)
    User login(@Param("userId")String userId, @Param("password")String password);
}
