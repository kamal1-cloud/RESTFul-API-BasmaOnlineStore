package ma.youcode.servicesImp;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.youcode.entities.User;
import ma.youcode.repository.UserRepository;
import ma.youcode.services.UserService;
import ma.youcode.shared.Utils;

@Service
public class UserServicesImp implements UserService {
	@Autowired
	UserRepository userRepository;
//
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createUser(User user) {

		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exist !!");

		
		// Crypting password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// Genery idUser
		user.setUserId(utils.genereteStringId(30));

		userRepository.save(user);

		return user;

	}

	
	
	
	
	
	
	
	
	
	
	

	@Override
	// Récupérer User vai son adresse Email
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntities = userRepository.findByEmail(email);
		if (userEntities == null)
			throw new UsernameNotFoundException(email);

		return new org.springframework.security.core.userdetails.User(userEntities.getEmail(),
				userEntities.getPassword(), new ArrayList<>());
	}
	
	
	
	
	

	@Override
	public User getUser(String email) {

		User userEntities = userRepository.findByEmail(email);
		if (userEntities == null)
			throw new UsernameNotFoundException(email);
		return userEntities;
	}

//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
	@Override
	public User getUserByUserId(String userId) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		return userEntities;
	}

	
	
	
	
	
	
	
	
	@Override
	public User updateUser(String userId, User userUp) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		userEntities.setFirstName(userUp.getFirstName());
		userEntities.setLastName(userUp.getLastName());
		User userEntity = userRepository.save(userEntities);
//		UserDto user = new UserDto();
//		BeanUtils.copyProperties(userEntity, user);

		return userUp;

	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void deleteUser(String userId) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntities);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<User> getUsers(int page, int limit) {
		if(page > 0 )page -=1; 
		List<User> userList = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<User> userPage = userRepository.findAll(pageableRequest);

		List<User> users = userPage.getContent();
		for (User userEntity : users) {
			userList.add(userEntity);

		}

		return userList;
	}

	
	
	
	
	
	



}
