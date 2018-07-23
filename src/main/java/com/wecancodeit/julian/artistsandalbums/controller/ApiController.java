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

  @RequestMapping(value = "/recordLabels", method = RequestMethod.GET)
  public Collection<RecordLabel> getRecordLabels() {
    return (Collection<RecordLabel>) rclblRepo.findAll();
  }

  @RequestMapping(value = "/bands", method = RequestMethod.GET)
  public Collection<Band> getBands() {
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/bands", method = RequestMethod.POST)
  public Collection<Band> addBand(
      @RequestParam(value = "bandName") String bandName,
      @RequestParam(value = "recordLabel") String recordLabel) {
    RecordLabel recordLabelToAdd = rclblRepo.findByLabelName(recordLabel);
    if (recordLabelToAdd == null) {
      recordLabelToAdd = rclblRepo.save(new RecordLabel(recordLabel));
      Band newBand = bandRepo.save(new Band(bandName, null, recordLabelToAdd));
    } else {
      Band newBand = bandRepo.save(new Band(bandName, null, recordLabelToAdd));
    }
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/band/delete", method = RequestMethod.DELETE)
  public Collection<Band> deleteBand(@RequestParam(value = "bandName") String bandName) {
    Band bandToDelete = bandRepo.findByBandName(bandName);
    bandToDelete.getAlbums().removeAll(bandToDelete.getAlbums());
    bandToDelete.getArtists().removeAll(bandToDelete.getArtists());
    bandRepo.delete(bandToDelete);
    return (Collection<Band>) bandRepo.findAll();
  }

  @RequestMapping(value = "/band/{bandName}", method = RequestMethod.GET)
  public Band getBand(@PathVariable(name = "bandName") String bandName) {
    return bandRepo.findByBandName(bandName);
  }

  @RequestMapping(value = "/band/{bandName}/albums", method = RequestMethod.GET)
  public Collection<Album> getBandAlbums(@PathVariable(name = "bandName") String bandName) {
    return bandRepo.findByBandName(bandName).getAlbums();
  }

  @RequestMapping(value = "/band/{bandName}/artists", method = RequestMethod.GET)
  public Collection<Artist> getBandArtists(@PathVariable(name = "bandName") String bandName) {
    System.out.println(bandRepo.findByBandName(bandName).getBandName());
    return bandRepo.findByBandName(bandName).getArtists();
  }

  @RequestMapping(value = "/artists", method = RequestMethod.GET)
  public Collection<Artist> getArtists() {
    return (Collection<Artist>) artistRepo.findAll();
  }

  @RequestMapping(value = "/artists/{artistName}", method = RequestMethod.GET)
  public Artist getArtist(@PathVariable(name = "artistName") String artistName) {
    return artistRepo.findByArtistName(artistName);
  }

  @RequestMapping(value = "/artists/add-artist", method = RequestMethod.POST)
  public Collection<Artist> addArtist(
      @RequestParam(value = "artistName") String artistName,
      @RequestParam(value = "bandName") String bandName) {
    if (bandName == "") {
      artistRepo.save(new Artist(artistName.trim(), null));
    } else if (artistRepo.findByArtistName(artistName.trim()) == null) {
      Band bandToAddToArtist = bandRepo.findByBandName(bandName);
        if (bandToAddToArtist != null) {
          artistRepo.save(new Artist(artistName.trim(), bandToAddToArtist));
      }
    }
    return (Collection<Artist>) artistRepo.findAll();
  }

  @RequestMapping(value = "/album/add-album", method = RequestMethod.POST)
  public Collection<Album> addAlbum(
      @RequestParam(value = "recordLabel") String recordLabel,
      @RequestParam(value = "albumName") String albumName,
      @RequestParam(value = "albumGenre") String albumGenre,
      @RequestParam(value = "albumReleaseDate") String albumReleaseDate) {
    if (artistRepo.findByArtistName(artistName) != null) {
      if (rcLabelToAdd == null) {
        rcLabelToAdd = rclblRepo.save(new RecordLabel(recordLabel));
      } else if (bandToAdd == null) {
        bandToAdd = bandRepo.save(new Band(bandName, null, rcLabelToAdd));
      } else {
        // if record label, band, and artist do exist in the repos
        if (albumRepo.findByAlbumName(albumName) == null) {
          Album newAlbum =
              albumRepo.save(
                  new Album(
                      albumName,
                      bandToAdd,
                      null,
                      albumGenre,
                      rcLabelToAdd,
                      albumReleaseDate,
                      null));
        }
      }
    }
    return artistRepo.findByArtistName(artistName).getAlbums();
  }

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
