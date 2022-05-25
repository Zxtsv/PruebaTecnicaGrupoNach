package com.gruponach.pruebatecnica.repository;

import com.gruponach.pruebatecnica.models.Jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
    @Modifying
    @Query(value= "SELECT id FROM Jobs WHERE id = :id", nativeQuery = true)
    Integer findJobById(@Param("id") Long id);
}
