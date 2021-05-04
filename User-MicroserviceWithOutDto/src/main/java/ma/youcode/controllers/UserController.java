package ma.youcode.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.entities.ConfirmationToken;
import ma.youcode.entities.ERole;
import ma.youcode.entities.Role;
import ma.youcode.entities.User;
import ma.youcode.exeption.UserException;
import ma.youcode.repository.ConfirmationTokenRepository;
import ma.youcode.repository.RoleRepository;
import ma.youcode.repository.UserRepository;
import ma.youcode.requests.UserRequest;
import ma.youcode.responses.ErrorMessages;
import ma.youcode.responses.UserResponse;
import ma.youcode.services.EmailSenderService;
import ma.youcode.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasRole('ADMIN')")

	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		User userEntity = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userEntity, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	
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

	
	
	
	
	
	
	
	
	
	
	
//
//	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
//			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
//
//		if (userRequest.getFirstName().isEmpty())
//			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//		// Send Email Validation
////		if (userRequest.isAccepte()) {
////			SentEmail.sendEmail(userRequest.getEmail(), "Dear " + userRequest.getFirstName() + " "
////					+ userRequest.getLastName() + " " + " ! \r\n " + "Welcome to your account BasmaOnlineStore :)");
////		}
//
//		User user = new User();
//		BeanUtils.copyProperties(userRequest, user);
//
//		User createUser = userService.createUser(user);
//		
//		
//		
//		
//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//        confirmationTokenRepository.save(confirmationToken);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setFrom("chand312902@gmail.com");
//        mailMessage.setText("To confirm your account, please click here : "
//        +"http://localhost:8080/users/confirm-account?token="+confirmationToken.getConfirmationToken());
//        emailSenderService.sendEmail(mailMessage);
//        
//        
//        
//        
//		UserResponse userResponse = new UserResponse();
//
//		BeanUtils.copyProperties(createUser, userResponse);
//		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
//	}

	
	
	
	
	
	
	
	
	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		if (userRequest.getFirstName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		//UserRequest  signUpRequest = new  UserRequest();}
		System.out.println("+++++++++++++++"+userRequest.getAddresse()+"+++++++++++++++++++++"+userRequest.getRole());
		User user = new User(userRequest.getFirstName(),userRequest.getLastName(),
				userRequest.getCountact(),userRequest.getAddresse(),
				userRequest.getEmail(),userRequest.getPassword(),userRequest.getTime());

		Set<String> strRoles = userRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
	
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);

		
		BeanUtils.copyProperties(userRequest, user);

		User createUser = userService.createUser(user);

		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("chand312902@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/users/confirm-account?token=" + confirmationToken.getConfirmationToken());
		emailSenderService.sendEmail(mailMessage);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(createUser, userResponse);
		System.out.println(userResponse.getAddresse()+"kkkkkkkkkkkkkkkkkkkkkkkkk");
		System.out.println(userResponse.getRoles()+"kkklllllllllllllllllllllllllllllll");

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/confirm-account", method = RequestMethod.GET)
	public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			return "slm";
		} else {
			return "not khf";
		}
	}
	// getters and setters

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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

		User updateUser = userService.updateUser(id, user);
		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

}
