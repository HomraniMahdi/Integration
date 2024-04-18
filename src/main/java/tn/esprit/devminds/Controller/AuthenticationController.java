package tn.esprit.devminds.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.AuthResponseDto;
import tn.esprit.devminds.Payload.LoginDto;
import tn.esprit.devminds.Service.AuthenticationService;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    private String register(@RequestParam(value = "roleName") String roleName, @RequestBody UserEntity user) {
        return this.authenticationService.register(user, roleName);
    }

    @PostMapping("/login")
    private AuthResponseDto login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @PostMapping("/refresh")
    private AuthResponseDto refreshToken(HttpServletRequest request) {
        return this.authenticationService.refreshToken(request);
    }

    @PostMapping("/logout")
    private String logout() {
        return this.authenticationService.logout();
    }
}

