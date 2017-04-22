package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.Mindset;

@RepositoryRestResource(exported = false)
public interface MindsetRepo extends  MongoRepository<Mindset, String> {
	
}
