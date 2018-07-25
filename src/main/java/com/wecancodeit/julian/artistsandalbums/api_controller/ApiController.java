package com.wecancodeit.julian.artistsandalbums.api_controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.julian.artistsandalbums.entity.Album;
import com.wecancodeit.julian.artistsandalbums.entity.Artist;
import com.wecancodeit.julian.artistsandalbums.entity.Band;
import com.wecancodeit.julian.artistsandalbums.entity.Comment;
import com.wecancodeit.julian.artistsandalbums.entity.RecordLabel;
import com.wecancodeit.julian.artistsandalbums.entity.Song;
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.BandRepository;
import com.wecancodeit.julian.artistsandalbums.repository.CommentRepository;
import com.wecancodeit.julian.artistsandalbums.repository.RecordLabelRepository;
import com.wecancodeit.julian.artistsandalbums.repository.SongRepository;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

  //  @Autowired private ArtistRepository artistRepo;
  @Autowired private AlbumRepository albumRepo;
  @Autowired private CommentRepository commentRepo;
  @Autowired private RecordLabelRepository rclblRepo;
  @Autowired private SongRepository songRepo;
  @Autowired private BandRepository bandRepo;
  
  @RequestMapping(value = "/album/{albumName}/artists", method = RequestMethod.GET)
  public Collection<Artist> getAlbumArtists(@PathVariable(value = "albumName") String albumName) {
	  return albumRepo.findByAlbumName(albumName).getArtists();
  }

  @RequestMapping(value = "/album/add-album", method = RequestMethod.POST)
  public Collection<Album> addAlbum(
      @RequestParam(required = true, value = "albumName") String albumName,
      @RequestParam(required = true, value = "albumGenre") String albumGenre,
      @RequestParam(required = false, value = "bandName") String bandName,
      @RequestParam(required = false, value = "albumRecordLabel") String albumRecordLabel,
      @RequestParam(required = false, value = "albumReleaseDate") String albumReleaseDate) {
    Band bandToAddAlbumTo = bandRepo.findByBandName(bandName.trim());
    RecordLabel recordLabelToAdd = rclblRepo.findByLabelName(albumRecordLabel.trim());

    System.out.println(albumReleaseDate);
    // if album does not exist in repos
    if (albumRepo.findByAlbumName(albumName.trim()) == null) {
      Album newAlbum =
          albumRepo.save(
              new Album(
                  albumName, bandToAddAlbumTo, albumGenre, recordLabelToAdd, albumReleaseDate));
    }
    return bandRepo.findByBandName(bandName).getAlbums();
  }

  @RequestMapping(value = "/album/delete-album", method = RequestMethod.DELETE)
  public Collection<Album> deleteAlbum(
      @RequestParam(value = "albumName") String albumName,
      @RequestParam(required = false, value = "bandName") String bandName) {
	  
	  System.out.println(albumName);
    Album albumToDelete = albumRepo.findByAlbumName(albumName);
    albumToDelete.getSongs().removeAll(albumToDelete.getSongs());
    albumRepo.delete(albumToDelete);
    return bandRepo.findByBandName(bandName).getAlbums();
  }

  @RequestMapping(value = "/album/{albumName}/songs", method = RequestMethod.GET)
  public Collection<Song> getAlbumSongs(@PathVariable(name = "albumName") String albumName) {
    Collection<Song> albumSongs = albumRepo.findByAlbumName(albumName).getSongs();
    return albumSongs;
  }

  @RequestMapping(value = "/album/{albumName}/comments", method = RequestMethod.GET)
  public Collection<Comment> getAlbumComments(@PathVariable(name = "albumName") String albumName) {
    return albumRepo.findByAlbumName(albumName).getComments();
  }

  @RequestMapping(value = "/album/{albumName}/post-comment", method = RequestMethod.POST)
  public Collection<Comment> addCommentToAlbum(
      @PathVariable(name = "albumName") String albumName,
      @RequestParam(value = "commentDescription") String commentDescription) {
    Album albumToAddComment = albumRepo.findByAlbumName(albumName);
    if (albumToAddComment != null) {
      Comment commentToAdd = commentRepo.save(new Comment(commentDescription, albumToAddComment));
    }
    return albumRepo.findByAlbumName(albumName).getComments();
  }

  @RequestMapping(value = "/song/{songName}/comments", method = RequestMethod.GET)
  public Collection<Comment> getSongComments(@PathVariable(name = "songName") String songName) {
    return songRepo.findBySongName(songName).getComments();
  }

  @RequestMapping(value = "/song/{songName}/post-comment", method = RequestMethod.POST)
  public Collection<Comment> addCommentToSong(
      @PathVariable(name = "songName") String songName,
      @RequestParam(value = "commentDescription") String commentDescription) {
    Song songToAddComment = songRepo.findBySongName(songName);
    if (songToAddComment != null) {
      Comment commentToAdd = commentRepo.save(new Comment(commentDescription, songToAddComment));
    }
    return songRepo.findBySongName(songName).getComments();
  }
}
