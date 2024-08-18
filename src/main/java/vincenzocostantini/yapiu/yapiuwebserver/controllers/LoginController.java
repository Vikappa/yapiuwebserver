package vincenzocostantini.yapiu.yapiuwebserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vincenzocostantini.yapiu.yapiuwebserver.payload.loginDTO.UserLoginDTO;
import vincenzocostantini.yapiu.yapiuwebserver.payload.loginDTO.UserLoginResponseDTO;


@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Validated UserLoginDTO userLoginDTO){
        return ResponseEntity.ok("Stocazzo!");
    }

}
