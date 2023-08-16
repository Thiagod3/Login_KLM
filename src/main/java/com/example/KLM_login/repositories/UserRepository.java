package com.example.KLM_login.repositories;

import com.example.KLM_login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsernameAndPassword(String username, String password);
    User findByPassword(String password);
}
