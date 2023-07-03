package service.Controller;

import service.model.dto.ResponseDto;
import service.model.request.AuthenticationRequest;
import service.model.request.RegistrationRequest;
import service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<String>> registration(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok().body(userService.registerUser(request));
    }
    @PostMapping("/confirm/{username}")
    public ResponseEntity<ResponseDto<String>> confirm(@PathVariable("username")String username, @RequestParam("code")int code){
        return ResponseEntity.ok().body(userService.confirm(username, code));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto<String>>authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok().body(userService.authenticate(request));
    }


}
