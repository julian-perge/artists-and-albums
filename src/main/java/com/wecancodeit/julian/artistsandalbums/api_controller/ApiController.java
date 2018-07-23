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

  @Autowired private ArtistRepository artistRepo;
  @Autowired private AlbumRepository albumRepo;
  @Autowired private BandRepository bandRepo;
  @Autowired private CommentRepository commentRepo;
  @Autowired private RecordLabelRepository rclblRepo;
  @Autowired private SongRepository songRepo;

//  @RequestMapping(value = "/album/add-album", method = RequestMethod.POST)
//  public Collection<Album> addAlbum(
//      @RequestParam(value = "recordLabel") String recordLabel,
//      @RequestParam(value = "albumName") String albumName,
//      @RequestParam(value = "albumGenre") String albumGenre,
//      @RequestParam(value = "albumReleaseDate") String albumReleaseDate) {
//    if (artistRepo.findByArtistName(artistName) != null) {
//      if (rcLabelToAdd == null) {
//        rcLabelToAdd = rclblRepo.save(new RecordLabel(recordLabel));
//      } else if (bandToAdd == null) {
//        bandToAdd = bandRepo.save(new Band(bandName, null, rcLabelToAdd));
//      } else {
//        // if record label, band, and artist do exist in the repos
//        if (albumRepo.findByAlbumName(albumName) == null) {
//          Album newAlbum =
//              albumRepo.save(
//                  new Album(
//                      albumName,
//                      bandToAdd,
//                      null,
//                      albumGenre,
//                      rcLabelToAdd,
//                      albumReleaseDate,
//                      null));
//        }
//      }
//    }
//    return artistRepo.findByArtistName(artistName).getAlbums();
//  }

  @RequestMapping(value = "/album/{albumName}/artists", method = RequestMethod.GET)
  public Collection<Artist> getAlbumArtists(@PathVariable(name = "albumName") String albumName) {
    Collection<Artist> albumArtists = albumRepo.findByAlbumName(albumName).getArtists();
    return albumArtists;
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
