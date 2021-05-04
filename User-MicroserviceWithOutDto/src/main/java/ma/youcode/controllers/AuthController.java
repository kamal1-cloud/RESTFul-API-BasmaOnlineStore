package ma.youcode.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.entities.ConfirmationToken;
import ma.youcode.entities.ERole;
import ma.youcode.entities.Role;
import ma.youcode.entities.User;
import ma.youcode.exeption.UserException;
import ma.youcode.repository.ConfirmationTokenRepository;
import ma.youcode.repository.RoleRepository;
import ma.youcode.repository.UserRepository;
import ma.youcode.requests.UserLoginRequests;
import ma.youcode.requests.UserRequest;
import ma.youcode.responses.ErrorMessages;
import ma.youcode.responses.JwtResponse;
import ma.youcode.responses.UserResponse;
import ma.youcode.security.jwt.JwtUtils;
import ma.youcode.services.EmailSenderService;
import ma.youcode.services.UserService;
import ma.youcode.shared.UserDetailsImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;



	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
//
//	@Autowired
//	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequests loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	
	
	
	
	@PostMapping(value="signup",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
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

}
