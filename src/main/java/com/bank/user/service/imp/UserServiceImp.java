package com.bank.user.service.imp;

import com.bank.user.entity.UserEntity;
import com.bank.user.repository.UserRepository;
import com.bank.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        try {
            UserEntity validateUser = this.getUserByDocument(user.getDocument());

            if (validateUser != null) return validateUser;

            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity getUserByDocument(String document) {
        Optional<UserEntity> userEntity = userRepository.findByDocument(document);

        return userEntity.orElse(null);
    }
}
