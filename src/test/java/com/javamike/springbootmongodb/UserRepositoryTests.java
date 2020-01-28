package com.javamike.springbootmongodb;

import com.javamike.entity.User;
import com.javamike.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    User javamike, naruto, internet;

    @BeforeEach
    public void setUp() {

        userRepository.deleteAll();

        javamike = userRepository.save(new User("javamike", "123456"));
        naruto = userRepository.save(new User("naruto", "123456"));
        internet = userRepository.save(new User("internet", "123123"));
    }

    @Test
    public void setsIdOnSave() {

        User javamike = userRepository.save(new User("javamike_save", "123456"));

        assertThat(javamike.getId()).isNotNull();
    }

    @Test
    public void findsByUsername() {

        List<User> result = userRepository.findByUsername("javamike");

        assertThat(result).hasSize(1).extracting("username").contains("javamike");
    }

    @Test
    public void findsByExample() {

        User javamike = new User(null, "123456");

        List<User> result = userRepository.findAll(Example.of(javamike));

        assertThat(result).hasSize(2).extracting("username").contains("javamike");
    }

}
