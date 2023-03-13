package org.newsportal.service.impl;

import org.newsportal.database.repository.UserRepository;
import org.newsportal.service.UserService;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(userMapper.mapToService(userRepository.findAll()));
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(userMapper.mapToService(userRepository.findUserById(id)));
    }

    @Override
    public void addUser(User user) {
        userRepository.createUser(userMapper.mapToDatabase(user));
    }

    @Override
    public Optional<User> changeUser(String id, User user) {
        return Optional.ofNullable(userMapper.mapToService(
                userRepository.updateUser(id, userMapper.mapToDatabase(user))));
    }

    @Override
    public void removeUser(String id) {
        userRepository.deleteUser(id);
    }
}
