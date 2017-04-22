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
import com.lifemanagement.models.Mindset;
import com.lifemanagement.repos.MindsetRepo;

@CrossOrigin
@RestController
public class MindsetController {
	
	private static final Logger logger = Logger.getLogger(MindsetController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;
	
	
	@Autowired MindsetRepo mindsetRepo;
	public MindsetController() {
	}
	
	@RequestMapping(value = {"/mindsets", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<Mindset> getAllMindsets(HttpServletResponse response) {
		logger.debug("Get all Mindset");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return mindsetRepo.findAll();
    }
	
	@RequestMapping(value = "/mindsets", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public Mindset post( @RequestBody Mindset newMindset, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newMindset));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newMindset));
				mindsetRepo.save(newMindset);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newMindset;

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
	
	@RequestMapping(value = "/mindsets/{mindset_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public Mindset putMindset( @RequestBody Mindset updatedMindset, @PathVariable("mindset_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		Mindset oldMindset = mindsetRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedMindset));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldMindset != null ) {
			try {				
				updatedMindset.set_id(oldMindset.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedMindset));
				mindsetRepo.save(updatedMindset);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedMindset;
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
	
	@RequestMapping(value = "/mindsets/{mindset_id}", method = RequestMethod.DELETE)
    public void deleteMindset(@PathVariable("mindset_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		Mindset oldMindset = mindsetRepo.findOne(id);
		
		if ( oldMindset != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldMindset));
				mindsetRepo.delete(id);
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
