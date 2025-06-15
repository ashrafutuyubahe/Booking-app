package com.hotel_mngs.TUYUBAHE_Ashrafu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.User;






@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User>  findByResetToken(String token);

}

