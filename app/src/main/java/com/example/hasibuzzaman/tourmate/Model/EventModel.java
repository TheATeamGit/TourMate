package com.example.hasibuzzaman.tourmate.Model;

public class EventModel {
	private int e_id;
	private int e_profile_id;
	private String e_name;
	private String e_place;
	private String e_start;
	private String e_end;
	private double e_budget;
	public EventModel(int e_id, int e_profile_id, String e_name,
			String e_place, String e_start, String e_end, double e_budget) {
		super();
		this.e_id = e_id;
		this.e_profile_id = e_profile_id;
		this.e_name = e_name;
		this.e_place = e_place;
		this.e_start = e_start;
		this.e_end = e_end;
		this.e_budget = e_budget;
	}
	public EventModel(int e_profile_id, String e_name,
			String e_place, String e_start, String e_end, double e_budget) {
		super();
		this.e_profile_id = e_profile_id;
		this.e_name = e_name;
		this.e_place = e_place;
		this.e_start = e_start;
		this.e_end = e_end;
		this.e_budget = e_budget;
	}
	public EventModel() {
		super();
	}
	public int getE_id() {
		return e_id;
	}
	public int getE_profile_id() {
		return e_profile_id;
	}
	public String getE_name() {
		return e_name;
	}
	public String getE_place() {
		return e_place;
	}
	public String getE_start() {
		return e_start;
	}
	public String getE_end() {
		return e_end;
	}
	public double getE_budget() {
		return e_budget;
	}
	@Override
	public String toString() {
		return "P_Id=" + e_profile_id + ", Name="
				+ e_name + ", Place=" + e_place ;
	}
	
	
	
}
