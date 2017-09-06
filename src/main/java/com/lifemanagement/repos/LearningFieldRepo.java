package com.lifemanagement.repos;

import com.lifemanagement.models.LearningField;
import com.lifemanagement.models.Wish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface LearningFieldRepo extends  MongoRepository<LearningField, String> {
	
}
