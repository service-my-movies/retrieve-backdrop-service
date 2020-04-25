package com.mymovies.service;

import com.mymovies.dto.BackDropDTO;
import com.mymovies.dto.ImageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class BackDropService implements IBackDropService {
	
	@Value("${resource.api.url}")
	private String BASE_URL;
	
	@Value("${resource.api.url.image}")
	private String BASE_URL_IMAGE;
	
	@Value("${resource.api.key}")
	private String API_KEY;
	
	@Value("${resource.api.language}")
	private String Language;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackDropService.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public ArrayList<BackDropDTO> retrieveBackDrop(String movie_id) {
	
		ImageDTO images = null;
		
		try {
			images = restTemplate.getForObject(BASE_URL+movie_id+"/images"+API_KEY+Language, ImageDTO.class);
		}catch (Exception e) {
			LOGGER.error("Unexpected Error: retrieveBackDrop: " + e);
		}
		
		return images.getBackdrops();

	}
	

}
