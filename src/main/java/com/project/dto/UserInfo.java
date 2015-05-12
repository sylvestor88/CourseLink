package com.project.dto;

public class UserInfo {
	
	private String Name;
	private String emailId;
	private String[] skillChoices;
	private String profession;
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String[] getSkillChoice() {
		return skillChoices;
	}
	public void setSkillChoice(String[] skillChoices) {
		this.skillChoices = skillChoices;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}

}
