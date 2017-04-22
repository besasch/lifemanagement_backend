package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.CheckUp;

@RepositoryRestResource(exported = false)
public interface CheckUpRepo extends  MongoRepository<CheckUp, String> {
	
}
