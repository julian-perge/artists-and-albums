package com.wecancodeit.julian.artistsandalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecancodeit.julian.artistsandalbums.repository.BandRepository;

@Controller
public class BandController {
	@Autowired BandRepository bandRepo;
	
	@RequestMapping(value = "/bands")
	public String getBands(Model model) {
		model.addAttribute("bands", bandRepo.findAll());
		return "bands";
	}
	
	@RequestMapping(value = "/band/{bandName}")
	public String getBands(@PathVariable(name="bandName") String bandName, Model model) {
		model.addAttribute("band", bandRepo.findByBandName(bandName));
		return "band";
	}
}
