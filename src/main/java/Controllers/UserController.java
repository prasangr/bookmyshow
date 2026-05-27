package Controllers;

import DTOs.SignUpRequestDto;
import DTOs.SignUpResponseDto;
import Models.ResponseStatus;
import Models.User;
import Repositories.UserRepository;
import Services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.Optional;


@Getter
@Setter
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());
        SignUpResponseDto responseDto = new SignUpResponseDto();
        if (optionalUser.isEmpty()) {
            // signup
            try {
                User user = userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
                responseDto.setUserId(user.getId());
            } catch (Exception exception) {
                responseDto.setResponseStatus(ResponseStatus.FAILURE);
            }
        } else {
            // login
            try {
                User user = userService.login(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
                responseDto.setUserId(user.getId());
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            } catch (Exception exception) {
                responseDto.setResponseStatus(ResponseStatus.FAILURE);
            }
        }
        return responseDto;
    }
}
