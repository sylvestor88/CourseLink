package com.project.dto;

public class StackItems {
	
	public Items [] items;
	
	boolean has_more;
	int quota_max;
	int quota_remaining;
	
	public Items[] getItems() {
		return items;
	}
	public void setItems(Items[] items) {
		this.items = items;
	}
	public boolean getHas_more() {
		return has_more;
	}
	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}
	public int getQuota_max() {
		return quota_max;
	}
	public void setQuota_max(int quota_max) {
		this.quota_max = quota_max;
	}
	public int getQuota_remaining() {
		return quota_remaining;
	}
	public void setQuota_remaining(int quota_remaining) {
		this.quota_remaining = quota_remaining;
	}
	

}
