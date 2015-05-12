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





	// get coursera courses by linkedin skill

	public  List<CourseInfoBySkill> getCoursesBySkills(String skill){

		List<CourseInfoBySkill> coursesForSkill=new ArrayList<CourseInfoBySkill>();


		RestTemplate restTemplate = new RestTemplate();
		Element elements=null;


		String courseUrl="https://api.coursera.org/api/catalog.v1/courses?q=search&query="+skill+"&includes=sessions,instructors,categories,universities&fields=photo,instructor";

		try{
			elements = restTemplate.getForObject(courseUrl, Element.class);

		}catch(Exception e){

			System.out.println("Courses Not Found : 4O4 NOT FOUND ERROR HANDLING");

		}

		//System.out.println(elements.getElements());

		if(elements !=null){

			for(Course c : elements.getElements())
			{

				CourseInfoBySkill course=new CourseInfoBySkill();

				course.setCourse_id(c.getId());
				course.setName(c.getName());
				course.setCourse_image_url(c.getPhoto());
				course.setInstructor_name(c.getInstructor());

				Linked l=c.getLinked();

				setUniversityNameforCourse(course,l.getUniversities());


				setSessionInfoForCourse(course,l.getSessions());

				coursesForSkill.add(course);

			}
		}

		return coursesForSkill;

	}


	public CourseInfoBySkill setUniversityNameforCourse(CourseInfoBySkill course,int []universities){
		RestTemplate restTemplate = new RestTemplate();

		if(universities.length>0){
			for(int i=0;i<universities.length;i++){

				String universityUrl="https://api.coursera.org/api/catalog.v1/universities/"+universities[i]+"?fields=name";

				UniversityElement ele=null;

				try{
					ele = restTemplate.getForObject(universityUrl, UniversityElement.class);
				}catch(Exception e){

					System.out.println("Universities  Not Found : 4O4 NOT FOUND ERROR HANDLING");

				}

				if(ele!=null){

					for(Universities u : ele.getElements())
					{

						course.setUniversity(u.getName());



					}	
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

			for(int i=0;i<sessions.length;i++){

				String sessionUrl="https://api.coursera.org/api/catalog.v1/sessions/"+sessions[i]+"?fields=startDay,startMonth,startYear,durationString";

				SessionsElement ele=null;

				try{
					ele= restTemplate.getForObject(sessionUrl, SessionsElement.class);
				}catch(Exception e){

					System.out.println("Sessions Not Found : 4O4 NOT FOUND ERROR HANDLING");

				}

				if(ele!=null){

					for(Sessions s : ele.getElements())
					{

						course.setDuration(s.getDurationString());

						course.setSession_start(s.getStartMonth()+"-"+s.getStartDay()+"-"+s.getStartYear());



					}	
				}
			}
		}else{
			System.out.println("sessions  list empty");
		}



		return course;
	}




}
