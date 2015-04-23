package courseLink;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	
	@RequestMapping("/")
    String login() {
        return "home";
    }
}
