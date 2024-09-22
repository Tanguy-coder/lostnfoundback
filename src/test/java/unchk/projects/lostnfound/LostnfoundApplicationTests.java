package unchk.projects.lostnfound;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.repos.UserRepository;

@SpringBootTest
class LostnfoundApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void createUser() {
		Roles role = new Roles("Chercheur");
		roleRepository.save(role);
		Users user = new Users("Tanguy","toor96","admin.admin.com",
				"admin","90710906",role);
		userRepository.save(user);
	}

}
