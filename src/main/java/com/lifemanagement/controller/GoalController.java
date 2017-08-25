package com.lifemanagement.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifemanagement.models.Goal;
import com.lifemanagement.repos.GoalRepo;

@CrossOrigin
@RestController
public class GoalController {
	
	private static final Logger logger = Logger.getLogger(GoalController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;
	
	
	@Autowired GoalRepo goalRepo;
	public GoalController() {
	}
	
	@RequestMapping(value = {"/goals/status=notreached"}, method = RequestMethod.GET, produces="application/json")
    public List<Goal> getAllNotFinishedGoals(HttpServletResponse response) {
		logger.debug("Get all Goal");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return goalRepo.findByStatusNot("reached");
    }


	@RequestMapping(value = {"/goals", "/"}, method = RequestMethod.GET, produces="application/json")
	public List<Goal> getAllGoals(HttpServletResponse response) {
		logger.debug("Get all Goal");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return goalRepo.findAll();
	}
	
	@RequestMapping(value = "/goals", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public Goal post( @RequestBody Goal newGoal, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newGoal));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newGoal));
				goalRepo.save(newGoal);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newGoal;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
		
	}
	
	@RequestMapping(value = "/goals/{goal_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public Goal putGoal( @RequestBody Goal updatedGoal, @PathVariable("goal_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		Goal oldGoal = goalRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedGoal));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldGoal != null ) {
			try {				
				updatedGoal.set_id(oldGoal.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedGoal));
				goalRepo.save(updatedGoal);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedGoal;
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}	
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;		
	}
	
	@RequestMapping(value = "/goals/{goal_id}", method = RequestMethod.DELETE)
    public void deleteGoal(@PathVariable("goal_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		Goal oldGoal = goalRepo.findOne(id);
		
		if ( oldGoal != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldGoal));
				goalRepo.delete(id);
				response.setStatus(HttpServletResponse.SC_OK); //HttpServletResponse.SC_ACCEPTED
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}					
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST );
		}	
    }        
	
	
}
