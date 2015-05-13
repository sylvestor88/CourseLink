package com.project.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.dto.Course;
import com.project.dto.CourseInfoBySkill;
import com.project.dto.Element;
import com.project.dto.Linked;
import com.project.dto.Sessions;
import com.project.dto.SessionsElement;
import com.project.dto.StackItems;
import com.project.dto.Universities;
import com.project.dto.UniversityElement;


@Component
public class CourseraOperations {





	// takes search query skill set

	public  List<CourseInfoBySkill> getCoursesBySkills(String skill){

		List<CourseInfoBySkill> coursesForSkill=new ArrayList<CourseInfoBySkill>();


		RestTemplate restTemplate = new RestTemplate();
		Element elements;

		String courseUrl="https://api.coursera.org/api/catalog.v1/courses?q=search&query="+skill+"&includes=sessions,instructors,categories,universities&fields=photo,instructor,previewLink";
		elements = restTemplate.getForObject(courseUrl, Element.class);

		System.out.println(elements.getElements());
		for(Course c : elements.getElements())
		{

			CourseInfoBySkill course=new CourseInfoBySkill();

			course.setCourse_id(c.getId());
			course.setName(c.getName());
			course.setCourse_image_url(c.getPhoto());
			course.setInstructor_name(c.getInstructor());
			course.setPreviewLink(c.getPreviewLink());      // added for preview link

			Linked l=c.getLinked();

			setUniversityNameforCourse(course,l.getUniversities());


			setSessionInfoForCourse(course,l.getSessions());

			coursesForSkill.add(course);

		}

		return coursesForSkill;

	}


	public CourseInfoBySkill setUniversityNameforCourse(CourseInfoBySkill course,int []universities){
		RestTemplate restTemplate = new RestTemplate();

		if(universities.length>0){
			for(int i=0;i<universities.length;i++){

				String universityUrl="https://api.coursera.org/api/catalog.v1/universities/"+universities[i]+"?fields=name";

				UniversityElement ele = restTemplate.getForObject(universityUrl, UniversityElement.class);


				for(Universities u : ele.getElements())
				{

					course.setUniversity(u.getName());



				}	

			}
		}else{
			System.out.println("University list empty");
		}

		return course;

	}


	public CourseInfoBySkill setSessionInfoForCourse(CourseInfoBySkill course,int []sessions){

		RestTemplate restTemplate = new RestTemplate();

		if(sessions.length>0){

			//for(int i=0;i<sessions.length;i++){

				String sessionUrl="https://api.coursera.org/api/catalog.v1/sessions/"+sessions[0]+"?fields=startDay,startMonth,startYear,durationString";
				
				SessionsElement ele = restTemplate.getForObject(sessionUrl, SessionsElement.class);


				for(Sessions s : ele.getElements())
				{

					course.setDuration(s.getDurationString());
					
					course.setSession_start(s.getStartMonth()+"-"+s.getStartDay()+"-"+s.getStartYear());



				}	

			//}
		}else{
			System.out.println("sessions  list empty");
		}



		return course;
	}
	
	
	public StackItems getStack(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		String stackUrl="https://api.stackexchange.com/2.2/tags?order=desc&sort=popular&site=stackoverflow&fromdate=1375401600";
		
	    StackItems items = restTemplate.getForObject(stackUrl, StackItems.class);

		
	    
	    return items;
		
	}


}
