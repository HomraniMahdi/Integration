package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.ChangePasswordDto;
import tn.esprit.devminds.Payload.ResetPasswordDto;
import tn.esprit.devminds.Payload.UpdateProfileDto;

import java.util.List;

public interface AccountService {

    String forgetPassword(String emailAddress);

    String resetPassword(ResetPasswordDto resetPasswordDto);

    List<UserEntity> listUserAccounts();

    String enableAccount(Long userId);

    String disableAccount(Long userId);

    UserEntity getProfile(String userName);

    String updateProfile(String userName, UpdateProfileDto updateProfileDto);

    String changePassword(String userName, ChangePasswordDto changePasswordDto);

    String activateAccount(String confirmationKey);
}
