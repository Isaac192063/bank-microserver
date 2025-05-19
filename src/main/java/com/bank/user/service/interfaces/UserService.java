package com.bank.user.service.interfaces;

import com.bank.user.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserByDocument(String document);
}
