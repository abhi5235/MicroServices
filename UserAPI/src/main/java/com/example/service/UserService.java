package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.ContactDTO;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.exception.UserAPIException;
import com.example.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@RefreshScope
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RestTemplate restTemplate;

	@Value("${url}")
	String url;

//	@Autowired
//	DiscoveryClient client
	@CircuitBreaker(name = "user-service", fallbackMethod = "getUserfallback")
	public UserDTO addUserToRepo(UserDTO userDTO) {
		User user = userDTO.convertToEntity();
		Optional<User> findById = userRepo.findById(user.getUserId());
		if (findById.isPresent()) {
			throw new UserAPIException("User with this user id is already available");
		}
		ContactDTO contactDTO = userDTO.getContactDTO();
		contactDTO.setUserid(userDTO.getUserId());
		// take all instances of contact-service from consul in the list
//		List<ServiceInstance> instances = client.getInstances("contact-service");
//		String uri = instances.get(0).getUri().toString();
//		String uri = "http://contact-service";
		ContactDTO addObject = restTemplate.postForObject(url + "/contact", contactDTO, ContactDTO.class);
		User userSaved = userRepo.save(user);
		UserDTO convertedDTO = userSaved.convertToDTO(user);
		convertedDTO.setContactDTO(addObject);
		return convertedDTO;
	}

	public UserDTO getUserfallback(UserDTO userDTO, Throwable throwable) {
		System.out.println("----fallback-----");
		return new UserDTO();
	}
    @CircuitBreaker(name = "user-service",fallbackMethod = "getuserfallback")
	public User getUserDataFromDB(int userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		User user = optionalUser.get();
		return user;
	}
    public User getuserfallback(int id,Throwable throwable) {
    	System.out.println("-----fallback-----"+id);
		return new User();
    }
}
