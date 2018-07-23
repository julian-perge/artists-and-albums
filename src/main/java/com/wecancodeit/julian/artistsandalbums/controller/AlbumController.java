package com.wecancodeit.julian.artistsandalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;

@Controller
public class AlbumController {
  @Autowired private AlbumRepository albumRepo;

  @RequestMapping(value = "/band/{bandName}/album/{albumName}")
  public String getAlbum(
      @PathVariable(name = "bandName") String bandName,
      @PathVariable(name = "albumName") String albumName,
      Model model) {
    model.addAttribute("album", albumRepo.findByAlbumName(albumName));
    return "album";
  }
}
