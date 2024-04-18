package tn.esprit.devminds.Controller;//package tn.esprit.devminds.Controllers;
//
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.servlet.http.HttpServletRequest;
//import net.bytebuddy.utility.RandomString;
//import org.springframework.data.repository.query.Param;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import tn.esprit.devminds.Entities.User;
//import tn.esprit.devminds.Services.UserService;
//
//import java.io.UnsupportedEncodingException;
//
//@Controller
//@RequestMapping("/api/Pwd")
//@CrossOrigin(origins = "http://localhost:4200")
//
//public class ForgotPasswordController {
//    private JavaMailSender mailSender;
//    private UserService userService;
//
//    @GetMapping("/forgot-password")
//    public String showForgotPasswordForm(Model model) {
//        model.addAttribute("pageTitle", "Forgot Password");
//        return "forgot-password";
//    }
//
//    @PostMapping("/forgot_password")
//    public String processForgotPassword(HttpServletRequest request, Model model) {
//        String email = request.getParameter("email");
//        String token = RandomString.make(45);
//
//        System.out.println("Email:" + email);
//        System.out.println("Token:" + token);
//
//
//        return "forgot-password";
//
//
//    }
//
//    public void sendEmail(String recipientEmail, String link)
//            throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("contact@shopme.com", "Shopme Support");
//        helper.setTo(recipientEmail);
//
//        String subject = "Here's the link to reset your password";
//
//        String content = "<p>Hello,</p>"
//                + "<p>You have requested to reset your password.</p>"
//                + "<p>Click the link below to change your password:</p>"
//                + "<p><a href=\"" + link + "\">Change my password</a></p>"
//                + "<br>"
//                + "<p>Ignore this email if you do remember your password, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
//
//
//    @GetMapping("/reset_password")
//    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//        User user = userService.getByResetPasswordToken(token);
//        model.addAttribute("token", token);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        }
//
//        return "reset_password_form"; }
//
//    @PostMapping("/reset_password")
//    public String processResetPassword(HttpServletRequest request, Model model) {
//        String token = request.getParameter("token");
//        String password = request.getParameter("password");
//
//        User user = userService.getByResetPasswordToken(token);
//        model.addAttribute("title", "Reset your password");
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        } else {
//            userService.updatePassword(user, password);
//
//            model.addAttribute("message", "You have successfully changed your password.");
//        }
//
//        return "message";
//    }
//
//
//}
