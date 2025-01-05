package com.example.demo;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends CrudRepository<AdminEntity, Serializable> {

	Optional<AdminEntity> findByUsername(String username);

}
