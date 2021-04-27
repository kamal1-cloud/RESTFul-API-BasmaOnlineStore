package ma.youcode.servicesImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.youcode.entities.User;
import ma.youcode.repository.UserRepository;

class UserServicesImpTest {

	@InjectMocks
	UserServicesImp userServicesImp;
	@InjectMocks
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUser() {
		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setFirstName("Yassine");
		userEntity.setLastName("Moumen");
		userEntity.setPassword("jkhshzd");
//		userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
		userEntity.setRole("User");
		userEntity.setAddresse("Youssofia Agndiz");
		userEntity.setCountact("0987654321");

		when(userRepository.findByEmail(anyString())).thenReturn(null);
		User user = userServicesImp.getUser("test@gmail.com");

		assertNotNull(user);
		assertEquals("khadija", user.getFirstName());

	}

}
