package tk.scaks.resource.web.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserInfoController {

    @GetMapping("/user/info")
    @PermitAll
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt principal) {
        return Collections.singletonMap("user_name", principal.getClaimAsString("preferred_username"));
    }

    @GetMapping("/test")
    @PermitAll
    public String getUserInfo() {
        return "12345";
    }
}