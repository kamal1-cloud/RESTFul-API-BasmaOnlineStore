package ma.youcode.security.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.youcode.models.User;
@Service
public interface UserService {
//	User createUser(User user);
//
//	User getUser(String email);
//
	User getUserByUserId(String userId);
//
	User updateUser(String id, User userEntity);

	void deleteUser(String userId);

	List<User> getUsers(int page, int limit);
}
