package service.Controller;

import service.model.dto.ResponseDto;
import service.model.dto.UserDto;
import service.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserDto>> getUserById(@PathVariable("userId") Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable("userId")Long id){
        return ResponseEntity.ok().body(userService.deleteByUserId(id));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable("userId")Long id){
        return ResponseEntity.ok().body(userService.updateUser(userDto, id));
    }
}
