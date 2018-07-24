package com.wecancodeit.julian.artistsandalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;


@Controller
public class ArtistController {
	@Autowired private ArtistRepository artistRepo;
	
	@RequestMapping(value="/")
	public String home(Model model) {
		model.addAttribute("artists", artistRepo.findAll());
		return "redirect:/index";
	}
			
	@RequestMapping(value="/artists")
	public String getArtists(Model model) {
		model.addAttribute("artists", artistRepo.findAll());
		return "artists";
	}
	
	@RequestMapping(value="/artist/{artistName}")
	public String getArtist(@PathVariable(name="artistName") String artistName, Model model) {
		model.addAttribute("artist", artistRepo.findByArtistName(artistName));
		return "artist";
	}
}
