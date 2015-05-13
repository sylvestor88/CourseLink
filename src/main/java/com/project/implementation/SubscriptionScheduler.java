package com.project.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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


	@Scheduled(fixedRate = 50000)
	public void sendSubscribedCoursesInfo(){

		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
		ft.setTimeZone(timeZone);
		String currentDateString=ft.format((new Date()));
		//String currentDateString="6-26-2014";            // Enable for testing purpose only
		Date currentDate=null;
		try {
			currentDate = ft.parse(currentDateString);


			List<UserInfo> users=new ArrayList<UserInfo>();

			users=userRepo.findAll();

			for(UserInfo user:users){   

				String subject="Course Link : Your Course Subscription !!!";
				StringBuilder msgBody=new StringBuilder();


				String[] skills=user.getSkillChoices();

				for(String skill:skills){  

					List<CourseInfoBySkill> courses=courseRepo.getCoursesBySkill(skill);

					for(CourseInfoBySkill course:courses){  


						String courseStartDateString=course.getSession_start();

						Date courseStartDate=null;

						try{

							courseStartDate = ft.parse(courseStartDateString);


							if(currentDate.compareTo(courseStartDate)<0){


								//in milliseconds
								long diff = courseStartDate.getTime() - currentDate.getTime(); 

								long diffDays = diff / (24 * 60 * 60 * 1000);


								if(diffDays<6){

									msgBody.append("course name : "+ course.getName()+" course start date : "+course.getSession_start());

								}

							}
						}
						catch(Exception e){
							System.out.println("coursera data improper  : date format issue");

						}


					}


				}

				System.out.println("out of user list");
				// if msgBody not null then only send the mail
				//if(msgBody!=null){

				emailNotification.sendEmailonSubscription(user.getEmailId(), user.getUsername(),subject,msgBody);
				//}
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}



}
