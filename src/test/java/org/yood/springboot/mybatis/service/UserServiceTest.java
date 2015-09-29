package org.yood.springboot.mybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.repository.UserRepository;
import org.yood.springboot.mybatis.service.impl.UserServiceImpl;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService =  new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAdd() throws Exception {
        User user = new User("admin");
        user.setPassword("000");
        userService.add(user);
        userService.add(user);
        verify(userRepository, times(2)).save(user);
    }

    @Test
    public void testUpdate() throws Exception {
        userService.update(null);
//        verify(userRepository, times(1)).update(null);
    }

    @Test
    public void testGet() throws Exception {
        User user = new User("");
        when(userRepository.findByName(anyString())).thenReturn(user);
//        assertNotNull(userService.getByUserName(anyString()));
    }

    @Test
    public void testGetAll() throws Exception {
        User user = new User("");
        User user1 = new User("");
//        when(userRepository.selectAll()).thenReturn(Arrays.asList(user, user1));
//        assertEquals(userService.getAll().size(), 2);
    }
}