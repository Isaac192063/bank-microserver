package com.bank.utils.mappers;

import com.bank.user.dto.UserDTO;
import com.bank.user.entity.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T16:26:26-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userDtoTOUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDTO.getId() );
        userEntity.setName( userDTO.getName() );
        userEntity.setEmail( userDTO.getEmail() );
        userEntity.setDocument( userDTO.getDocument() );

        return userEntity;
    }

    @Override
    public UserDTO userEntityTOUserDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userEntity.getId() );
        userDTO.setName( userEntity.getName() );
        userDTO.setEmail( userEntity.getEmail() );
        userDTO.setDocument( userEntity.getDocument() );

        return userDTO;
    }
}
