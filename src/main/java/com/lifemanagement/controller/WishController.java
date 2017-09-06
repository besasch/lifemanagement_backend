package com.lifemanagement.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifemanagement.models.Wish;
import com.lifemanagement.repos.WishRepo;
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
public class WishController {

	private static final Logger logger = Logger.getLogger(WishController.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${spring.data.mongodb.uri}")
	public String mongoURI;


	@Autowired WishRepo wishRepo;
	public WishController() {
	}
	
	@RequestMapping(value = {"/wishs", "/"}, method = RequestMethod.GET, produces="application/json")
    public List<Wish> getAllWishs(HttpServletResponse response) {
		logger.debug("Get all Wish");
		try {
			logger.info(this.mongoURI);
			logger.info(InetAddress.getLocalHost().getHostName());
			logger.info(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("no host name could be found");
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return wishRepo.findAll();
    }
	
	@RequestMapping(value = "/wishs", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public Wish post( @RequestBody Wish newWish, HttpServletResponse response) {
		logger.info("got HTTP POST request");
		try {
			System.out.println(objectMapper.writeValueAsString(newWish));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			logger.info("data: " + objectMapper.writeValueAsString(newWish));
				wishRepo.save(newWish);
				response.setStatus(HttpServletResponse.SC_CREATED); 
				return newWish;

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
	
	@RequestMapping(value = "/wishs/{wish_id}", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
	public Wish putWish( @RequestBody Wish updatedWish, @PathVariable("wish_id") String id, HttpServletResponse response) {
		System.out.println("PUT");
		Wish oldWish = wishRepo.findOne(id);
		//Set URL Wizard if applicable, Should be set from a deviceTemplate (to be created)
		
		try {
			System.out.println(objectMapper.writeValueAsString(updatedWish));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if ( oldWish != null ) {
			try {				
				updatedWish.set_id(oldWish.get_id());
				System.out.println(objectMapper.writeValueAsString(updatedWish));
				wishRepo.save(updatedWish);
			
				response.setStatus(HttpServletResponse.SC_OK);	
				return updatedWish;
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
	
	@RequestMapping(value = "/wishs/{wish_id}", method = RequestMethod.DELETE)
    public void deleteWish(@PathVariable("wish_id") String id,  HttpServletResponse response) {
		System.out.println("DELETE");
		Wish oldWish = wishRepo.findOne(id);
		
		if ( oldWish != null ) {
			try {
				System.out.println(objectMapper.writeValueAsString(oldWish));
				wishRepo.delete(id);
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
