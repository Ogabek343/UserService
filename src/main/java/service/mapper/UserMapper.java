package service.mapper;

import service.model.dto.UserDto;
import service.model.entity.User;

public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto, User user);
}
