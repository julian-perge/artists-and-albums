package com.wecancodeit.julian.artistsandalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecancodeit.julian.artistsandalbums.repository.SongRepository;

@Controller
public class SongController {
	
	@Autowired SongRepository songRepo;
	
	@RequestMapping(value="/artists/{artistName}/{albumName}/{songName}")
	public String getSong(
		      @PathVariable(name = "artistName") String artistName,
		      @PathVariable(name = "albumName") String albumName,
		      @PathVariable(name = "songName") String songName,
		      Model model) {
		    model.addAttribute("song", songRepo.findBySongName(songName));
		    return "song";
	}
}
