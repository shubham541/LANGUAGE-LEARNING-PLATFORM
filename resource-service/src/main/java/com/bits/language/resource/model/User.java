package com.bits.language.resource.model;

import com.bits.language.commons.model.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("user")
public class User {

	@Id
	private String id;

	private String username;

	private String firstName;

	private String lastName;

	private Gender gender;

	private LocalDate dateOfBirth;

	private String bio;

	private String contactNumber;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public User id(String id) {
		setId(id);
		return this;
	}

	public User username(String username) {
		setUsername(username);
		return this;
	}

	public User firstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	public User lastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	public User gender(Gender gender) {
		setGender(gender);
		return this;
	}

	public User dateOfBirth(LocalDate dateOfBirth) {
		setDateOfBirth(dateOfBirth);
		return this;
	}

	public User bio(String bio) {
		setBio(bio);
		return this;
	}

	public User contactNumber(String contactNumber) {
		setContactNumber(contactNumber);
		return this;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", username='" + getUsername() + "'" + ", firstName='" + getFirstName()
				+ "'" + ", lastName='" + getLastName() + "'" + ", gender='" + getGender() + "'" + ", dateOfBirth='"
				+ getDateOfBirth() + "'" + ", bio='" + getBio() + "'" + "}";
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
