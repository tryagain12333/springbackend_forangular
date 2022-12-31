package com.example.springbackend.Repository;

import com.example.springbackend.Model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueRepository extends JpaRepository<Value, Long> {
    List<Value> findByValue1Containing(String value1);

}