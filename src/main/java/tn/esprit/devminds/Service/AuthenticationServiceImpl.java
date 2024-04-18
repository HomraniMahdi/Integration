package tn.esprit.devminds.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.devminds.Entities.ConfirmationKey;
import tn.esprit.devminds.Entities.RoleEntity;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.AuthResponseDto;
import tn.esprit.devminds.Payload.LoginDto;
import tn.esprit.devminds.Payload.Validation;
import tn.esprit.devminds.Repository.KeyRepository;
import tn.esprit.devminds.Security.JwtGenerator;
import tn.esprit.devminds.error.BadRequestException;
import tn.esprit.devminds.Repository.UserEntityRepo;
import tn.esprit.devminds.Repository.RoleEntityRepo;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserEntityRepo userRepository;
    @Autowired
    RoleEntityRepo roleRepository;
    @Autowired
    Validation validation;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtGenerator jwtGenerator;
    @Value("${refresh.token.secret}")
    private String refreshTokenSecret;
    @Autowired
    KeyRepository keyRepository;
    @Value("${access.token.expiration}")
    private Long accessTokenExpiration;
    @Value("${access.token.secret}")
    private String accessTokenSecret;
    @Autowired
    EmailService emailService;

  //  private JavaMailSender mailSender;



    @Override
    public String register(UserEntity user, String roleName) {
        if (!validation.usernameValidation(user.getUserName()) || !validation.passwordValidation(user.getPassword()) ||
                !validation.emailValidation(user.getEmailAddress()))
            throw new BadRequestException("Username, email-address or password invalid");
        if (this.userRepository.existsByUserNameOrEmailAddress(user.getUserName(), user.getEmailAddress()))
            throw new BadRequestException("Username or " +
                    "email-address already used");
        RoleEntity role = this.roleRepository.findByRoleName(roleName);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        String keyValue = UUID.randomUUID().toString();
        this.generateAndPersistKey(keyValue, user.getEmailAddress());
        this.sendEmail(user.getEmailAddress(), keyValue);/////
        return "User saved successfully";
    }

    private void generateAndPersistKey(String keyValue, String emailAddress) {
        ConfirmationKey confirmationKey = new ConfirmationKey();
        confirmationKey.setEmailAddress(emailAddress);
        confirmationKey.setValue(keyValue);
        keyRepository.save(confirmationKey);
    }

    private void sendEmail(String emailAddress, String keyValue) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("Activate account");
        simpleMailMessage.setFrom("Mouhamed.ounalli@gmail.com");
        simpleMailMessage.setText("To activate your account add this confirmation Key : " + keyValue);
        emailService.send(simpleMailMessage);
    }

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = getAuthentication(loginDto.getUserName(), loginDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtGenerator.generateTokens(authentication);
    }

    @Override
    public AuthResponseDto refreshToken(HttpServletRequest request) {
        String token = jwtGenerator.getJWTFromRequest(request);
        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token, refreshTokenSecret)) {
            UserEntity userEntity = this.getUser(jwtGenerator.getUsernameFromToken(token, refreshTokenSecret));
            return new AuthResponseDto(this.generateAccessToken(userEntity), null, "New access token", null);
        }
        return new AuthResponseDto(null, null, "Refresh token invalid", null);
    }

    @Override
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) SecurityContextHolder.clearContext();
        return "Logged out";
    }

    private String generateAccessToken(UserEntity userEntity) {
        Date currentDate = new Date();
        Claims claims = Jwts.claims().setSubject(userEntity.getUserName());
        claims.put("roles", userEntity.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + this.accessTokenExpiration))
                .signWith(SignatureAlgorithm.HS512, accessTokenSecret)
                .compact();
    }

    private Authentication getAuthentication(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));
        return authentication;
    }

    private UserEntity getUser(String userName) {
        return this.userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Bad Credential"));
    }
}
