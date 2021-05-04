package ma.youcode.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import ma.youcode.entities.User;

@Repository
public interface UserService{

	User createUser(User user);

	User getUser(String email);

	User getUserByUserId(String userId);

	User updateUser(String id, User userEntity);

	void deleteUser(String userId);

	List<User> getUsers(int page, int limit);

//	public UserDetails loadUserByUsername(String username);

}
