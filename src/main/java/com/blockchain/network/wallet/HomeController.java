package com.blockchain.network.wallet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/wallet/home")
    public String home(Map<String, Object> model) {
        return "home";
    }
}
