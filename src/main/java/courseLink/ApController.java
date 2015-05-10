package courseLink;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApController {
	
	private LinkedIn linkedIn;
	
	@Inject
	public ApController(LinkedIn linkedin)
	{
		this.linkedIn = linkedin;
	}
	
	@RequestMapping("/")
    String login() {
        return "login";
    }
}	
