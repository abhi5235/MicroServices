package com.example.dto;

import com.example.entity.User;

public class UserDTO {

	private int userId;
	private String userfirstname;
	private String userlastname;
	private ContactDTO contactDTO;
	
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
	public ContactDTO getContactDTO() {
		return contactDTO;
	}
	public void setContactDTO(ContactDTO contactDTO) {
		this.contactDTO = contactDTO;
	}
	public User convertToEntity() {
		    User user = new User();
		    user.setUserId(this.getUserId());
		    user.setUserfirstname(this.getUserfirstname());
		    user.setUserlastname(this.getUserlastname());
		    return user;
	}
	
}
