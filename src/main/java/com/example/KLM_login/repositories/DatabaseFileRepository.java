package com.example.KLM_login.repositories;

import com.example.KLM_login.models.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {
}
