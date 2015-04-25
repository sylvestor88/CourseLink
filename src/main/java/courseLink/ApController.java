package courseLink;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
	
	@RequestMapping(method=RequestMethod.GET)
	public String helloLinkedIn(Model model){
		if(!linkedIn.isAuthorized())
			return "redirect:/connect/linkedin";
		else
			return "/connect/linkedinConnected";

		//		model.addAttribute(linkedIn.profileOperations().getUserProfile().getFirstName());
//		return linkedin.profileOperations().getUserProfile().getFirstName();
	}
	
	/*@RequestMapping("/connections")
	String connected(Model model){
		model.addAttribute(linkedIn.profileOperations().getUserProfile().getFirstName());
		LinkedInProfile profile = linkedIn.profileOperations().getUserProfileFull();
		return "connections";
	}*/
	
}
