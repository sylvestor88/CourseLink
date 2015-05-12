package com.project.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.dto.CourseInfoBySkill;
import com.project.dto.UserInfo;
import com.project.util.EmailNotification;

import courseLink.CourseRepository;
import courseLink.UserRepository;

@Component
public class SubscriptionScheduler {
	
	
	@Autowired
	EmailNotification  emailNotification;
	
	@Autowired
    private CourseRepository courseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Scheduled(fixedRate = 5000)
	public void sendSubscribedCoursesInfo(){
		
		
		List<UserInfo> users=new ArrayList<UserInfo>();
		
		users=userRepo.findAll();
		
		for(UserInfo user:users){
			
			
			String[] skills=user.getSkillChoice();
			
			for(String skill:skills){
				
				List<CourseInfoBySkill> courses=courseRepo.getCoursesBySkill(skill);
				
				for(CourseInfoBySkill course:courses){
					
					
					String couseStartDate=course.getSession_start();
					
				}
				
				
				
				
			}
			
		}
		
		
		emailNotification.sendEmailonSubscription("subscribermailid@gmail.com", "subscriber name");
		
		
	}

}
