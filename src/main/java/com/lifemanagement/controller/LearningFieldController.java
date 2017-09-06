package com.lifemanagement.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifemanagement.models.LearningField;
import com.lifemanagement.repos.LearningFieldRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@CrossOrigin
@RestController
public class LearningFieldController {

	private static final Logger logger = Logger.getLogger(LearningFieldController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;


	@Autowired LearningFieldRepo learningFieldRepo;
	public LearningFieldController() {
	}
	
	@RequestMapping(value = {"/learningFields", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<LearningField> getAllLearningFields(HttpServletResponse response) {
		logger.debug("Get all LearningField");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return learningFieldRepo.findAll();
    }
	
	@RequestMapping(value = "/learningFields", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public LearningField post( @RequestBody LearningField newLearningField, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newLearningField));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newLearningField));
				learningFieldRepo.save(newLearningField);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newLearningField;

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
	
	@RequestMapping(value = "/learningFields/{learningField_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public LearningField putLearningField( @RequestBody LearningField updatedLearningField, @PathVariable("learningField_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		LearningField oldLearningField = learningFieldRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedLearningField));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldLearningField != null ) {
			try {				
				updatedLearningField.set_id(oldLearningField.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedLearningField));
				learningFieldRepo.save(updatedLearningField);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedLearningField;
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
	
	@RequestMapping(value = "/learningFields/{learningField_id}", method = RequestMethod.DELETE)
    public void deleteLearningField(@PathVariable("learningField_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		LearningField oldLearningField = learningFieldRepo.findOne(id);
		
		if ( oldLearningField != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldLearningField));
				learningFieldRepo.delete(id);
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
