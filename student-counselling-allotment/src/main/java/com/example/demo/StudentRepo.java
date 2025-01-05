package com.example.demo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByPhone(String phone);
    Optional<StudentEntity> findByEmail(String email);
    @Query("SELECT s FROM StudentEntity s ORDER BY s.tenth DESC, s.inter DESC")
    List<StudentEntity> findTopStudents(Pageable pageable);
}
