package com.bits.language.commons.model;

import com.bits.language.commons.serializer.DateDeserializer;
import com.bits.language.commons.serializer.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.*;
import java.time.LocalDate;

public abstract class UserInfoDto {

	@NotBlank
	@Size(min = 5, max = 12)
	private String username;

	@NotBlank
	@Size(min = 3, max = 24)
	private String firstName;

	@Size(max = 120)
	private String bio;

	@Size(max = 24)
	private String lastName;

	@NotNull
	private Gender gender;

	@Past
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@JsonProperty("dob")
	private LocalDate dateOfBirth;

	@NotBlank
	@Size(min = 10, max = 10)
	private String contactNumber;

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

	public UserInfoDto username(String username) {
		setUsername(username);
		return this;
	}

	public UserInfoDto firstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	public UserInfoDto lastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	public UserInfoDto gender(Gender gender) {
		setGender(gender);
		return this;
	}

	public UserInfoDto dateOfBirth(LocalDate dateOfBirth) {
		setDateOfBirth(dateOfBirth);
		return this;
	}

	public UserInfoDto bio(String bio) {
		setBio(bio);
		return this;
	}

	@Override
	public String toString() {
		return "{" + " username='" + getUsername() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='"
				+ getLastName() + "'" + ", gender='" + getGender() + "'" + ", dateOfBirth='" + getDateOfBirth() + "'"
				+ ", bio='" + getBio() + "'" + "}";
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
