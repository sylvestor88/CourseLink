package com.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//to prevent specified fields from being serialized or deserialized
//(i.e. not include in JSON output; or being set even if they were included)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
	//Tag name
	private String name;
	//tag count
	private int count;
	//tag date
	//private String date;
	
	public String getName() {
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
	/*public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}*/
	
	
	

}
