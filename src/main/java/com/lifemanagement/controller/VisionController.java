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
import com.lifemanagement.models.Vision;
import com.lifemanagement.repos.VisionRepo;

@CrossOrigin
@RestController
public class VisionController {
	
	private static final Logger logger = Logger.getLogger(VisionController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;
	
	
	@Autowired VisionRepo visionRepo;
	public VisionController() {
	}
	
	@RequestMapping(value = {"/visions", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<Vision> getAllVisions(HttpServletResponse response) {
		logger.debug("Get all Vision");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return visionRepo.findAll();
    }
	
	@RequestMapping(value = "/visions", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public Vision post( @RequestBody Vision newVision, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newVision));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newVision));
				visionRepo.save(newVision);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newVision;

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
	
	@RequestMapping(value = "/visions/{vision_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public Vision putVision( @RequestBody Vision updatedVision, @PathVariable("vision_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		Vision oldVision = visionRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedVision));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldVision != null ) {
			try {				
				updatedVision.set_id(oldVision.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedVision));
				visionRepo.save(updatedVision);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedVision;
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
	
	@RequestMapping(value = "/visions/{vision_id}", method = RequestMethod.DELETE)
    public void deleteVision(@PathVariable("vision_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		Vision oldVision = visionRepo.findOne(id);
		
		if ( oldVision != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldVision));
				visionRepo.delete(id);
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
