package com.marketingapp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp2.dto.LeadDto;
import com.marketingapp2.entity.Lead;
import com.marketingapp2.repository.LeadRepository;

@Service // this particular class is 'service layer',thats's why this annotation added
public class LeadServiceImpl implements LeadService {
	@Autowired
	private LeadRepository leadRepo;

	@Override
	public void saveLead(Lead lead) {
		leadRepo.save(lead);
	}

	@Override
	public List<Lead> getleads() {
		List<Lead> leads = leadRepo.findAll();
		return leads;
	}

	@Override
	public void deleteLead(long id) {
		leadRepo.deleteById(id);

	}

	@Override
	public Lead findLeadById(long id) {
		Optional<Lead> findById = leadRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public void updateLead(LeadDto dto) {
		
    Lead lead = new Lead();
    lead.setId(dto.getId());//from dto coping the data to entity(lead)
    lead.setFirstName(dto.getFirstName());
    lead.setLastName(dto.getLastName());
    lead.setEmail(dto.getEmail());
    lead.setMobile(dto.getMobile());
    
    leadRepo.save(lead);//update and save will done by 'save' method
	}

}
