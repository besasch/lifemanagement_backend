package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.Habbit;

@RepositoryRestResource(exported = false)
public interface HabbitRepo extends  MongoRepository<Habbit, String> {
	
}
