package tn.esprit.devminds.Service;

import jakarta.servlet.http.HttpServletRequest;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.AuthResponseDto;
import tn.esprit.devminds.Payload.LoginDto;

public interface AuthenticationService {
    String register(UserEntity user, String roleName);

    AuthResponseDto login(LoginDto loginDto);

    AuthResponseDto refreshToken(HttpServletRequest request);

    String logout();
}
