package com.smoothstack.december.rsuserservice.service;

import com.smoothstack.december.rsuserservice.dao.UserDao;
import com.smoothstack.december.rsuserservice.model.User;
import com.smoothstack.december.rsuserservice.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(int id) {
        Optional<User> result = userDao.findById(id);
        return result.orElse(null);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(String email) {
        Optional<User> queryResult = Optional.ofNullable(userDao.findByEmail(email));
        return queryResult.orElse(null);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User createUser(UserDto user, int role) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setRole(role);
        newUser.setDeleted(false);
        return userDao.save(newUser);
    }

    public User updateUser(UserDto user) {
        User updatedUser = userDao.findByEmail(user.getEmail());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setDeleted(false);
        return userDao.save(updatedUser);
    }

    public User deleteUser(String email) {
        User user = getUser(email);
        user.setDeleted(true);
        return userDao.save(user);
    }
}
