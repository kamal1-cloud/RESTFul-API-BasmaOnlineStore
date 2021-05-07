package ma.youcode.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.models.User;
import ma.youcode.payload.request.SignupRequest;
import ma.youcode.payload.response.UserResponse;
import ma.youcode.security.servicesImp.UserServiceImp;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserController {
	
	@Autowired
	UserServiceImp userService;
//	@GetMapping("/all")
//	public String allAccess() {
//		return "Public Content.";
//	}
	
//	@GetMapping("/user")
//	@PreAuthorize("hasRole('USER')")
//	public String userAccess() {
//		return "User Content.";
//	}
//
//	@GetMapping("/mod")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String moderatorAccess() {
//		return "Moderator Board.";
//	}
//
//	@GetMapping("/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String adminAccess() {
//		return "Admin Board.";
//	}
	
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody SignupRequest userRequest) {
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);

		User updateUser = userService.updateUser(id, user);
		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "5") int limit) {
		List<UserResponse> usersResponse = new ArrayList<>();
		List<User> users = userService.getUsers(page, limit);
		for (User userFor : users) {
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userFor, user);
			usersResponse.add(user);

		}
		return usersResponse;
	}
	
	
	
	
	
	
	
	
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		User userEntity = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userEntity, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
