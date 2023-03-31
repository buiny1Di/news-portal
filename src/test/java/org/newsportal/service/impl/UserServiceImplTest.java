package org.newsportal.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.service.mapper.NewsMapper;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.model.News;
import org.newsportal.service.model.User;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    private static News news;
    private static User user;
    @BeforeAll
    public static void init() {
        news = new News("1", "Race" , "Schumaher" , user);
        user = new User("1", "Dima", "B", Collections.singletonList(news));
    }

    @Test
    void getAll() {

        userService.getAll();
        Mockito.verify(userMapper).mapToService(any(List.class));
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void getUserById() {
        userService.getUserById("1");
        Mockito.verify(userRepository).findUserById("1");
        Mockito.verify(userMapper).mapToService(userRepository.findUserById("1"));
    }

    @Test
    void addUser() {
        userService.addUser(user);
        org.newsportal.database.entity.User added = Mockito.verify(userMapper).mapToDatabase(user);
        Mockito.verify(userRepository).createUser(added);


    }

    @Test
    void changeUser() {

        News newsFuture = new News();
        newsFuture.setId("2");
        newsFuture.setTitle("Football");
        newsFuture.setContent("Argentina wins");

        User userFuture = new User("2", "Oleg", "Borisov", Collections.singletonList(newsFuture));
        newsFuture.setUser(user);

        userRepository.updateUser("1", userMapper.mapToDatabase(userFuture));
        org.newsportal.database.entity.User updatedUser = Mockito.verify(userMapper).mapToDatabase(userFuture);
        Mockito.verify(userRepository).updateUser("1", updatedUser);
        //Mockito.doReturn(newsFuture).when(newsRepository).findNewsById("1");
    }

    @Test
    void removeUser() {
        userService.removeUser(any());
        userRepository.deleteUser("1");
        Mockito.verify(userRepository).deleteUser("1");
    }
}