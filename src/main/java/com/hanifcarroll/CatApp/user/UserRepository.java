package com.hanifcarroll.CatApp.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.email = :email, u.password = :password WHERE u.id = :id")
    void updateUser(
            @Param("id") long id,
            @Param("username") String username,
            @Param("email") String email,
            @Param("password") String password
    );

    User findFirstByUsername(String username);

    User findFirstByEmail(String email);
}
