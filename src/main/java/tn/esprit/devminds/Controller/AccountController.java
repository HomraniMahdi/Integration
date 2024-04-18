package tn.esprit.devminds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.UserEntity;
import tn.esprit.devminds.Payload.ChangePasswordDto;
import tn.esprit.devminds.Payload.ResetPasswordDto;
import tn.esprit.devminds.Payload.UpdateProfileDto;
import tn.esprit.devminds.Service.AccountService;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/forgot-password")
    private String forgotPassword(@RequestParam(value = "emailAddress") String emailAddress) {
        return this.accountService.forgetPassword(emailAddress);
    }

    @PostMapping("/reset-password")
    private String resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return this.accountService.resetPassword(resetPasswordDto);
    }

    @GetMapping("/list-user-accounts")
    private List<UserEntity> listUserAccounts() {
        return this.accountService.listUserAccounts();
    }

    @PostMapping("/enable-account")
    private String enableAccount(@RequestParam(value = "userId") Long userId) {
        return this.accountService.enableAccount(userId);
    }

    @PostMapping("/disable-account")
    private String disableAccount(@RequestParam(value = "userId") Long userId) {
        return this.accountService.disableAccount(userId);
    }

    @GetMapping("/profile")
    public UserEntity getProfile(Principal principal) {
        return this.accountService.getProfile(principal.getName());
    }

    @PostMapping("change-password")
    public String changePassword(Principal principal, @RequestBody ChangePasswordDto changePasswordDto) {
        return this.accountService.changePassword(principal.getName(), changePasswordDto);
    }

    @PostMapping("update-profile")
    public String updateProfile(Principal principal, @RequestBody UpdateProfileDto updateProfileDto) {
        return this.accountService.updateProfile(principal.getName(), updateProfileDto);
    }

    @PostMapping("activate-account")
    public String activateAccount(@RequestParam String confirmationKey) {
        return this.accountService.activateAccount(confirmationKey);
    }
}

