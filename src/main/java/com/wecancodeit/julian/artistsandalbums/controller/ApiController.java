package com.wecancodeit.julian.artistsandalbums.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Comment;
import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.CommentRepository;
import com.wecancodeit.julian.artistsandalbums.repository.SongRepository;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

  @Autowired private ArtistRepository artistRepo;
  @Autowired private AlbumRepository albumRepo;
  @Autowired private CommentRepository commentRepo;
  @Autowired private SongRepository songRepo;
  
  @RequestMapping(value = "/artists", method = RequestMethod.GET)
  public Collection<Artist> getArtists() {
	  return (Collection<Artist>) artistRepo.findAll();
  }

  @RequestMapping(value = "/artists", method = RequestMethod.POST)
  public Collection<Artist> addArtist(
      @RequestParam(value = "artistName") String artistName,
      @RequestParam(value = "recordLabel") String recordLabel) {
    if (artistRepo.findByArtistName(artistName) == null) {
      Artist newArtist = artistRepo.save(new Artist(artistName, recordLabel));
    }
    return (Collection<Artist>) artistRepo.findAll();
  }

  @RequestMapping(value = "/artists/{artistName}/{albumName}/comments", method = RequestMethod.GET)
  public Collection<Comment> getAlbumComments(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName) {

    return albumRepo.findByAlbumName(albumName).getComments();
  }

  @RequestMapping(
      value = "/artists/{artistName}/{albumName}/post-comment",
      method = RequestMethod.POST)
  public Collection<Comment> addCommentToAlbum(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName,
      @RequestParam(value = "commentDescription") String commentDescription) {
    Album albumToAddComment = albumRepo.findByAlbumName(albumName);
    if (albumToAddComment != null) {
      Comment commentToAdd = commentRepo.save(new Comment(commentDescription, albumToAddComment));
    }
    return albumRepo.findByAlbumName(albumName).getComments();
  }

  @RequestMapping(
      value = "/artists/{artistName}/{albumName}/{songName}/comments",
      method = RequestMethod.GET)
  public Collection<Comment> getSongComments(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName,
      @PathVariable(name = "songName") String songName) {

    return songRepo.findBySongName(songName).getComments();
  }

  @RequestMapping(
      value = "/artists/{artistName}/{albumName}/{songName}/post-comment",
      method = RequestMethod.POST)
  public Collection<Comment> addCommentToSong(
      @PathVariable(name = "artistName") String artistName,
      @PathVariable(name = "albumName") String albumName,
      @PathVariable(name = "songName") String songName,
      @RequestParam(value = "commentDescription") String commentDescription) {
    Song songToAddComment = songRepo.findBySongName(songName);
    if (songToAddComment != null) {
      Comment commentToAdd = commentRepo.save(new Comment(commentDescription, songToAddComment));
    }
    return songRepo.findBySongName(songName).getComments();
  }
}
