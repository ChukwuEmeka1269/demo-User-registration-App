package com.emirexco.demoRegistrationAndLoginApp;

import com.emirexco.demoRegistrationAndLoginApp.model.User;
import com.emirexco.demoRegistrationAndLoginApp.repository.UserRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PasswordEncoder encoder;

    @Test()
    public void createUserTest(){
        User user = new User();
        user.setEmail("admin@app.com");
        user.setFirstname("admin");
        user.setLastname("admin");
        user.setPassword(encoder.encode("admin"));

        User savedUser = userRepository.save(user);

        User existingUser = entityManager.find(User.class, savedUser.getId());

            assertThat(existingUser.getEmail().equals(savedUser.getEmail()));

            assertThat(existingUser.getFirstname().equals(savedUser.getFirstname()));

    }

    @Test
    public void findUserByEmail(){
        String email = "user@app45.com";

        User user1 = userRepository.findByEmail(email);

        assertThat(user1).isNotNull();
    }
}
