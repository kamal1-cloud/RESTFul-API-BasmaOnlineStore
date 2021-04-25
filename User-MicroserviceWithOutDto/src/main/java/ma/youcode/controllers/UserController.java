package ma.youcode.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.entities.User;
import ma.youcode.exeption.UserException;
import ma.youcode.repository.SentEmail;
import ma.youcode.requests.UserRequest;
import ma.youcode.responses.ErrorMessages;
import ma.youcode.responses.UserResponse;
import ma.youcode.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		User userEntity = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userEntity, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value = "page" , defaultValue = "1") int page,
			@RequestParam(value = "limit",defaultValue = "5") int limit) {
		List<UserResponse> usersResponse = new ArrayList<>();
		List<User> users = userService.getUsers(page, limit);
		for (User userFor : users) {
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userFor, user);
			usersResponse.add(user);

		}
		return usersResponse;
	}
	
	
	
	
	
	
	

	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		if (userRequest.getFirstName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if(userRequest.isAccepte()) {
			SentEmail.sendEmail(userRequest.getEmail(), "You're accepted");	
		}

		User user = new User();
		BeanUtils.copyProperties(userRequest, user);

		User createUser = userService.createUser(user);
		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(createUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping(path = "/{id}",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	
	
	
	
	
	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		
		User updateUser = userService.updateUser(id,user);
		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
	}

}
