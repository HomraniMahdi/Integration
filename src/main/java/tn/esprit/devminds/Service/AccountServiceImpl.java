package tn.esprit.devminds.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.ConfirmationKey;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.ChangePasswordDto;
import tn.esprit.devminds.Payload.ResetPasswordDto;
import tn.esprit.devminds.Payload.UpdateProfileDto;

import tn.esprit.devminds.Repository.KeyRepository;
import tn.esprit.devminds.Repository.UserEntityRepo;
import tn.esprit.devminds.error.BadRequestException;
import tn.esprit.devminds.error.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserEntityRepo userRepository;
    @Autowired
    KeyRepository keyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;

    @Override
    public String forgetPassword(String emailAddress) {
        this.checkIfUserHasGeneratedAKey(emailAddress);
        String keyValue = UUID.randomUUID().toString();
        this.generateAndPersistKey(keyValue, emailAddress);
        this.sendEmail(emailAddress, keyValue);///
        return "We have sent an email to reset your password";
    }

    //@Value("$(spring.mail.username)")
    ///private String fromMail;
    private void sendEmail(String emailAddress, String keyValue) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("Password reset");
        simpleMailMessage.setFrom("Mouhamed.ounalli@gmail.com");
        simpleMailMessage.setText("To change your password add this confirmation Key : " + keyValue);
        emailService.send(simpleMailMessage);
    }

    @Override
    public String resetPassword(ResetPasswordDto resetPasswordDto) {
        this.validateResetPassword(resetPasswordDto);
        UserEntity userEntity = this.userRepository.findByEmailAddress(this.keyRepository.getEmailAddress(resetPasswordDto.getConfirmationKeyValue())).get();
        userEntity.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        this.userRepository.save(userEntity);
        ConfirmationKey confirmationKey = this.keyRepository.findByValue(resetPasswordDto.getConfirmationKeyValue());
        this.keyRepository.delete(confirmationKey);
        return "Your password has been updated successfully";
    }

    @Override
    public List<UserEntity> listUserAccounts() {
        return this.userRepository.findAll();
    }

    @Override
    public String enableAccount(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).get();
        userEntity.setAccountStatus(true);
        this.userRepository.save(userEntity);
        return "Account enabled";
    }

    @Override
    public String disableAccount(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).get();
        userEntity.setAccountStatus(false);
        this.userRepository.save(userEntity);
        return "Account disabled";
    }

    @Override
    public UserEntity getProfile(String userName) {
        return this.userRepository.findByUserName(userName).get();
    }

    @Override
    public String updateProfile(String userName, UpdateProfileDto updateProfileDto) {
        UserEntity userEntity = this.userRepository.findByUserName(userName).get();
        userEntity.setFirstName(updateProfileDto.getFirstName());
        userEntity.setLastName(updateProfileDto.getLastName());
        userEntity.setPhoneNumber(updateProfileDto.getPhoneNumber());
        this.userRepository.save(userEntity);
        return "Profile updated";
    }

    @Override
    public String changePassword(String userName, ChangePasswordDto changePasswordDto) {
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmation()))
            throw new BadRequestException("Confirm your password again");
        UserEntity userEntity = this.userRepository.findByUserName(userName).get();
        userEntity.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        this.userRepository.save(userEntity);
        return "Password updated";
    }

    @Transactional
    @Override
    public String activateAccount(String confirmationKey) {
        this.checkIfConfirmationKeyValueIsValid(confirmationKey);
        UserEntity userEntity = this.getUserByEmailAddress(this.keyRepository.getEmailAddress(confirmationKey));
        userEntity.setAccountStatus(true);
        this.userRepository.save(userEntity);
        ConfirmationKey key = this.keyRepository.findByValue(confirmationKey);
        this.keyRepository.delete(key);
        return "Account activated";
    }

    private void validateResetPassword(ResetPasswordDto resetPasswordDto) {
        this.checkIfConfirmationKeyValueIsValid(resetPasswordDto.getConfirmationKeyValue());
        this.validateNewPassword(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmedNewPassword());
    }

    private void checkIfConfirmationKeyValueIsValid(String confirmationKeyValue) {
        if (!this.keyRepository.existsByValue(confirmationKeyValue))
            throw new BadRequestException("confirmation key value invalid");
    }

    private void validateNewPassword(String newPassword, String confirmedNewPassword) {
        if (!Objects.equals(newPassword, confirmedNewPassword))
            throw new BadRequestException("New password confirmation invalid");
    }

    private void checkIfUserHasGeneratedAKey(String emailAddress) {
        if (this.keyRepository.existsByEmailAddress(emailAddress))
            throw new BadRequestException("We have already sent an email to reset your password");
    }

    private UserEntity getUserByEmailAddress(String emailAddress) {
        return this.userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new NotFoundException("Email address invalid"));
    }

    private void generateAndPersistKey(String keyValue, String emailAddress) {
        ConfirmationKey confirmationKey = new ConfirmationKey();
        confirmationKey.setEmailAddress(emailAddress);
        confirmationKey.setValue(keyValue);
        keyRepository.save(confirmationKey);
    }
}
