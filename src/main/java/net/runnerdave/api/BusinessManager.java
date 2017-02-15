package net.runnerdave.api;

import java.util.List;

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
	
	public User getUserById(String id) throws Exception {
		log.info("BusinessManager::getUserById started userId=" + id);
		User user = DataManager.getInstance().findUserByID(id);
		if (user == null) {
			throw new Exception("User not found!");
		}
    	return user;
	}
	
	public List<User> getUsers() {		    	
    	return DataManager.getInstance().getAllUsers();
	}

	public User addUser(User user) {
		User newUser = DataManager.getInstance().insertUser(user);
		return newUser;
	}

	public User updateUserAttribute(String userId, String attribute, String value) {
		return DataManager.getInstance().updateUserAttribute(userId, attribute, value);
	}

	public void deleteUser(String userId) {
		return;
	}
	
	

}
