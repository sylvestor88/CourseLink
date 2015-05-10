package com.project.dto;

public class Items {
	
	String name;
	int count;
	boolean is_required;
	boolean is_moderator_only;
	boolean has_synonyms;
	 String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean getIs_required() {
		return is_required;
	}
	public void setIs_required(boolean is_required) {
		this.is_required = is_required;
	}
	public boolean getIs_moderator_only() {
		return is_moderator_only;
	}
	public void setIs_moderator_only(boolean is_moderator_only) {
		this.is_moderator_only = is_moderator_only;
	}
	public boolean getHas_synonyms() {
		return has_synonyms;
	}
	public void setHas_synonyms(boolean has_synonyms) {
		this.has_synonyms = has_synonyms;
	}
	

}
