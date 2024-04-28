package com.bits.language.data.repo;

import com.bits.language.data.entity.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<AuthUser, Long> {

	AuthUser findByUsername(String username);
	
	AuthUser findByEmail(String email);

	@Query("{ '$or' : [ { 'username': ?0 }, { 'email': ?1 } ] }")
	AuthUser findByUsernameOrEmail(String username, String email);

}
