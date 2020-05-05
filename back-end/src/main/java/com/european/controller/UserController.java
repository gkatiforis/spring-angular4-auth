package com.european.controller;

import java.security.Principal;

import com.european.aop.SendEmail;
import com.european.model.User;
import com.european.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String id) {
		logger.info("Searching for: " + id);
		User user = (User) userService.getUser(id.trim());

		if(user != null)
			return new ResponseEntity<User>(user,  HttpStatus.OK);

		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
	}

	@SendEmail
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public  ResponseEntity<?> saveUser(@RequestBody User user) {
		logger.info("Register user: " + user);

		return new ResponseEntity<User>((User) userService.saveUser(user), HttpStatus.CREATED);
	}

	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}


}
