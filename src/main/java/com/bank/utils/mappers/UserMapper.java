package com.bank.utils.mappers;

import com.bank.user.dto.UserDTO;
import com.bank.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userDtoTOUserEntity(UserDTO userDTO);
    UserDTO userEntityTOUserDTO(UserEntity userEntity);
}
