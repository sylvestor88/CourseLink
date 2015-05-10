package com.project.dto;

public class Links {
	
	Categories categories [];
	Universities universities [];
	Instructors instructors[];
	Sessions sessions[];
	
	public Categories[] getCategories() {
		return categories;
	}
	public void setCategories(Categories[] categories) {
		this.categories = categories;
	}
	public Universities[] getUniversities() {
		return universities;
	}
	public void setUniversities(Universities[] universities) {
		this.universities = universities;
	}
	public Instructors[] getInstructors() {
		return instructors;
	}
	public void setInstructors(Instructors[] instructors) {
		this.instructors = instructors;
	}
	public Sessions[] getSessions() {
		return sessions;
	}
	public void setSessions(Sessions[] sessions) {
		this.sessions = sessions;
	}
	
	
	
}
