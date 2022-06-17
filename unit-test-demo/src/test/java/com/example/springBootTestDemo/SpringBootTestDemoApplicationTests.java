package com.example.springBootTestDemo;

import com.example.springBootTestDemo.dto.UserDTO;
import com.example.springBootTestDemo.model.User;
import com.example.springBootTestDemo.repository.UserRepository;
import com.example.springBootTestDemo.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootTestDemoApplicationTests {
	@MockBean
	UserRepository userRepository;

	@Autowired
	IUserService userService;


	@Test
	public void saveUser(){

	}

	@Test
	public void listAllUsers(){
		Mockito.when(userRepository.findAll()).thenReturn(
				Stream.of(new User(1l, "Mohamed", "Gomaa", "mGomaa", "123456", "mohamed@gomaa.com", "0123456789")
				, new User(2l, "Alaa", "Gomaa", "aGomaa", "654321", "alaa@gomaa.com", "0135792468"))
						.collect(Collectors.toList()));
		Assertions.assertEquals(2, userService.listAllUsers().size());
	}

}
