package courseLink;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/connections")
	String connected(Model model){
		
		LinkedInProfile profile = linkedIn.profileOperations().getUserProfileFull();
		model.addAttribute("FirstName", profile.getFirstName());
		model.addAttribute("LastName", profile.getLastName());
		model.addAttribute("ProfilePictureUrl", profile.getProfilePictureUrl());
		//model.addAttribute("Summary", profile.getSummary());
		//model.addAttribute("Industry", profile.getIndustry());
		model.addAttribute("Email", profile.getEmailAddress());
		model.addAttribute("Skills", linkedIn.profileOperations().getUserProfileFull().getSkills());
		
		//System.out.println(profile.getProfilePictureUrl());
		
		return "connect/connections";
	}
}	
