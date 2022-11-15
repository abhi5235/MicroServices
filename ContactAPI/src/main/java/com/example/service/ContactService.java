package com.example.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Contact;
import com.example.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepo;

	public Contact addContactDetails(Contact contact) {
		return contactRepo.save(contact);
	}

	public Contact getContactDetails(int id) {
		Optional<Contact> findById = contactRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	
}
