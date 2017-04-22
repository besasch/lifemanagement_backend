package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.Goal;

@RepositoryRestResource(exported = false)
public interface GoalRepo extends  MongoRepository<Goal, String> {
	
}
