package service.mapper;

import service.model.dto.UserDto;
import service.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImp implements UserMapper{
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setId(userDto.getId());
        user.setBirthDate(userDto.getBirthDate());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        return user;
    }
}
