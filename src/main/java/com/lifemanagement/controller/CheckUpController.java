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
import com.lifemanagement.models.CheckUp;
import com.lifemanagement.repos.CheckUpRepo;

@CrossOrigin
@RestController
public class CheckUpController {
	
	private static final Logger logger = Logger.getLogger(CheckUpController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;
	
	
	@Autowired CheckUpRepo checkUpRepo;
	public CheckUpController() {
	}
	
	@RequestMapping(value = {"/checkUps", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<CheckUp> getAllCheckUps(HttpServletResponse response) {
		logger.debug("Get all CheckUp");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return checkUpRepo.findAll();
    }
	
	@RequestMapping(value = "/checkUps", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public CheckUp post( @RequestBody CheckUp newCheckUp, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newCheckUp));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newCheckUp));
				checkUpRepo.save(newCheckUp);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newCheckUp;

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
	
	@RequestMapping(value = "/checkUps/{checkUp_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public CheckUp putCheckUp( @RequestBody CheckUp updatedCheckUp, @PathVariable("checkUp_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		CheckUp oldCheckUp = checkUpRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedCheckUp));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldCheckUp != null ) {
			try {				
				updatedCheckUp.set_id(oldCheckUp.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedCheckUp));
				checkUpRepo.save(updatedCheckUp);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedCheckUp;
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
	
	@RequestMapping(value = "/checkUps/{checkUp_id}", method = RequestMethod.DELETE)
    public void deleteCheckUp(@PathVariable("checkUp_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		CheckUp oldCheckUp = checkUpRepo.findOne(id);
		
		if ( oldCheckUp != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldCheckUp));
				checkUpRepo.delete(id);
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
