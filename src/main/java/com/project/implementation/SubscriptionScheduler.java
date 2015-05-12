package com.project.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.util.EmailNotification;

@Component
public class SubscriptionScheduler {
	
	
	@Autowired
	EmailNotification  emailNotification;
	
	@Scheduled(fixedRate = 5000)
	public void sendSubscribedCoursesInfo(){
		
		
		// need to write scheduler code here which will compare current date with course start date of mongodb collection of courses
		
		
		emailNotification.sendEmailonSubscription("subscribermailid@gmail.com", "subscriber name");
		
		
	}

}
