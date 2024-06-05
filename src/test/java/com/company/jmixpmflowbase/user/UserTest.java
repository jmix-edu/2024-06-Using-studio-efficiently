package com.company.jmixpmflowbase.user;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.company.jmixpmflowbase.entity.User;
import com.company.jmixpmflowbase.test_support.AuthenticatedAsAdmin;
import io.jmix.core.DataManager;
import io.jmix.core.security.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sample integration test for the User entity.
 */
@SpringBootTest
@ExtendWith(AuthenticatedAsAdmin.class)
public class UserTest {

    @Autowired
    DataManager dataManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    User savedUser;

    @Test
    void test_saveAndLoad() {
        // Create and save a new User
        User user = dataManager.create(User.class);
        user.setUsername("test-user-" + System.currentTimeMillis());
        user.setPassword(passwordEncoder.encode("test-passwd"));
        savedUser = dataManager.save(user);

        // Check the new user can be loaded
        User loadedUser = dataManager.load(User.class).id(user.getId()).one();
        assertThat(loadedUser).isEqualTo(user);

        // Check the new user is available through UserRepository
        UserDetails userDetails = userRepository.loadUserByUsername(user.getUsername());
        assertThat(userDetails).isEqualTo(user);
    }

    @Test
    void testPage() {
        Selenide.open();
        SelenideElement element = $(By.name("some name"));
        element.shouldBe(visible);

    }

    @AfterEach
    void tearDown() {
        if (savedUser != null)
            dataManager.remove(savedUser);
    }
}
