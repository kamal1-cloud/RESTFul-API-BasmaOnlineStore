package ma.youcode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import ma.youcode.exeption.UserExeption;
import ma.youcode.requests.UserRequest;
import ma.youcode.responses.ErrorMessages;
import ma.youcode.responses.UserResponse;
import ma.youcode.services.UserService;
import ma.youcode.shared.UserDto;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value = "page" , defaultValue = "1") int page,
			@RequestParam(value = "limit",defaultValue = "5") int limit) {
		List<UserResponse> usersResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		for (UserDto userDto : users) {
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userDto, user);
			usersResponse.add(user);

		}
		return usersResponse;
	}

	
	
	
	
	
	
	@PostMapping(
		    consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		    )
public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
	
	if(userRequest.getFirstName().isEmpty()) throw new UserExeption(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
	
	//UserDto userDto = new UserDto();
	//BeanUtils.copyProperties(userRequest, userDto);
	ModelMapper modelMapper = new ModelMapper();
	UserDto userDto = modelMapper.map(userRequest, UserDto.class);
	
	UserDto createUser = userService.createUser(userDto);
	
	UserResponse userResponse =  modelMapper.map(createUser, UserResponse.class);
	
	return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		// la coche repr??seentation
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// passer information ver le service
		UserDto updateUser = userService.updateUser(id, userDto);

		// create reponse
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
