package courseLink;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.dto.CourseInfoBySkill;
import com.project.dto.Courses;
import com.project.dto.Tag;
import com.project.dto.UserInfo;
import com.project.implementation.CourseraOperations;
import com.project.implementation.StackOverflowOperations;
import com.project.util.EmailNotification;

@Controller
public class ApController {
	
	@Autowired
    private CourseRepository courseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	CourseraOperations cop = new CourseraOperations();
	StackOverflowOperations sop = new StackOverflowOperations();
	
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
	
	@RequestMapping(value="/graph", method=RequestMethod.GET)
	public String getGraphs(Model model){
	
		
/*		List<Tag> trending;
		
		trending = sop.getTopTrending();
		
		//System.out.println("Testing");
		
		model.addAttribute("trending", trending);*/
		
		return "graph";
		
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String registerForm(Model model) {

		model.addAttribute("user", new UserInfo());
		
		return "subscribe";
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute UserInfo user,
			Model model) {

		model.addAttribute("user", user);		
		
		userRepo.save(user);
		
		EmailNotification email = new EmailNotification();
		
		email.sendEmailOnSubscriptionSignUp(user.getEmailId(), user.getUsername(), "Welcome to CourseLink !!!", new StringBuilder("Hi, Welcome to CourseLink and thank you for subscribing to us."));
		
		return "welcome";
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
	
		
		List<CourseInfoBySkill> courses;
		
		String chkSkill = skill.toLowerCase().toString();
		courses = courseRepo.getCoursesBySkill(chkSkill);
		
		
		if(!(courses.size() == 0)){
			
			model.addAttribute("courses", courses);
			return "connect/courses :: resultsList";
		}
		
		else{
			
			courses=cop.getCoursesBySkills(skill);
			model.addAttribute("courses", courses);
			return "connect/courses :: resultsList";
		}
		
	}
	
	@RequestMapping(value="/trend", method=RequestMethod.GET)
	public String getCourseByLinkedIn(Model model){
	
		
		List<Tag> trending;
		
		trending = sop.getTopTrending();
		
		//System.out.println("Testing");
		
		model.addAttribute("trending", trending);
		
		return "trend";
		
	}
	
	@RequestMapping(value="/importToMongo/{skill}", method=RequestMethod.GET)
	public String getCourseToMongo(@PathVariable String skill){
		
		List<CourseInfoBySkill> courses;
		
		courses = cop.getCoursesBySkills(skill);
		
		
		for(CourseInfoBySkill course : courses){
			
			Courses toCourse = new Courses();
			
			toCourse.setCourse_id(course.getCourse_id());
			toCourse.setSkill(skill);
			toCourse.setName(course.getName());
			toCourse.setUniversity(course.getUniversity());
			toCourse.setLink(course.getLink());
			toCourse.setInstructor_name(course.getInstructor_name());
			toCourse.setCourse_image_url(course.getCourse_image_url());
			toCourse.setSession_start(course.getSession_start());
			toCourse.setDuration(course.getDuration());
			
			if(!(course.getPreviewLink() == null)){
				toCourse.setPreviewLink(course.getPreviewLink());
			} else{
				toCourse.setPreviewLink("#");
			}
			//System.out.println(course.getPreviewLink());
			courseRepo.save(toCourse);
		}
		
		return "login";
	}
}	
