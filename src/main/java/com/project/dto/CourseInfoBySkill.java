package com.project.dto;

public class CourseInfoBySkill {
	
	
	int course_id;
	String name;
	String university;
	String link;
	String instructor_name;
	String course_image_url;
	String session_start;
	String duration;
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getInstructor_name() {
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	public String getCourse_image_url() {
		return course_image_url;
	}
	public void setCourse_image_url(String course_image_url) {
		this.course_image_url = course_image_url;
	}
	public String getSession_start() {
		return session_start;
	}
	public void setSession_start(String session_start) {
		this.session_start = session_start;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	

}
