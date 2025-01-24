package org.example.repository;

import org.example.model.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}