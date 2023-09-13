package tk.scaks.eureka.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping("/admin/logout")
    public String logout2(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/logout";
    }
}
