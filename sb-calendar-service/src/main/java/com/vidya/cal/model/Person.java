package com.vidya.cal.model;

import java.util.Objects;

public class Person {

	private String id;
	private String name;
	private String avatar; // www.gravatar.com/avatar/88c3d17245bc58e95d3ed6df1479108e
	private String email;
	private String phone;

	public Person(String id, String name, String avatar, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}