package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.CheckUpTemplate;

@RepositoryRestResource(exported = false)
public interface CheckUpTemplateRepo extends  MongoRepository<CheckUpTemplate, String> {
	
}
