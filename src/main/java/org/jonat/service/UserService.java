package org.jonat.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jonat.entity.UserEntity;
import org.jonat.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity createUser(UserEntity user) {

        userRepository.persist(user);

        return user;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public UserEntity patchUser(UserEntity updateUser, Long id) {
        var user = this.findById(id);
        if (updateUser.username != null) {
            user.username = updateUser.username;
        }

        if (updateUser.password != null) {
            user.password = updateUser.password;
        }

        userRepository.persist(user);
        return user;
    }

    @Transactional
    public void deleteUser(Long id) {

        var user = this.findById(id);

        userRepository.delete(user);
    }

}
