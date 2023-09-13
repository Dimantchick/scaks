package tk.scaks.eureka.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for encrypt values to ENV
 */
@RestController
public class AdminController {

    private final StringEncryptor encryptor;

    public AdminController(@Qualifier("stringEncryptor") StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /**
     * Encrypt value to use in environment
     * @param value - value to encrypt
     * @return encrypted value to put in environment
     */
    @GetMapping("/encrypt")
    public String encrypt(@RequestParam(value = "value") String value) {
        return "ENC(" + encryptor.encrypt(value) + ")";
    }

    /*
     * Example endpoint for decrypt cars
     */
//    @GetMapping("/decrypt")
//    public String decrypt(@RequestParam(value = "pass", required = false) String password) {
//        return encryptor.decrypt(password);
//    }
}
