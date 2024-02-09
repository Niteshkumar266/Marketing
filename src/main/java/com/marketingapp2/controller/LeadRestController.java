package com.marketingapp2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp2.dto.LeadDto;
import com.marketingapp2.entity.Lead;
import com.marketingapp2.repository.LeadRepository;
//CRUD Operation
@RestController//By Applying this annotation, I can start doing my API coding here. 
@RequestMapping("/api/leads")
public class LeadRestController {

	// http://localhost:8080/api/leads

	@Autowired
	private LeadRepository leadRepo;

	@GetMapping // This method will not return java object now,
	public List<Lead> getAllLeads() {
		List<Lead> leads = leadRepo.findAll();
		return leads;
	}

	@PostMapping
	public ResponseEntity<Lead> saveLead(@RequestBody Lead lead) {
		Lead savedLead = leadRepo.save(lead);
		return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
	}

	// http://localhost:8080/api/leads?id=8
	@PutMapping
	public ResponseEntity<Lead> updateLead(@RequestParam("id") long id, @RequestBody LeadDto dto) {
		Optional<Lead> findById = leadRepo.findById(id);
		Lead lead = findById.get();
		lead.setFirstName(dto.getFirstName());
		lead.setLastName(dto.getLastName());
		lead.setEmail(dto.getEmail());
		lead.setMobile(dto.getMobile());

		Lead updatedLead = leadRepo.save(lead);
		return new ResponseEntity<>(updatedLead, HttpStatus.OK);
	}

	// http://localhost:8080/api/leads/7
	// Path Parameter
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLead(@PathVariable("id") long id) {
		leadRepo.deleteById(id);
		return new ResponseEntity<>("Record is deleted", HttpStatus.OK);
	}

	// http://localhost:8080/api/leads/
	
	@GetMapping("/{id}")//Get mapping will convert java object to Json Object
	public ResponseEntity<Lead> getLeadById(@PathVariable("id") long id) {
		Lead lead = leadRepo.findById(id).get();
		return new ResponseEntity<>(lead, HttpStatus.OK);
	}
}
