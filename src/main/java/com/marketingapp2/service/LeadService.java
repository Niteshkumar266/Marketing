package com.marketingapp2.service;

import java.util.List;

import com.marketingapp2.dto.LeadDto;
import com.marketingapp2.entity.Lead;

public interface LeadService {
	public void saveLead(Lead lead);

	public List<Lead> getleads();

	public void deleteLead(long id);

	public Lead findLeadById(long id);

	public void updateLead(LeadDto dto);
}
