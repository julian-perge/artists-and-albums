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
import com.wecancodeit.julian.artistsandalbums.repository.AlbumRepository;
import com.wecancodeit.julian.artistsandalbums.repository.ArtistRepository;
import com.wecancodeit.julian.artistsandalbums.repository.BandRepository;

@RestController
@RequestMapping("/api")
public class ArtistApiController {
  @Autowired private ArtistRepository artistRepo;
  @Autowired private BandRepository bandRepo;
  @Autowired private AlbumRepository albumRepo;

  /*
   *
   * GETTERS
   *
   */

  @RequestMapping(value = "/artists", method = RequestMethod.GET)
  public Collection<Artist> getArtists() {
    return (Collection<Artist>) artistRepo.findAll();
  }

  @RequestMapping(value = "/artists/{artistName}", method = RequestMethod.GET)
  public Artist getArtist(@PathVariable(name = "artistName") String artistName) {
    return artistRepo.findByArtistName(artistName);
  }

  /*
   *
   * POSTERS
   *
   */

  @RequestMapping(value = "/artists/add-artist", method = RequestMethod.POST)
  public Collection<Artist> addArtist(
      @RequestParam(value = "artistName") String artistName,
      @RequestParam(required = false, value = "urlHit") String urlHit) {

    

    // if album exists
    Album albumToAddArtistTo = albumRepo.findByAlbumName(urlHit);
    Band bandToAddArtistTo = bandRepo.findByBandName(urlHit);
    Artist newArtist = artistRepo.save(new Artist(artistName, bandToAddArtistTo));
    // if the urlhit string is empty,
    // means they're adding an artist with no band
    // on the artists page
    if (albumToAddArtistTo != null) {
      Collection<Artist> artists = albumToAddArtistTo.getArtists();
      artists.add(newArtist);
      albumToAddArtistTo.setArtists(artists);
      System.out.println(urlHit);
      return albumToAddArtistTo.getArtists();
    } else if (bandToAddArtistTo != null) {
//    	newArtist.setBand(bandToAddArtistTo);
    	System.out.println(urlHit);
    	return bandToAddArtistTo.getArtists();
    } else {
    	bandToAddArtistTo = bandRepo.save(new Band(urlHit, "", null));
    }
    
    
    System.out.println(urlHit);
    return (Collection<Artist>) artistRepo.findAll();
  }

  /*
   *
   * DELETERS
   *
   */

  @RequestMapping(value = "/artists/delete-artist", method = RequestMethod.DELETE)
  public Collection<Artist> deleteArtist(
      @RequestParam(value = "artistName") String artistName,
      @RequestParam(value = "urlHit") String urlHit) {
    // urlHit params
    // 'artists'
    // 'album'
    // 'band'

    // link band and albums attached to artist to be used after deletion
    Artist artistToDelete = artistRepo.findByArtistName(artistName);
    Band bandOfArtist = artistToDelete.getBand();
    // in the case of the album api being hit to delete an artist,
    // we need to specify WHICH album artists to return
    Album albumOfArtist = albumRepo.findByAlbumName(urlHit);

    artistToDelete.getAlbums().forEach(album -> album.getArtists().remove(artistToDelete));
    artistRepo.delete(artistToDelete);

    if (bandOfArtist != null) {
      return bandOfArtist.getArtists();
    } else if (albumOfArtist != null) {
      return albumOfArtist.getArtists();
    }
    // Since it's many to many,
    // the the album needs to remove the artist since the artist owns the relationship
    return (Collection<Artist>) artistRepo.findAll();
  }
}
