package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.dto.UserDTO;

@Entity
public class User {

	@Id
	private int userId;
	private String userfirstname;
	private String userlastname;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setUserfirstname(user.getUserfirstname());
		userDTO.setUserlastname(user.getUserlastname());
		return userDTO;
	
	}
}
