package com.harsha.newspaper;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<UserEntity, Serializable> {

	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByPhone(String phone);
	@Transactional
	@Modifying
	@Query("Delete From UserEntity u where u.email=:email")
	void deleteByEmail(@Param("email") String email);

}
