package com.lifemanagement.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lifemanagement.models.Goal;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface GoalRepo extends  MongoRepository<Goal, String> {


    List<Goal> findByStatusNot(String status);
}
