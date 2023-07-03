package service.service;

import service.exception.BaseException;
import service.exception.NotFoundException;
import service.mapper.UserMapper;
import service.model.constants.Errors;
import service.model.constants.Role;
import service.model.dto.ResponseDto;
import service.model.dto.UserDto;

import service.model.entity.ConfirmationCode;
import service.model.entity.User;
import service.model.request.AuthenticationRequest;
import service.model.request.RegistrationRequest;
import service.model.request.NotificationRequest;
import service.repo.ConfirmationCodeRepository;
import service.repo.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import static service.model.constants.Errors.*;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository codeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final NotificationService notificationService;

    @Override
    public ResponseDto<String> registerUser(RegistrationRequest request) {
        boolean exists = userRepository.existsByUsername(request.getUsername());
        if (exists){
            throw new BaseException(Errors.USERNAME_TAKEN);
        }
        int code = new Random().nextInt(100000,999999);
        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                Role.USER,
                false
        );

        ConfirmationCode confirmationToken = new ConfirmationCode(
                code,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                user
        );
        userRepository.save(user);
        codeRepository.save(confirmationToken);
        notificationService.sendOTP(new NotificationRequest(
                "Your confirmation code: "+code,user.getEmail()));
        return new ResponseDto<>("We have sent confirmation code");
    }

    @Override
    public ResponseDto<UserDto> getUserById(Long id) {
        var userDto = userMapper.toDto(findUserById(id));
        return new ResponseDto<>("userDto", userDto);
    }
    @Override
    public ResponseDto<String> authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new BaseException(USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new BaseException(WRONG_USER_CREDENTIALS);
        }
        return new ResponseDto<>(jwtService.generateToken(user));
    }

    @Override
    public ResponseDto<String> confirm(String userName, int code) {
        ConfirmationCode confirmationCode = codeRepository.findByCode(code)
                .orElseThrow(() -> new BaseException(CODE_NOT_FOUND));
        if (!confirmationCode.getUser().getUsername().equals(userName)){
            throw new BaseException(CODE_NOT_ACCEPTABLE);
        }
        if (confirmationCode.getConfirmedAt()!=null){
            throw new BaseException(CODE_ALREADY_CONFIRMED);
        }
        if (confirmationCode.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new BaseException(CODE_EXPIRED);
        }
        if (confirmationCode.getUser().isEnabled()){
            throw new BaseException(USER_ALREADY_CONFIRMED);
        }
        confirmationCode.getUser().setEnabled(true);
        confirmationCode.setConfirmedAt(LocalDateTime.now());
        userRepository.save(confirmationCode.getUser());
        codeRepository.save(confirmationCode);
        return new ResponseDto<>("User confirmed");
    }

    @Override
    public ResponseDto<String> deleteByUserId(Long id) {
        if (!userRepository.existsById(id)){
            throw new NotFoundException(USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseDto<>("User deleted");
    }

    @Override
    public ResponseDto<UserDto> updateUser(UserDto userDto, Long id) {
        User user = userRepository.save(userMapper.toEntity(userDto, findUserById(id)));
        return new ResponseDto<>("userDto", userMapper.toDto(user));
    }
    private User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
    }
}
