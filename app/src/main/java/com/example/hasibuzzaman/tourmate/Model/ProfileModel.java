package com.example.hasibuzzaman.tourmate.Model;

public class ProfileModel {
	private int id;
	private String name;
	private String email;
	private String password;
	private String imagePath;
	private String phoneNumber;
	private String emergencyNo;

	public ProfileModel(int id, String name, String email, String password,
			String imagePath, String phoneNumber, String emergencyNo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
		this.phoneNumber = phoneNumber;
		this.emergencyNo = emergencyNo;
	}

	public ProfileModel(String name, String email, String password,
			String imagePath, String phoneNumber, String emergencyNo) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
		this.phoneNumber = phoneNumber;
		this.emergencyNo = emergencyNo;
	}

	public ProfileModel() {
		super();

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmergencyNo() {
		return emergencyNo;
	}

	@Override
	public String toString() {
		return name;
	}
}
