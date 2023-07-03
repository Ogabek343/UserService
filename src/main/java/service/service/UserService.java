package service.service;

import service.model.dto.ResponseDto;
import service.model.dto.UserDto;
import service.model.request.AuthenticationRequest;
import service.model.request.RegistrationRequest;

public interface UserService {
    ResponseDto<String> registerUser(RegistrationRequest request);

    ResponseDto<UserDto> getUserById(Long id);

    ResponseDto<String> authenticate(AuthenticationRequest request);

    ResponseDto<String> confirm(String userName, int code);

    ResponseDto<String> deleteByUserId(Long id);

    ResponseDto<UserDto> updateUser(UserDto userDto, Long id);
}
