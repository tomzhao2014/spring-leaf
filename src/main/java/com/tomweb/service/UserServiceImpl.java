package com.tomweb.service;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.core.service.BaseEntityService;
import com.tomweb.entity.User;
import com.tomweb.repository.UserRepository;
import com.tomweb.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/4 0004
 * Time: 17:23
 */
@Service
public class UserServiceImpl extends BaseEntityService<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public EntityRepository<User> getEntityRepository() {
        return userRepository;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

        User user = userRepository.get(userId);
        user.setPassword(newPassword);
        (new PasswordHelper()).encryptPassword(user);
        userRepository.update(user);


    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
