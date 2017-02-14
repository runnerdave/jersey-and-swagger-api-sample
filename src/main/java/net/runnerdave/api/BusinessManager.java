package net.runnerdave.api;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import net.runnerdave.api.services.v1.User;

public class BusinessManager {
	
	private static final Logger log = Logger.getLogger(BusinessManager.class.getName());

	private BusinessManager() {
	}

	private static BusinessManager INSTANCE;

	public static BusinessManager getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		} else {
			return new BusinessManager();
		}
	}
	
	public User getUserById(String id) {
		log.info("BusinessManager::getUserById started userId=" + id);
		User user = new User();
    	user.setId("111125");
    	user.setName("Tom Jay");
    	return user;
	}
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		log.info("BusinessManager::getUsers started");
		
		User user = new User();
    	user.setId("111125");
    	user.setName("Tom Jay");
    	users.add(user);
    	user = new User();
    	user.setId("111126");
    	user.setName("Paul Gay");
    	users.add(user);
    	
    	return users;
	}

	public User addUser(User user) {
		User newUser = new User();
		newUser.setId("111126");
		newUser.setName("Petra Perez");
		return newUser;
	}

	public User updateUserAttribute(String userId, String attribute, String value) {
		User user = new User();
		user.setId(userId);
		
		if (attribute == "name") {
			user.setName(value);
		}
		
		return user;
	}

	public void deleteUser(String userId) {
		return;
	}
	
	

}
