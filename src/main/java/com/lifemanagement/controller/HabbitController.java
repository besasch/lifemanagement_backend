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
import com.lifemanagement.models.Habbit;
import com.lifemanagement.repos.HabbitRepo;

@CrossOrigin
@RestController
public class HabbitController {
	
	private static final Logger logger = Logger.getLogger(HabbitController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;
	
	
	@Autowired HabbitRepo habbitRepo;
	public HabbitController() {
	}
	
	@RequestMapping(value = {"/habbits", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<Habbit> getAllHabbits(HttpServletResponse response) {
		logger.debug("Get all Habbit");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return habbitRepo.findAll();
    }
	
	@RequestMapping(value = "/habbits", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public Habbit post( @RequestBody Habbit newHabbit, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newHabbit));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newHabbit));
				habbitRepo.save(newHabbit);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newHabbit;

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
	
	@RequestMapping(value = "/habbits/{habbit_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public Habbit putHabbit( @RequestBody Habbit updatedHabbit, @PathVariable("habbit_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		Habbit oldHabbit = habbitRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedHabbit));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldHabbit != null ) {
			try {				
				updatedHabbit.set_id(oldHabbit.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedHabbit));
				habbitRepo.save(updatedHabbit);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedHabbit;
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
	
	@RequestMapping(value = "/habbits/{habbit_id}", method = RequestMethod.DELETE)
    public void deleteHabbit(@PathVariable("habbit_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		Habbit oldHabbit = habbitRepo.findOne(id);
		
		if ( oldHabbit != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldHabbit));
				habbitRepo.delete(id);
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
