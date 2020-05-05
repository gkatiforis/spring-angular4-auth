package com.european.services;

import com.european.config.CacheConfig;
import com.european.security.CacheAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.european.dao.UserRepository;
import com.european.model.User;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	CacheAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private CacheConfig cache;

	@PostConstruct
	public void initCatch(){

		cache.getUserCache().put("1234", new User());
	}
	/**
	 * Return User from cache
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return cache.getUserCache().containsKey(id) ? cache.getUserCache().get(id) : null;
	}

	/**
	 * Save user in cache and return stored object
	 * @param user
	 * @return
	 */

	public User saveUser(User user) {
		String id = generateId();
		user.setId(id);
		user.setPassword(id);
		cache.getUserCache().put(id, user);
		log.info("User saved with id: " + id);
		return user;
	}

	/**
	 * Generate random id
	 * @return
	 */
	public String generateId() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 5);
	}

}
