package com.marketingapp2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp2.dto.LeadDto;
import com.marketingapp2.entity.Lead;
import com.marketingapp2.service.LeadService;
import com.marketingapp2.util.EmailService;

@Controller
public class LeadController {
	@Autowired
	private LeadService leadService;
	@Autowired
	private EmailService emailService;
	// handler methods
	// for create - http://localhost:8080/viewLeadForm
	// for delete- http://localhost:8080/list_leads

	@RequestMapping("viewLeadForm")
	public String viewLeadForm() {
		return "create_lead";
	}

	@RequestMapping("/saveLead")
	public String saveLeadInformation(LeadDto leadDto, Model model) {

		Lead lead = new Lead();

		lead.setFirstName(leadDto.getFirstName());
		lead.setLastName(leadDto.getLastName());
		lead.setEmail(leadDto.getEmail());
		lead.setMobile(leadDto.getMobile());
		leadService.saveLead(lead);
		emailService.sendSimpleMail(leadDto.getEmail(), "Test", "test Email");
		model.addAttribute("msg", "Record saved");
		return "create_lead";
	}

//ModelMap and Model both does the same work
	@RequestMapping("/list_leads")
	public String getAllLeads(ModelMap model) {
		List<Lead> leads = leadService.getleads();
		model.addAttribute("leads", leads);
		return "list_leads";

	}
//http://localhost:8080/delete?id=3
	@RequestMapping("/delete")
	public String deleteLead(@RequestParam("id") long id, Model model) {
		leadService.deleteLead(id);
		List<Lead> leads = leadService.getleads();
		model.addAttribute("leads", leads);// (attribute Name,attribute Value)
		return "list_leads";
	}

	@RequestMapping("/update")
	public String viewUpdateForm(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.findLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}

	@RequestMapping("/updateLead")
	public String updateLead(LeadDto dto, Model model) {
		leadService.updateLead(dto);
		List<Lead> leads = leadService.getleads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
}
