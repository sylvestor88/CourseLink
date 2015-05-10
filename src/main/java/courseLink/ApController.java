package courseLink;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.dto.CourseInfoBySkill;
import com.project.implementation.CourseraOperations;

@Controller
public class ApController {
	
	CourseraOperations cop=new CourseraOperations();
	List<CourseInfoBySkill> courses;
	
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
	
	@RequestMapping(value="/getcoursesbylinkedinskills/{skill}", method=RequestMethod.GET)
	public String getCourseByLinkedIn(Model model, @PathVariable String skill){
		
		
		courses=cop.getCoursesBySkills(skill);
		model.addAttribute("courses", courses);

		return "connect/courses";
		
	}
}	
