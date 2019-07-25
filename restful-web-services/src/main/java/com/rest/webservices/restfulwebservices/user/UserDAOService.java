package com.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {

		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));

	}

	public List<User> findAll() {

		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;

	}

	public User findOne(int id) {

		for (int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {
				return users.get(i);
			}
		}
		return null;

	}

}
