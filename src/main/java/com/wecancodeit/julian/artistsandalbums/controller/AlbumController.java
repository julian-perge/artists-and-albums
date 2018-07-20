package com.wecancodeit.julian.artistsandalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Comment;
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.CommentRepository;

@Controller
public class AlbumController {
  @Autowired private AlbumRepository albumRepo;
  @Autowired private CommentRepository commentRepo;

  @RequestMapping(value = "/artists/{artistName}/{albumName}")
  public String getAlbum(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName,
      Model model) {
    model.addAttribute("album", albumRepo.findByAlbumName(albumName));
    return "album";
  }

  @RequestMapping(
      value = "/artists/{artistName}/{albumName}/post-comment",
      method = RequestMethod.POST)
  public String addComment(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName,
      @RequestParam(value = "commentDescription") String commentDescription,
      Model model) {
    Album albumToAddComment = albumRepo.findByAlbumName(albumName);
    if (albumToAddComment != null) {
      Comment commentToAdd =
          commentRepo.save(new Comment(commentDescription, albumToAddComment));
    }
    return "redirect:/artists/" + artistName + "/" + albumName;
  }
}
