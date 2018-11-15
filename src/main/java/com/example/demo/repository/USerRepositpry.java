package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface USerRepositpry extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
	/*  @Query("update User u set u.password = :password where u.userId = :id")
	    void updatePassword( @Param("password")String password, @Param("id")Long id);
*/
}
