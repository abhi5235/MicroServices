package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Contact;
import com.example.service.ContactService;

@RestController
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@PostMapping("/contact")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
		if(contact.getUserid()==20) {
			throw new RuntimeException();
		}
		Contact addContact = contactService.addContactDetails(contact);
		return new ResponseEntity<Contact>(addContact, HttpStatus.CREATED);
	}
	@GetMapping("/getContact/{id}")
	public Contact getContact(@PathVariable int id) {
		if(id==20) {
			throw new RuntimeException();
		}
		return contactService.getContactDetails(id);
	}
	
}
