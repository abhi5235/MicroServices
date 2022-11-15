package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{

}
