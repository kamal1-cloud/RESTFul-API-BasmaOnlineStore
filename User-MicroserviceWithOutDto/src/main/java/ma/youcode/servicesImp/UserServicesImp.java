package ma.youcode.servicesImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		user.setTime(new Date());
		// Crypting password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// Genery idUser
		user.setUserId(utils.genereteStringId(30));

		userRepository.save(user);

		return user;

	}


	
	
	
	

	@Override
	public User getUser(String email)  {

		User userEntities = userRepository.findByEmail(email);
		if (userEntities == null)
			throw new UsernameNotFoundException(email);
		return userEntities;
	}

	
	
	
	
	
	
	
	
	
	
	
	
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
		userRepository.save(userEntities);
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
		if (page > 0)
			page -= 1;
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
