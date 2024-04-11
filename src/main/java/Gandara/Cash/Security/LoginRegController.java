package Gandara.Cash.Security;

import Gandara.Cash.Dao.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")

public class LoginRegController{

    @GetMapping("/login")
    public String loginPage(){
        return "sign-in";
    }


}



