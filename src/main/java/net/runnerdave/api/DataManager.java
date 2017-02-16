package net.runnerdave.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import net.runnerdave.api.services.v1.User;

public class DataManager {
	private static final Logger log = Logger.getLogger(DataManager.class.getName());
	private static DataManager INSTANCE;
	private static DB japiDB;

	private static DBCollection userCollection;

	public static DataManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataManager();
		}
		return INSTANCE;
	}

	private DataManager() {
		try {
			MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));

			japiDB = mongoClient.getDB("japi");

			userCollection = japiDB.getCollection("users");

		} catch (Exception e) {
			log.error("db connection error e=", e);
		}
	}

	// Insert User into database
	public User insertUser(User user) {

		// Get a document object
		BasicDBObject doc = new BasicDBObject();

		// Add in name
		doc.put("name", user.getName());

		// Insert document into users collection
		userCollection.insert(doc);

		// Put new id into user object
		user.setId(doc.get("_id").toString());

		// return new object
		return user;

	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		
		DBCursor doc = userCollection.find();
		
		if (doc != null) {
			while (doc.hasNext()) {
				BasicDBObject obj = (BasicDBObject)doc.next();  
				users.add(mapUserFromDBObject(obj));
			}
		}
		return users;
	}

	public User findUserByID(String userId) {
		
		if (userId == null) {
			return null;
		}
		
		try {
			// Get a document object
			DBObject doc = new BasicDBObject("_id", new ObjectId(userId));
			
			DBObject userObject = userCollection.findOne(doc);
			
			if (userObject == null) {
				return null;
			} else {
				return mapUserFromDBObject(userObject);
			}				
			
		} catch (Exception e) {
			log.error("DataManager::findUserByID error:" + e.getMessage());
		}
		
		return null;
	}
	
	private User mapUserFromDBObject(DBObject obj) {
		User user = new User();
		user.setId(obj.get("_id").toString());
		user.setName((String)obj.get("name"));
		return user;
	}
	
	public User updateUserAttribute(String userId, String attribute,
			String value) {

		String updateValue = value;

		BasicDBObject doc = new BasicDBObject();

		doc.append("$set", new BasicDBObject().append(attribute, updateValue));

		DBObject searchById = new BasicDBObject("_id", new ObjectId(userId));

		userCollection.update(searchById, doc);


		return findUserByID(userId);
	}

	public void deleteUser(String userId) {
		DBObject doc = new BasicDBObject("_id", new ObjectId(userId));
		userCollection.remove(doc);
	}

}
