package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.Vision;

@RepositoryRestResource(exported = false)
public interface VisionRepo extends  MongoRepository<Vision, String> {
	
}
