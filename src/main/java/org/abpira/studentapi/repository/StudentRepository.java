package org.abpira.studentapi.repository;

import jakarta.transaction.Transactional;
import org.abpira.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    @Modifying
    @Transactional
    void deleteByEmail(String email);
}
